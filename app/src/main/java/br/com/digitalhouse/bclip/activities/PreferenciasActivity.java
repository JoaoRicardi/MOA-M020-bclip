package br.com.digitalhouse.bclip.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.adapter.PreferenciasAdapter;
import br.com.digitalhouse.bclip.interfaces.PreferenciaListener;
import br.com.digitalhouse.bclip.model.Preferencia;

public class PreferenciasActivity extends AppCompatActivity implements PreferenciaListener {

    private RecyclerView preferenciasRecyclerView;
    private Button salvarPreferenciasButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);

        List<Preferencia> listaDePreferencias = getListaPreferencias();

        preferenciasRecyclerView = findViewById(R.id.preferencias_recycler_id);

        PreferenciasAdapter preferenciasAdapter = new PreferenciasAdapter(listaDePreferencias, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        RecyclerView recyclerView = findViewById(R.id.preferencias_recycler_id);

        recyclerView.setAdapter(preferenciasAdapter);

        recyclerView.setLayoutManager(layoutManager);

        salvarPreferenciasButton = findViewById(R.id.preferencias_salvar_button_id);

        salvarPreferenciasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botaoClicado();

            }
        });
    }

    private void botaoClicado() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private List<Preferencia> getListaPreferencias(){
        List<Preferencia> preferenciasList = new ArrayList<>();

        Preferencia preferencia1 = new Preferencia("Tecnologia", false);
        preferenciasList.add(preferencia1);

        Preferencia preferencia2 = new Preferencia("Mobilidade Urbana", false);
        preferenciasList.add(preferencia2);

        Preferencia preferencia3 = new Preferencia("Construção Civil", false);
        preferenciasList.add(preferencia3);

        Preferencia preferencia4 = new Preferencia("Moda", false);
        preferenciasList.add(preferencia4);

        Preferencia preferencia5 = new Preferencia("Varejo", false);
        preferenciasList.add(preferencia5);

        Preferencia preferencia6 = new Preferencia("Marketing", false);
        preferenciasList.add(preferencia6);

        return preferenciasList;

    }

    @Override
    public void preferenciaClicado(int position) {

    }
}
