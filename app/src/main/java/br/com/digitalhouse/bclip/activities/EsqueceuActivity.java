package br.com.digitalhouse.bclip.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import br.com.digitalhouse.bclip.R;


public class EsqueceuActivity extends AppCompatActivity {

    private Button buttonRecuperar;
    private TextInputEditText emailDigitado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueceu);

        emailDigitado = findViewById(R.id.esqueceu_email_edit_text);
        buttonRecuperar = findViewById(R.id.esqueceu_button_ok_id);

        buttonRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botaoClicado(v);
            }
        });

    }

    private void botaoClicado(View view) {
        emailDigitado.setError(null);
        if (emailDigitado.getEditableText().toString().equals(" ")){
            emailDigitado.setError("este campo não pode ficar em branco");
        } else {
            Snackbar.make(view, "senha recuperada enviada para o email " + emailDigitado.getEditableText().toString(), Snackbar.LENGTH_INDEFINITE)
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