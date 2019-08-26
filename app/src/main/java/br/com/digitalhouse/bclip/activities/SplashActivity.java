package br.com.digitalhouse.bclip.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

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

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                irParaLogin();
            }
        },3000);


        PreferenciasViewModel viewModel = ViewModelProviders.of(this).get(PreferenciasViewModel.class);

        Preferencia preferencia1 = new Preferencia(01,"business", false);
        Preferencia preferencia2 = new Preferencia(02,"technology", false);
        Preferencia preferencia3 = new Preferencia(03,"health", false);
        Preferencia preferencia4 = new Preferencia(04,"science", true);
        Preferencia preferencia5 = new Preferencia(05,"sports", false);
        Preferencia preferencia6 = new Preferencia(06,"entertainment", false);
        Preferencia preferencia7 = new Preferencia(07,"general", true);

        viewModel.inserirPreferencias(preferencia1);
        viewModel.inserirPreferencias(preferencia2);
        viewModel.inserirPreferencias(preferencia3);
        viewModel.inserirPreferencias(preferencia4);
        viewModel.inserirPreferencias(preferencia5);
        viewModel.inserirPreferencias(preferencia6);
        viewModel.inserirPreferencias(preferencia7);

    }

    private void irParaLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}
