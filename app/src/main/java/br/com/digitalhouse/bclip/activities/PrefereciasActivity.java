package br.com.digitalhouse.bclip.activities;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.adapters.PreferenciasAdapter;
import br.com.digitalhouse.bclip.model.Preferencia;

public class PrefereciasActivity extends AppCompatActivity implements PreferenciasAdapter.OnPreferenciaListener {


    private RecyclerView recyclerView;

      private Button btnSalvarPreferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_preferecias);

        List<Preferencia> listaPreferencia = getListaPreferencias();

        recyclerView = findViewById(R.id.recycler_preferencias);


        PreferenciasAdapter preferenciasAdapter = new PreferenciasAdapter(listaPreferencia, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        RecyclerView recyclerView = findViewById(R.id.recycler_preferencias);

        recyclerView.setAdapter(preferenciasAdapter);
        recyclerView.setLayoutManager(layoutManager);


        btnSalvarPreferencias = findViewById(R.id.button_salva_preferencias);

        btnSalvarPreferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                botaoClicado();
            }
        });


    }

    private void botaoClicado() {

        Intent intent = new Intent(this, FeedActivity.class);

        startActivity(intent);

    }

    private List<Preferencia> getListaPreferencias() {

        List<Preferencia> preferenciaList = new ArrayList<>();

        Preferencia preferencia1 = new Preferencia("Tecnologia", true);
        preferenciaList.add(preferencia1);

        Preferencia preferencia2 = new Preferencia("Ciencia", true);
        preferenciaList.add(preferencia2);

        Preferencia preferencia3 = new Preferencia("Industria", false);
        preferenciaList.add(preferencia3);

        return preferenciaList;

    }

    @Override
    public void onPreferenciaClick(int position) {

        Toast.makeText(this, "A opção  " + getListaPreferencias().get(position).getPreferencia() + " foi selecionada!", Toast.LENGTH_SHORT).show();


    }
}

