package br.com.digitalhouse.bclip.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import br.com.digitalhouse.bclip.R;


public class LoginActivity extends AppCompatActivity {

    private TextInputEditText usuarioEditText;
    private TextInputEditText senhaEditText;
    private TextView esqueceuTextView;
    private Button okButton;
    private TextView novaContaEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuarioEditText = findViewById(R.id.login_usuario_edit_text);
        senhaEditText = findViewById(R.id.login_senha_edit_text);
        esqueceuTextView = findViewById(R.id.login_esqueceu_text_view);
        okButton = findViewById(R.id.login_button_ok_id);
        novaContaEditText = findViewById(R.id.login_registrar_text_view);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irParaCadastroActivity();
            }
        });

        esqueceuTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irParaEsqueceuActivity();
            }
        });

        novaContaEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irParaRegistroActivity();
            }
        });

    }

    private void irParaRegistroActivity() {
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }

    private void irParaEsqueceuActivity() {
        Intent intent = new Intent(this, EsqueceuActivity.class);
        startActivity(intent);
    }

    private void irParaCadastroActivity() {

        String usuarioDigitado = usuarioEditText.getEditableText().toString();
        String senhaDigitada = senhaEditText.getEditableText().toString();

        usuarioEditText.setError(null);
        senhaEditText.setError(null);

        if(usuarioDigitado == null || senhaDigitada == null){

            usuarioEditText.setError("esse campo não pode ficar em branco");
            senhaEditText.setError("esse campo não pode ficar em branco");

        } else {

            Intent intent = new Intent(this, CadastroActivity.class);

            Bundle bundle = new Bundle();

            bundle.putString("NOME", usuarioDigitado);

            intent.putExtras(bundle);

            startActivity(intent);

        }



    }
}
