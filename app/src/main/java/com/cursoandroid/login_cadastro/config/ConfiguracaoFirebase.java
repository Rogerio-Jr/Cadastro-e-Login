package com.cursoandroid.login_cadastro.config;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracaoFirebase {

    private static FirebaseAuth autentitacao;

    public static FirebaseAuth getFirebaseAutenticacao(){

        if (autentitacao == null){
            autentitacao = FirebaseAuth.getInstance();
        }
        return autentitacao;
    }

}
