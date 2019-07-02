package br.com.digitalhouse.bclip.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.digitalhouse.bclip.R;

public class CadastroActivity extends AppCompatActivity {

    private TextInputEditText usuarioEditText;
    private TextInputEditText emailEditText;
    private TextInputEditText senhaEditText;
    private TextInputEditText confirmaSenhaEditText;
    private Button okButton;
    private String emBranco = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        usuarioEditText = findViewById(R.id.et_usuario);
        emailEditText = findViewById(R.id.et_email);
        senhaEditText = findViewById(R.id.et_senha);
        confirmaSenhaEditText = findViewById(R.id.et_confirma_senha);
        okButton = findViewById(R.id.btn_ok);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botaoClicado(v);
            }
        });
    }

    private void botaoClicado(View view) {
        usuarioEditText.setError(null);
        emailEditText.setError(null);
        senhaEditText.setError(null);
        confirmaSenhaEditText.setError(null);

        if (!senhaEditText.getEditableText().toString().equals(confirmaSenhaEditText.getEditableText().toString())){
            senhaEditText.setError("as senhas precisam ser as mesmas");
            confirmaSenhaEditText.setError("as senhas precisam as mesmas");

        } else if (emailEditText.getEditableText().toString().equals(emBranco)){
            emailEditText.setError("esse campo não pode estar vazio");

        } else if (usuarioEditText.getEditableText().toString().equals(emBranco)){
            usuarioEditText.setError("esse campo não pode estar vazio");
        }

        else {
            Snackbar.make(view, "usuario cadastrado com sucesso", Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
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

