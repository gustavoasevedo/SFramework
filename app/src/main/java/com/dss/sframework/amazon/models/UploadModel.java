/*
 * Copyright 2010-2014 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.dss.sframework.amazon.models;

import android.content.Context;
import android.util.Log;

import com.amazonaws.mobileconnectors.s3.transfermanager.PersistableUpload;
import com.amazonaws.mobileconnectors.s3.transfermanager.Transfer;
import com.amazonaws.mobileconnectors.s3.transfermanager.TransferManager;
import com.amazonaws.mobileconnectors.s3.transfermanager.Upload;
import com.amazonaws.mobileconnectors.s3.transfermanager.exception.PauseException;
import com.amazonaws.services.s3.model.*;
import com.dss.sframework.amazon.AmazonUtil;
import com.dss.sframework.amazon.UploadFileCallbacks;
import com.dss.sframework.constant.ConstantAmazon;
import com.dss.sframework.enums.Enums;

import java.io.File;
import java.util.Locale;

/* UploadModel handles the interaction between the Upload and TransferManager.
 * This also makes sure that the file that is uploaded has the same file extension
 *
 * One thing to note is that we always create a copy of the file we are given. This
 * is because we wanted to demonstrate pause/resume which is only possible with a
 * File parameter, but there is no reliable way to get a File from a Uri(mainly
 * because there is no guarantee that the Uri has an associated File).
 *
 * You can easily avoid this by directly using an InputStream instead of a Uri.
 */
public class UploadModel extends TransferModel {
    private static final String TAG = "UploadModel";

    private Upload mUpload;
    private PersistableUpload mPersistableUpload;
    private ProgressListener mListener;
    private Status mStatus;
    private File mFile;
    private String mExtension;
    private String mediaURL;
    private Enums.MEDIA_TYPE mMediaType;
    private String mCallerFragmentTag;

    private UploadFileCallbacks mCallbacks;

//    public UploadModel(Context context, Uri uri, TransferManager manager) {
    public UploadModel(Context context, TransferManager manager, Enums.MEDIA_TYPE mediaType, File file, String extension, final String callerFragmentTag) {
//        super(context, uri, manager);
        super(context, manager, file);

        try {
            mCallbacks = (UploadFileCallbacks) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement UploadFileCallbacks");
        }

        mStatus = Status.IN_PROGRESS;
        mExtension = extension;
        mFile = file;
        mMediaType = mediaType;
//        mExtension = MimeTypeMap.getSingleton().getExtensionFromMimeType(
//                context.getContentResolver().getType(uri));
        mCallerFragmentTag = callerFragmentTag;

        mListener = new ProgressListener() {
            @Override
            public void progressChanged(ProgressEvent event) {
                if (event.getEventCode() == ProgressEvent.COMPLETED_EVENT_CODE) {
                    mStatus = Status.COMPLETED;
                    if (mFile != null) {
                        mFile.delete();
                    }

                    mCallbacks.onSuccessListenerUploadFile(mMediaType, mediaURL, mCallerFragmentTag);
                }
            }
        };
    }

    public Runnable getUploadRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                upload();
            }
        };
    }

    @Override
    public void abort() {
        if (mUpload != null) {
            mStatus = Status.CANCELED;
            mUpload.abort();
            if (mFile != null) {
                mFile.delete();
            }
        }
    }

    @Override
    public Status  getStatus() {
        return mStatus;
    }

    @Override
    public Transfer getTransfer() {
        return mUpload;
    }

    @Override
    public void pause() {
        if (mStatus == Status.IN_PROGRESS) {
            if (mUpload != null) {
                mStatus = Status.PAUSED;
                try {
                    mPersistableUpload = mUpload.pause();
                } catch (PauseException e) {
                    Log.d(TAG, "", e);
                }
            }
        }
    }

    @Override
    public void resume() {
        if (mStatus == Status.PAUSED) {
            mStatus = Status.IN_PROGRESS;
            if (mPersistableUpload != null) {
                // if it paused fine, resume
                mUpload = getTransferManager().resumeUpload(mPersistableUpload);
                mUpload.addProgressListener(mListener);
                mPersistableUpload = null;
            } else {
                // if it was actually aborted, start a new one
                upload();
            }
        }
    }

    public void upload() {
        if (mFile != null) {
            try {

                    ObjectMetadata metaData = new ObjectMetadata();
                    metaData.setContentType(
                            mMediaType == Enums.MEDIA_TYPE.VIDEO ?
                                    "video/" : "image/"
                                    + mExtension);

                if(mMediaType == Enums.MEDIA_TYPE.VIDEO){
                    mediaURL = AmazonUtil.getPrefix(getContext())
                            + "videos/"
                            + super.getFileName() + "." + mExtension;
                }else{
                    mediaURL = AmazonUtil.getPrefix(getContext())
                            + "imagens/"
                            + super.getFileName() + "." + mExtension;
                }

                    mUpload = getTransferManager().upload(
                            new PutObjectRequest(
                                    ConstantAmazon.BUCKET_NAME.toLowerCase(Locale.US),
                                    mediaURL,
                                    mFile)
                                    .withCannedAcl(CannedAccessControlList.PublicRead)
                                    .withMetadata(metaData)
                    );


                Log.i("TAG", "Upload: " + ConstantAmazon.BUCKET_NAME + mediaURL);

                mUpload.addProgressListener(mListener);
            } catch (Exception e) {
                Log.e(TAG, "", e);
                mCallbacks.onErrorListenerUploadFile("Erro no upload");
            }
        }
    }

}
