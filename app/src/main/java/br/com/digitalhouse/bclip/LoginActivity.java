package br.com.digitalhouse.bclip;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText usuarioEditText;
    private TextInputEditText senhaEditText;
    private TextView esqueceuSenhaButton;
    private Button loginButton;
    private TextView criarContaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuarioEditText = findViewById(R.id.login_usuario_edit_text);
        senhaEditText = findViewById(R.id.login_senha_edit_text);
        esqueceuSenhaButton = findViewById(R.id.login_esqueceu_senha_button);
        loginButton = findViewById(R.id.login_button);
        criarContaButton = findViewById(R.id.login_criar_conta_button);

        esqueceuSenhaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarSenha();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logar();
            }
        });

        criarContaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarConta();
            }
        });
    }

    private void criarConta() {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }

    private void recuperarSenha() {
        // Mudar para mandar email
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void logar() {

        String usuarioDigitado = usuarioEditText.getEditableText().toString();
        String senhaDigitada = senhaEditText.getEditableText().toString();

        usuarioEditText.setError(null);
        senhaEditText.setError(null);

        if (usuarioDigitado.equals("mariana@digitalhouse.com") && senhaDigitada.equals("123456")) {

            Intent intent = new Intent(this, MainActivity.class);

            Bundle bundle = new Bundle();

            bundle.putString("USUARIO", usuarioDigitado);

            intent.putExtras(bundle);

            startActivity(intent);

        } else {
            usuarioEditText.setError("Usuário e/ou senha incorreto(s)");
            senhaEditText.setError("Usuário e/ou senha incorreto(s)");
        }
    }


}


