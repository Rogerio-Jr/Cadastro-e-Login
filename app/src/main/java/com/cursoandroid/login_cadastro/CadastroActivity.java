package com.cursoandroid.login_cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    public void btCadastro(View view){
        startActivity(new Intent(this, MenuPrincialActivity.class));
    }

    public void btJaCadastrado(View view){
        startActivity( new Intent(this, LoginActivity.class));
    }
}