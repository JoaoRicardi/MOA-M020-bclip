package br.com.digitalhouse.bclip.modules.Preferencias.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.adapters.PreferenciasAdapter;
import br.com.digitalhouse.bclip.adapters.PreferenciasEmpresasAdapter;
import br.com.digitalhouse.bclip.database.AppDatabasePreferencia;
import br.com.digitalhouse.bclip.interfaces.OnPreferenciaEmpresaListener;
import br.com.digitalhouse.bclip.interfaces.OnPreferenciaListener;
import br.com.digitalhouse.bclip.model.Preferencia;
import br.com.digitalhouse.bclip.modules.CadastroEmpresa.view.CadastroEmpresaActivity;
import br.com.digitalhouse.bclip.modules.CadastroEmpresa.viewmodel.CadastroEmpresaViewModel;
import br.com.digitalhouse.bclip.modules.Preferencias.viewmodel.PreferenciasViewModel;

public class PreferenciasActivity extends AppCompatActivity implements OnPreferenciaListener {


    private RecyclerView recyclerView;
    private Button btnSalvarPreferencias;
    private PreferenciasViewModel viewModel;
    private PreferenciasAdapter preferenciasAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_preferecias);


        viewModel = ViewModelProviders.of(this).get(PreferenciasViewModel.class);
        viewModel.getListaPreferencia();

        viewModel.getListaPreferenciaLiveData()
                .observe(this, listaPreferencia -> {
                    preferenciasAdapter.atualizarPreferencia(listaPreferencia);
                });



        preferenciasAdapter = new PreferenciasAdapter( this);
        setupRecyclerView(); // verificar melhor ordem para gerar o recyclerview


        btnSalvarPreferencias = findViewById(R.id.button_salva_preferencias);

        btnSalvarPreferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                botaoClicado();
            }
        });


    }

    private void setupRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recycler_preferencias);
        recyclerView.setAdapter(preferenciasAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void botaoClicado() {

        Intent intent = new Intent(this, CadastroEmpresaActivity.class);

        startActivity(intent);

    }

    @Override
    public void onPreferenciaClick(int position) {

    }


//    @Override
//    public void onPreferenciaClick(int position) {
//
//        Toast.makeText(this, "A opção  " + db.preferenciasDao() + " foi selecionada!", Toast.LENGTH_SHORT).show();
//
//
//    }
}

