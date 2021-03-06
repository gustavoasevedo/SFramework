package com.dss.sframework.tools.constant;

import java.util.Locale;

public abstract class ConstantAmazon {

    public static final String AWS_ACCOUNT_ID =
            "026068817053";

    public static final String COGNITO_POOL_ID =
            "us-east-1:386b1749-3fc9-4d7e-bd09-87e27f4005df";

    public static final String COGNITO_ROLE_UNAUTH =
            "arn:aws:iam::026068817053:role/Cognito_TimEventosAppUnauth_Role";
    public static final String BUCKET_NAME =
            "timeventosapp";

    public static final String AMAZON_FILE_URL = "https://s3-sa-east-1.amazonaws.com/" + BUCKET_NAME.toLowerCase(Locale.US) + "/";
}
