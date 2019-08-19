package br.com.digitalhouse.bclip.modules.Login.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.modules.CadastroUsuário.view.CadastroActivity;
import br.com.digitalhouse.bclip.activities.HomeActivity;
import br.com.digitalhouse.bclip.activities.RecuperarSenhaActivity;
import br.com.digitalhouse.bclip.modules.Login.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private TextInputEditText emailEditText;
    private TextInputEditText senhaEditText;
    private Button loginButton;
    private TextView esqueceuTextview;
    private TextView novaContaTextview;

    private LoginViewModel loginViewModel;

    //private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.email_login_edit_text);
        senhaEditText = findViewById(R.id.senha_login_edit_text);

        loginButton = findViewById(R.id.btn_login);
        esqueceuTextview = findViewById(R.id.btn_esqueceu);
        novaContaTextview = findViewById(R.id.btn_registro);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.getAutenticadoLiveData()
                .observe(this, autenticado -> {
                    if (autenticado){
                        irParaPreferencias();
                    } else {
                        Toast.makeText(this, "Falha na autenticação!", Toast.LENGTH_SHORT).show();
                    }
                });
//
//        String email = emailEditText.getText().toString();
//        String senha = senhaEditText.getText().toString();


//        mAuth = FirebaseAuth.getInstance();
//        emailEditText = (TextInputEditText) findViewById(R.id.email_login_edit_text);
//        senhaEditText = (TextInputEditText) findViewById(R.id.senha_login_edit_text);
//
//        emailEditText.setError(null);
//        senhaEditText.setError(null);
//
//        if (emailEditText != null && senhaEditText != null) {
//            mAuth.signInWithEmailAndPassword(email, senha)
//                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                FirebaseUser user = mAuth.getCurrentUser();
//
//                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//
//                                Bundle bundle = new Bundle();
//
//                                bundle.putString("NOME", email);
//
//                                intent.putExtras(bundle);
//
//                                startActivity(intent);
//
//
//                            } else {
//                                Toast.makeText(LoginActivity.this, "Falha na autenticação.",
//                                        Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//        } else {
//            emailEditText.setError("usuário e/ou senha incorreto(s)");
//            senhaEditText.setError("usuário e/ou senha incorreto(s)");
//        }


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logar();
            }
        });

        esqueceuTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarSenha();
            }
        });

        novaContaTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irParaCadastro();
            }
        });
    }




    private void logar() {


        String email = emailEditText.getText().toString();
        String senha = senhaEditText.getText().toString();

        loginViewModel.autenticarUsuario(email, senha);
    }

    private void irParaPreferencias() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private void irParaCadastro() {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);

    }

    private void recuperarSenha() {
        Intent intent = new Intent(this, RecuperarSenhaActivity.class);
        startActivity(intent);

    }
}







