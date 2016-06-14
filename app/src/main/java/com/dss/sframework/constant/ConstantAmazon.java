package com.dss.sframework.constant;

import java.util.Locale;

/**
 * Created by gustavo.vieira on 11/05/2015.
 */
public abstract class ConstantAmazon {

    private static final String AWS_ACCOUNT_ID =
            "026068817053";

    private static final String COGNITO_POOL_ID =
            "us-east-1:386b1749-3fc9-4d7e-bd09-87e27f4005df";

    private static final String COGNITO_ROLE_UNAUTH =
            "arn:aws:iam::026068817053:role/Cognito_TimEventosAppUnauth_Role";
    private static final String BUCKET_NAME =
            "timeventosapp";

    private static final String AMAZON_FILE_URL = "https://s3-sa-east-1.amazonaws.com/" + getBucketName().toLowerCase(Locale.US) + "/";

    public static String getAwsAccountId() {
        return AWS_ACCOUNT_ID;
    }

    public static String getCognitoPoolId() {
        return COGNITO_POOL_ID;
    }

    /**
     * Could pass AuthRole too, see CognitoCachingCredentialsProvider > AmazonUtil : getCredProvider
     * @{@link  //com.squares. timevent.amazon.AmazonUtil}
     */
    public static String getCognitoRoleUnauth() {
        return COGNITO_ROLE_UNAUTH;
    }

    public static String getBucketName() {
        return BUCKET_NAME;
    }

    public static String getAmazonFileUrl() {
        return AMAZON_FILE_URL;
    }
}
