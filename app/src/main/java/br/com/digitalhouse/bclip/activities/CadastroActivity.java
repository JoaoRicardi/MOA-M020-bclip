package br.com.digitalhouse.bclip.activities;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.com.digitalhouse.bclip.R;

public class CadastroActivity extends AppCompatActivity {

    private TextInputEditText usuarioEditText;
    private TextInputEditText emailEditText;
    private TextInputEditText senhaEditText;
    private TextInputEditText confirmaSenhaEditText;
    private Button okButton;
    private String emBranco = "";

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        firebaseAuth = FirebaseAuth.getInstance();

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

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
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


            firebaseAuth.createUserWithEmailAndPassword(emailEditText.getText().toString(), confirmaSenhaEditText.getText().toString())

                    .addOnFailureListener(new OnFailureListener() {
                        @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            ((AuthenticatorException) e).printStackTrace();
                            String errorMessage = e.getMessage();

                            Log.e("Causa: ", errorMessage);
                        }
                    })
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Snackbar.make(view, "usuario cadastrado com sucesso", Snackbar.LENGTH_INDEFINITE)
                                    .setAction("OK", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            irParaLogin();
                                        }
                                    }).setActionTextColor(getResources().getColor(R.color.colorAccent))
                                    .show();

                        } else {
                            Toast.makeText(CadastroActivity.this, "Falha ao criar conta.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        }
                    });
        }

    }

    private void irParaLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}

