package br.com.digitalhouse.bclip.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.model.Preferencia;
import br.com.digitalhouse.bclip.modules.CadastroEmpresa.viewmodel.CadastroEmpresaViewModel;
import br.com.digitalhouse.bclip.modules.Login.view.LoginActivity;
import br.com.digitalhouse.bclip.modules.Preferencias.viewmodel.PreferenciasViewModel;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        FirebaseAuth auth = FirebaseAuth.getInstance();


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (auth.getCurrentUser() != null) {
                    // User is signed in (getCurrentUser() will be null if not signed in)
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    irParaLogin();
                }
            }
        }, 3000);
    }

    private void irParaLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}
