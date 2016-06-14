package com.dss.sframework.enums;

/**
 * Created by rodrigo on 06/08/2014.
 */
public class Enums {
    public enum AvaiableFragments {
        LOGIN, ESQUECISENHA, CADASTRO, CADASTROUSUARIO, CONFIGURACOES, NAVDRAWER, PERFIL
    }

    public enum UserStatus {
        INATIVO(6);

        private final int value;

        UserStatus(final int newValue) {
            value = newValue;
        }

        public int getValue() {
            return value;
        }
    }

    public enum ViewStatus {
        NORMAL, LOADING, TRYAGAIN
    }

    //TODO: Use this, give it int values
    public enum ContentTypes {
        POST, ENQUETE, CONVITE
    }

    public enum MEDIA_TYPE {
        CAMERA(0), VIDEO(1);

        private final int value;

        MEDIA_TYPE(final int newValue) {
            value = newValue;
        }

        public int getValue() {
            return value;
        }
    }
}