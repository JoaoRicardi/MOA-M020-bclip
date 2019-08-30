package br.com.digitalhouse.bclip.modules.Login.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.constantes.Constantes;
import br.com.digitalhouse.bclip.modules.CadastroEmpresa.view.CadastroEmpresaActivity;
import br.com.digitalhouse.bclip.modules.CadastroUsuário.view.CadastroActivity;
import br.com.digitalhouse.bclip.activities.HomeActivity;
import br.com.digitalhouse.bclip.activities.RecuperarSenhaActivity;
import br.com.digitalhouse.bclip.modules.Login.viewmodel.LoginViewModel;
import br.com.digitalhouse.bclip.modules.Preferencias.view.PreferenciasActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private TextInputEditText emailEditText;
    private TextInputEditText senhaEditText;
    private Button loginButton;
    private TextView esqueceuTextview;
    private TextView novaContaTextview;

    private LoginViewModel loginViewModel;

    private SignInButton signInButtonGoogle;
    GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN = 1;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.email_login_edit_text);
        senhaEditText = findViewById(R.id.senha_login_edit_text);

        loginButton = findViewById(R.id.btn_login);
        esqueceuTextview = findViewById(R.id.btn_esqueceu);
        novaContaTextview = findViewById(R.id.btn_registro);
        signInButtonGoogle = findViewById(R.id.sign_in_button_google_id);

        SharedPreferences sharedPreferences = getSharedPreferences(Constantes.SHARED_PREFERENCES, Context.MODE_PRIVATE);

        if(sharedPreferences.contains(Constantes.EMAIL)){
            emailEditText.setText(sharedPreferences.getString(Constantes.EMAIL, ""));
        }

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

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.getAutenticadoLiveData()
                .observe(this, autenticado -> {
                    if (autenticado){
                        logar();
                    } else {
                        Toast.makeText(this, "Falha na autenticação!", Toast.LENGTH_SHORT).show();
                    }
                });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logar();
            }
        });

        esqueceuTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarSenha();
            }
        });

        novaContaTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irParaCadastro();
            }
        });
    }

    private void logar() {


        String email = emailEditText.getText().toString();
        String senha = senhaEditText.getText().toString();

        emailEditText.setError(null);
        emailEditText.setError(null);

        SharedPreferences sharedPreferences = getSharedPreferences("BCLIPAPP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("EMAIL", email);
        editor.commit();

        loginViewModel.autenticarUsuario(email, senha);
    }

    private void irParaPreferencias() {
        Intent intent = new Intent(this, PreferenciasActivity.class);
        startActivity(intent);
    }

    private void irParaCadastro() {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);

    }

    private void recuperarSenha() {
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

                Intent intent = new Intent(LoginActivity.this, PreferenciasActivity.class);
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
}







