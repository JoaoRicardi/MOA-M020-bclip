package br.com.digitalhouse.modules.Login.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.activities.RecuperarSenhaActivity;
import br.com.digitalhouse.modules.Cadastro.view.CadastroActivity;
import br.com.digitalhouse.modules.CadastroEmpresa.view.PreferenciaEmpresaActivity;
import br.com.digitalhouse.modules.Preferencias.view.PreferenciasActivity;

public class LoginActivity extends AppCompatActivity {


    private static final String TAG = "LoginActivity";
    private TextInputEditText emailEditText;
    private TextInputEditText senhaEditText;
    private Button loginButton;
    private TextView esqueceuTextview;
    private TextView novaContaTextview;
    private FirebaseAuth mAuth;

    private SignInButton signInButtonGoogle;
    GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // erro nessa linha

        loginButton = findViewById(R.id.btn_login);
        esqueceuTextview = findViewById(R.id.btn_esqueceu);
        novaContaTextview = findViewById(R.id.btn_registro);

        signInButtonGoogle = findViewById(R.id.sign_in_button_google_id);

        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);



        signInButtonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });


        mAuth = FirebaseAuth.getInstance();
        emailEditText = (TextInputEditText) findViewById(R.id.email_editText_id);
        senhaEditText = (TextInputEditText) findViewById(R.id.et_senha);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logar();
            }
        });

        esqueceuTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irParaEsqueceuActivity();
            }
        });

        novaContaTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irParaRegistroActivity();
            }
        });
    }

    private void irParaRegistroActivity() {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);

    }

    private void irParaEsqueceuActivity() {
        Intent intent = new Intent(this, RecuperarSenhaActivity.class);
        startActivity(intent);

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);

                Intent intent = new Intent(LoginActivity.this, PreferenciaEmpresaActivity.class);
                startActivity(intent); ///// tentando trocar de activity

            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }

        }

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Falha no login", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            Toast.makeText(LoginActivity.this, "bem vindo "+ personName + "!!!", Toast.LENGTH_SHORT).show();

        }
    }


    private void logar() {

        String usuarioDigitado = emailEditText.getEditableText().toString();
        String senhaDigitada = senhaEditText.getEditableText().toString();

        emailEditText.setError(null);
        senhaEditText.setError(null);

        if (usuarioDigitado != null && senhaDigitada != null) {


            mAuth.signInWithEmailAndPassword(usuarioDigitado, senhaDigitada)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();

                                Intent intent = new Intent(LoginActivity.this, PreferenciaEmpresaActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("NOME", usuarioDigitado);
                                intent.putExtras(bundle);
                                startActivity(intent);


                            } else {
                                Toast.makeText(LoginActivity.this, "Falha na autenticação.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });




        } else {
            emailEditText.setError("usuário e/ou senha incorreto(s)");
            senhaEditText.setError("usuário e/ou senha incorreto(s)");
        }
    }


}
