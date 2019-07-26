package br.com.digitalhouse.bclip.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import br.com.digitalhouse.bclip.R;


public class RegistroActivity extends AppCompatActivity {

    private TextInputEditText usuarioEditText;
    private TextInputEditText emailEditText;
    private TextInputEditText senhaEditText;
    private TextInputEditText confirmaSenhaEditText;
    private Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        usuarioEditText = findViewById(R.id.registro_usuario_edit_text);
        emailEditText = findViewById(R.id.registro_email_edit_text);
        senhaEditText = findViewById(R.id.registro_senha_edit_text);
        confirmaSenhaEditText = findViewById(R.id.registro_confirma_senha_edit_text);
        okButton = findViewById(R.id.registro_button_ok_id);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botaoClicado(view);
            }
        });

    }

    private void botaoClicado(View view) {
        usuarioEditText.setError(null);
        emailEditText.setError(null);
        senhaEditText.setError(null);
        confirmaSenhaEditText.setError(null);

        if(!senhaEditText.getEditableText().toString().equals(confirmaSenhaEditText.getEditableText().toString())){
            senhaEditText.setError("as senhas devem ser as mesmas");
            confirmaSenhaEditText.setError("as senhas devem ser as mesmas");
        } else if (emailEditText.getEditableText().toString().equals(" ")){
            emailEditText.setError("esse campo não pode ficar vazio");
        }else if (usuarioEditText.getEditableText().toString().equals(" ")){
            usuarioEditText.setError("esse campo não pode ficar vazio");
        } else {
            Snackbar.make(view, "usuário cadastrado com sucesso", Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            irParaLogin();
                        }
                    }).setActionTextColor(getResources().getColor(R.color.colorAccent))
                    .show();
        }
    }

    private void irParaLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
}
