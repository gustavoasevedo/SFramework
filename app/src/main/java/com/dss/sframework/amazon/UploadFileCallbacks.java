package com.dss.sframework.amazon;

import com.dss.sframework.enums.Enums;

/**
 *
 * Created by rodrigo on 07/10/2014.
 */
public interface UploadFileCallbacks {//extends BaseCallbacks {
    void onErrorListenerUploadFile(String error);
    void onSuccessListenerUploadFile(Enums.MEDIA_TYPE mediaType, String mediaURL, String callerFragmentTag);
}