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

package com.dss.sframework.tools.amazon.models;

import android.content.Context;

import com.amazonaws.mobileconnectors.s3.transfermanager.Transfer;
import com.amazonaws.mobileconnectors.s3.transfermanager.TransferManager;
import com.dss.sframework.tools.util.MD5;

import java.io.File;
import java.util.LinkedHashMap;

/*
 * The TransferModel is a class that encapsulates a transfer. It handles the 
 * interaction with the underlying TransferManager and Upload/Download classes
 */
public abstract class TransferModel {
    private static final String TAG = "TransferModel";

    public enum Status {
        IN_PROGRESS, PAUSED, CANCELED, COMPLETED
    }

    // all TransferModels have associated id which is their key to sModels
    private static LinkedHashMap<Integer, TransferModel> sModels =
            new LinkedHashMap<>();
    private static int sNextId = 1;

    private String mFileName;
    private Context mContext;
//    private Uri mUri;
    private File mFile;
    private int mId;
    private TransferManager mManager;

    public static TransferModel getTransferModel(int id) {
        return sModels.get(id);
    }

    public static TransferModel[] getAllTransfers() {
        TransferModel[] models = new TransferModel[sModels.size()];
        return sModels.values().toArray(models);
    }

//    public TransferModel(Context context, Uri uri, TransferManager manager) {
    public TransferModel(Context context, TransferManager manager, File file) {
        mContext = context;
//        mUri = uri;
        mFile = file;
        mManager = manager;
//        String uriString = uri.toString();
//        mFileName = AmazonUtil.getFileName(uriString);
        mFileName = MD5.calculateMD5(mFile);
        mId = sNextId++;

//        manager.getConfiguration().
        sModels.put(mId, this);
    }

    public String getFileName() {
        return mFileName;
    }

    public int getId() {
        return mId;
    }

    public int getProgress() {
        Transfer transfer = getTransfer();
        if (transfer != null) {
            return (int) transfer.getProgress().getPercentTransferred();
        }
        return 0;
    }

//    public Uri getUri() {
//        return mUri;
//    }
    public File getFile() {
        return mFile;
    }

    public abstract void abort();

    public abstract Status getStatus();

    public abstract Transfer getTransfer();

    public abstract void pause();

    public abstract void resume();

    protected Context getContext() {
        return mContext;
    }

    protected TransferManager getTransferManager() {
        return mManager;
    }

    public void setFileName(String mFileName) {
        this.mFileName = mFileName;
    }
}
