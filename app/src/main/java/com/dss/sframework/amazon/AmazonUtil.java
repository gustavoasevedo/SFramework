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

package com.dss.sframework.amazon;

import android.content.Context;
import android.util.Log;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest.KeyVersion;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.dss.sframework.constant.ConstantAmazon;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* 
 * This class just handles getting the client since we don't need to have more than
 * one per application
 */
public class AmazonUtil {
    private static AmazonS3Client sS3Client;
    private static CognitoCachingCredentialsProvider sCredProvider;

    public static CognitoCachingCredentialsProvider getCredProvider(Context context) {
        if (sCredProvider == null) {
            sCredProvider = new CognitoCachingCredentialsProvider(
                    context,

                    ConstantAmazon.getAwsAccountId(),
                    ConstantAmazon.getCognitoPoolId(),
                    ConstantAmazon.getCognitoRoleUnauth(),
                    null,
//                    Regions.SA_EAST_1);
                    Regions.US_EAST_1);
        }
        return sCredProvider;
    }


    public static String getPrefix(Context context) {
        return String.valueOf(1) + "/";

    }

    public static AmazonS3Client getS3Client(Context context) {
        if (sS3Client == null) {
            sS3Client = new AmazonS3Client(getCredProvider(context));
        }
        return sS3Client;
    }

    public static String getFileName(String path) {
        Log.i("TAG", "path: " + path);
        return path.substring(path.lastIndexOf("/") + 1).replace("%3A", "-");
    }

    public static boolean doesBucketExist() {
        return sS3Client.doesBucketExist(ConstantAmazon.getBucketName().toLowerCase(Locale.US));
    }

    public static void createBucket() {
        sS3Client.createBucket(ConstantAmazon.getBucketName().toLowerCase(Locale.US));
    }

    public static void deleteBucket() {
        String name = ConstantAmazon.getBucketName().toLowerCase(Locale.US);
        List<S3ObjectSummary> objData = sS3Client.listObjects(name).getObjectSummaries();
        if (objData.size() > 0) {
            DeleteObjectsRequest emptyBucket = new DeleteObjectsRequest(name);
            List<KeyVersion> keyList = new ArrayList<>();
            for (S3ObjectSummary summary : objData) {
                keyList.add(new KeyVersion(summary.getKey()));
            }
            emptyBucket.withKeys(keyList);
            sS3Client.deleteObjects(emptyBucket);
        }
        sS3Client.deleteBucket(name);
    }
}
