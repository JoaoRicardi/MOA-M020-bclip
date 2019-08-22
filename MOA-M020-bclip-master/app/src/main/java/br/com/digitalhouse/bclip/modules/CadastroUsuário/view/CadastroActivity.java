package br.com.digitalhouse.bclip.modules.CadastroUsuário.view;

import android.accounts.AuthenticatorException;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.modules.CadastroUsuário.viewmodel.CadastroUsuarioViewModel;
import br.com.digitalhouse.bclip.modules.Login.view.LoginActivity;

public class CadastroActivity extends AppCompatActivity {

    private TextInputEditText nomeEditText;
    private TextInputEditText emailEditText;
    private TextInputEditText senhaEditText;
    private TextInputEditText confirmaSenhaEditText;
    private Button okButton;
    private String emBranco = "";

    private CadastroUsuarioViewModel cadastroUsuarioViewModel;


    private static final String TAG = "CadastroActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        cadastroUsuarioViewModel = ViewModelProviders.of(this).get(CadastroUsuarioViewModel.class);

        nomeEditText = findViewById(R.id.nome_edit_text);
        emailEditText = findViewById(R.id.et_email);
        senhaEditText = findViewById(R.id.senha_login_edit_text);
        confirmaSenhaEditText = findViewById(R.id.et_confirma_senha);
        okButton = findViewById(R.id.btn_ok);

        okButton.setOnClickListener(v -> botaoClicado(v));

        cadastroUsuarioViewModel.getCadastradoLiveData()
                .observe(this, cadastrado -> {
                    if (cadastrado) {
                        irParaLogin();
                    } else {
                        Toast.makeText(this, "Falha na autenticação", Toast.LENGTH_SHORT).show();
                    }
                });

    }


    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    private void botaoClicado(View view) {
        nomeEditText.setError(null);
        emailEditText.setError(null);
        senhaEditText.setError(null);
        confirmaSenhaEditText.setError(null);

        if (!senhaEditText.getEditableText().toString().equals(confirmaSenhaEditText.getEditableText().toString())) {
            senhaEditText.setError("as senhas precisam ser as mesmas");
            confirmaSenhaEditText.setError("as senhas precisam as mesmas");

        } else if (emailEditText.getEditableText().toString().equals(emBranco)) {
            emailEditText.setError("esse campo não pode estar vazio");

        } else if (nomeEditText.getEditableText().toString().equals(emBranco)) {
            nomeEditText.setError("esse campo não pode estar vazio");
        } else {
            cadastrar();

        }


    }

    private void cadastrar() {

        String nome = nomeEditText.getEditableText().toString();
        String email = emailEditText.getEditableText().toString();
        String senha = senhaEditText.getEditableText().toString();


        cadastroUsuarioViewModel.cadastrarUsuario(nome, email, senha, this);


    }


    private void irParaLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}

