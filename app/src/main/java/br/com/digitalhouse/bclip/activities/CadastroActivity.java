package br.com.digitalhouse.bclip.activities;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.digitalhouse.bclip.R;

public class CadastroActivity extends AppCompatActivity {

    private TextInputEditText nomeUsuario;
    private TextInputEditText emailUsuario;
    private TextInputEditText senhaUsuario;
    private TextInputEditText confirmaSenhaUsuario;

    private Button btnSalvaUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

//        nomeUsuario = findViewById(R.id.edt_text_nome_usuario);
//        emailUsuario = findViewById(R.id.edt_text_email);
//        senhaUsuario = findViewById(R.id.edt_text_senha);
//        confirmaSenhaUsuario = findViewById(R.id.edt_text_confirma_senha);
//
//        btnSalvaUsuario = findViewById(R.id.btn_ok);

//
//
//        btnSalvaUsuario.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(nomeUsuario.getEditableText().toString().equals("")){
//                    nomeUsuario.setError("Campo nome não pode ser vazio!");
//                }
//                else if (emailUsuario.getEditableText().toString().equals("")){
//                    emailUsuario.setError("Campo Email não pode ser vazio!");
//                }
//
//                else if (senhaUsuario.getEditableText().toString().equals(senhaUsuario.getEditableText().toString())
//                        && !confirmaSenhaUsuario.toString().equals("")
//                        && !confirmaSenhaUsuario.toString().equals("")){
//
//                    botaoClicado(v);
//
//                }else {
//                    senhaUsuario.setError("senhas não conferem!");
//                }
//
//            }
//        });
//
//    }
//    public void botaoClicado(View view){
//        Snackbar.make(view, "USUARIO CADASTRADO!!!", Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        }).setActionTextColor(getResources().getColor(R.color.colorPrimary))
//                .show();
//

    }
}
