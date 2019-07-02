package br.com.digitalhouse.bclip.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.digitalhouse.bclip.R;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText usuarioEditText;
    private TextInputEditText senhaEditText;
    private Button loginButton;
    private TextView esqueceuTextview;
    private TextView novaContaTextview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuarioEditText = findViewById(R.id.et_usuario);
        senhaEditText = findViewById(R.id.et_senha);
        loginButton = findViewById(R.id.btn_login);
        esqueceuTextview = findViewById(R.id.btn_esqueceu);
        novaContaTextview = findViewById(R.id.btn_registro);

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

        String usuarioDigitado = usuarioEditText.getEditableText().toString();
        String senhaDigitada = senhaEditText.getEditableText().toString();

        usuarioEditText.setError(null);
        senhaEditText.setError(null);

        if (usuarioDigitado != null && senhaDigitada != null) {

            Intent intent = new Intent(this, CadastroEmpresaActivity.class);

            Bundle bundle = new Bundle();

            bundle.putString("NOME", usuarioDigitado);

            intent.putExtras(bundle);

            startActivity(intent);
        } else {
            usuarioEditText.setError("usuário e/ou senha incorreto(s)");
            senhaEditText.setError("usuário e/ou senha incorreto(s)");
        }
    }


}
