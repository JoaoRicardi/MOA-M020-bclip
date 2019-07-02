package br.com.digitalhouse.bclip.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.digitalhouse.bclip.R;

public class CadastroEmpresaActivity extends AppCompatActivity {

    private TextInputEditText empresaUsuarioEditText;
    private TextInputEditText concorrenteAEditText;
    private TextInputEditText concorrenteBEditText;
    private TextInputEditText concorrenteCEditText;
    private Button adicionarConcorrenteButton;
    private Button okButton;
    private String emBranco = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_empresa);

        empresaUsuarioEditText = findViewById(R.id.et_empresa_usuario);
        concorrenteAEditText = findViewById(R.id.et_concorrente_a);
        concorrenteBEditText = findViewById(R.id.et_concorrente_b);
        concorrenteCEditText = findViewById(R.id.et_concorrente_c);
        okButton = findViewById(R.id.btn_ok_cadastro);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botaoClicado(v);
            }
        });
    }

    private void botaoClicado(View v) {

        if (empresaUsuarioEditText.getEditableText().toString().equals(emBranco)){
            empresaUsuarioEditText.setError("esse campo não pode ficar vazio");
        } else {
            Intent intent = new Intent(this, PrefereciasActivity.class);
            startActivity(intent);
        }

    }

}