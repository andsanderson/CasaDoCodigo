package br.com.caelum.casadocodigo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.caelum.casadocodigo.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_email)
    public EditText campoEmail;

    @BindView(R.id.login_senha)
    public EditText campoSenha;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener listener;

    private boolean flagUsuarioLogado =false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        firebaseAuth = FirebaseAuth.getInstance();


        listener =  new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= firebaseAuth.getCurrentUser();
                if(user != null && !flagUsuarioLogado) {
                    flagUsuarioLogado=true;
                    Intent vaiParaMain = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(vaiParaMain);
                    finish();
                }

            }
        };

        firebaseAuth.addAuthStateListener(listener);

    }

    @OnClick(R.id.login_logar)
    public void logar(){
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        if(!email.isEmpty() || !senha.isEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Snackbar.make(campoEmail, task.getException().getMessage().toString(), Snackbar.LENGTH_LONG).show();
                            }
                        }
                    });
        } else{
                Snackbar.make(campoSenha,"Por favor complete todos os campos",Snackbar.LENGTH_LONG).show();
            }

        }

    @OnClick({R.id.login_novo})
    public void novoUsuario(){
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        if(!email.isEmpty() || !senha.isEmpty()) {
            firebaseAuth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Snackbar.make(campoEmail, "Acesso não autorizado, verifique suas informações", Snackbar.LENGTH_LONG).show();
                            }
                        }
                    });
        } else{
            Snackbar.make(campoSenha,"Por favor complete todos os campos",Snackbar.LENGTH_LONG).show();
        }


    }

    }

