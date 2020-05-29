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

public class  CadastroActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoSenha;
    private Button buttoncadastrar;
    private FirebaseAuth autenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro) ;

        campoNome = findViewById(R.id.editNomeC);
        campoEmail = findViewById(R.id.editEmailC);
        campoSenha = findViewById(R.id.editSenhaC);
        buttoncadastrar = findViewById(R.id.button2);

        buttoncadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoNome = campoNome.getText().toString();
                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();

                if (!textoNome.isEmpty()){
                    if (!textoEmail.isEmpty()){
                        if (!textoSenha.isEmpty()){

                            usuario = new Usuario();
                            usuario.setNome(textoNome);
                            usuario.setEmail(textoEmail);
                            usuario.setSenha(textoSenha);

                            cadastrarUsuario();

                        }else {
                            Toast.makeText(CadastroActivity.this, "Campo senha não preenchido!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(CadastroActivity.this, "Campo email não preenchido!",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(CadastroActivity.this, "Campo nome não preenchido!",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void cadastrarUsuario(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
          usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CadastroActivity.this, "Sucesso ao cadastrar usuario!",
                            Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(CadastroActivity.this, "Erro ao cadastrar usuario",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void btJaCadastrado(View view){
        startActivity( new Intent(this, LoginActivity.class));
    }
}