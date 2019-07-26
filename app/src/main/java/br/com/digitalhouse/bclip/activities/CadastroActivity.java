package br.com.digitalhouse.bclip.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.com.digitalhouse.bclip.R;


public class CadastroActivity extends AppCompatActivity {

    private EditText nomeEmpresaEditText;
    private Button cadastroOkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nomeEmpresaEditText = findViewById(R.id.cadastro_empresa_edit_text);

        cadastroOkButton = findViewById(R.id.cadastro_button_ok_id);
        cadastroOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irParaPreferenciasActivity();
            }
        });


    }

    private void irParaPreferenciasActivity() {
        if(nomeEmpresaEditText.getEditableText().toString().equals(" ")){
            nomeEmpresaEditText.setError("esse campo não pode ficar em branco");
        } else{
            Intent intent = new Intent(this, ConcorrentesActivity.class);
            startActivity(intent);
        }
    }
}
