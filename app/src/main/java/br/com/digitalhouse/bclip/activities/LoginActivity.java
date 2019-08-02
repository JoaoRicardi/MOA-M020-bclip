package br.com.digitalhouse.bclip.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.digitalhouse.bclip.R;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText emailEditText;
    private TextInputEditText senhaEditText;
    private Button loginButton;
    private TextView esqueceuTextview;
    private TextView novaContaTextview;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        usuarioEditText = findViewById(R.id.et_usuario);
//        senhaEditText = findViewById(R.id.et_senha);

        loginButton = findViewById(R.id.btn_login);
        esqueceuTextview = findViewById(R.id.btn_esqueceu);
        novaContaTextview = findViewById(R.id.btn_registro);



        mAuth = FirebaseAuth.getInstance();
        emailEditText = (TextInputEditText) findViewById(R.id.email_editText_id);
        senhaEditText = (TextInputEditText) findViewById(R.id.et_senha);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logar();
            }
        });

        esqueceuTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irParaEsqueceuActivity();
            }
        });

        novaContaTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irParaRegistroActivity();
            }
        });
    }

    private void irParaRegistroActivity() {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);

    }

    private void irParaEsqueceuActivity() {
        Intent intent = new Intent(this, RecuperarSenhaActivity.class);
        startActivity(intent);

    }

    private void logar() {



        String usuarioDigitado = emailEditText.getEditableText().toString();
        String senhaDigitada = senhaEditText.getEditableText().toString();

        emailEditText.setError(null);
        senhaEditText.setError(null);

        if (usuarioDigitado != null && senhaDigitada != null) {


            mAuth.signInWithEmailAndPassword(usuarioDigitado, senhaDigitada)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();

                                Intent intent = new Intent(LoginActivity.this, PrefereciasActivity.class);

                                Bundle bundle = new Bundle();

                                bundle.putString("NOME", usuarioDigitado);

                                intent.putExtras(bundle);

                                startActivity(intent);


                            } else {
                                Toast.makeText(LoginActivity.this, "Falha na autenticação.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });




        } else {
            emailEditText.setError("usuário e/ou senha incorreto(s)");
            senhaEditText.setError("usuário e/ou senha incorreto(s)");
        }
    }


}
