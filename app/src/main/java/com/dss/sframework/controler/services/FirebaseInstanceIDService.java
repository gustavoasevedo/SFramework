package com.dss.sframework.controler.services;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String token) {
        //Crie aqui a implementação de envio do Token ao servidor
    }
}
