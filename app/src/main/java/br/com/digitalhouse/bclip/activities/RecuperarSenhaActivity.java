package br.com.digitalhouse.bclip.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.modules.Login.view.LoginActivity;

public class RecuperarSenhaActivity extends AppCompatActivity {

    private Button buttonRecuperar;
    private TextInputEditText emailDigitado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        emailDigitado = findViewById(R.id.et_email_recuperar);

        buttonRecuperar = findViewById(R.id.btn_recupera_senha);
        buttonRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botaoClicado(v);
            }
        });
    }

    private void botaoClicado(View v) {
        emailDigitado.setError(null);
        if (emailDigitado.getEditableText().toString().equals(" ")){
            emailDigitado.setError("esse campo não pode ficar vazio");
        } else if (emailDigitado.getEditableText().toString().equals("0123") || emailDigitado.getEditableText().toString().equals(".")){
            emailDigitado.setError("email não confere");
        }
        else {
            Snackbar.make(v, "link enviado para o email", Snackbar.LENGTH_INDEFINITE)
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
