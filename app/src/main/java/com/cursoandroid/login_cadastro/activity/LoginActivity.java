package com.cursoandroid.login_cadastro.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cursoandroid.login_cadastro.R;
import com.cursoandroid.login_cadastro.config.ConfiguracaoFirebase;
import com.cursoandroid.login_cadastro.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class LoginActivity extends AppCompatActivity {

    private EditText campoEmail, campoSenha;
    private Button buttonLogin;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoEmail = findViewById(R.id.editEmailC);
        campoSenha = findViewById(R.id.editSenhaC);
        buttonLogin = findViewById(R.id.button2);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoEmail = campoEmail.getText().toString();
                String textSenha = campoSenha.getText().toString();

                if(!textoEmail.isEmpty()){
                    if(!textSenha.isEmpty()){
                        usuario = new Usuario();
                        usuario.setEmail( textoEmail );
                        usuario.setSenha( textSenha );
                        validarLogin();

                    }else{
                        Toast.makeText(LoginActivity.this, "Preencha a senha",
                                Toast.LENGTH_SHORT).show();
                    }
                }else {
                        Toast.makeText(LoginActivity.this, "Preencha o email",
                                Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public void validarLogin(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    abrirMenuPrincipal();
                    Toast.makeText(LoginActivity.this, "Entrando...",
                            Toast.LENGTH_SHORT).show();


                }else {
                    String excecao = "";
                    try {
                        throw task.getException();
                    }catch (FirebaseAuthInvalidCredentialsException e) {
                        excecao = "E-mail e senha não correspodem a um usuário cadastrado";
                    }catch (FirebaseAuthInvalidUserException e){
                        excecao = "Usuário não está cadastrado";
                    }catch (Exception e){
                        excecao = "Erro ao cadastrar usuário: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(LoginActivity.this, excecao,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void abrirMenuPrincipal(){
        Intent i = new Intent(LoginActivity.this, MenuPrincialActivity.class);
        startActivity(i);
        finish();
    }

}
