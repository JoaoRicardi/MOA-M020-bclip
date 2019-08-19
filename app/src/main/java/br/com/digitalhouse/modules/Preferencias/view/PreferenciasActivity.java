package br.com.digitalhouse.modules.Preferencias.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.modules.CadastroEmpresa.view.PreferenciaEmpresaActivity;
import br.com.digitalhouse.bclip.adapters.PreferenciasAdapter;
import br.com.digitalhouse.bclip.model.Preferencia;
import br.com.digitalhouse.modules.Preferencias.viewmodel.PreferenciasViewModel;

public class PreferenciasActivity extends AppCompatActivity implements PreferenciasAdapter.OnPreferenciaListener {


    private RecyclerView recyclerView;

      private Button btnSalvarPreferencias;

      private PreferenciasViewModel listaPreferenciasViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_preferecias);

        List<Preferencia> listaPreferencia = getListaPreferencias();

//        recyclerView = findViewById(R.id.recycler_preferencias);





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

        Intent intent = new Intent(this, PreferenciaEmpresaActivity.class);

        startActivity(intent);

    }

    private List<Preferencia> getListaPreferencias() {

        List<Preferencia> preferenciaList = new ArrayList<>();

        Preferencia preferencia1 = new Preferencia("Tecnologia", false);
        preferenciaList.add(preferencia1);

        Preferencia preferencia2 = new Preferencia("Mobilidade Urbana", false);
        preferenciaList.add(preferencia2);

        Preferencia preferencia3 = new Preferencia("Construção Civil", false);
        preferenciaList.add(preferencia3);

        Preferencia preferencia4 = new Preferencia("Moda", true);
        preferenciaList.add(preferencia4);

        Preferencia preferencia5 = new Preferencia("Varejo", true);
        preferenciaList.add(preferencia5);

        Preferencia preferencia6 = new Preferencia("Markeing", false);
        preferenciaList.add(preferencia6);

        Preferencia preferencia7 = new Preferencia("Esporte", true);
        preferenciaList.add(preferencia7);

        Preferencia preferencia8 = new Preferencia("Alimentação", true);
        preferenciaList.add(preferencia8);

        Preferencia preferencia9 = new Preferencia("Industria", false);
        preferenciaList.add(preferencia9);

        return preferenciaList;

    }

    @Override
    public void onPreferenciaClick(int position) {

        Toast.makeText(this, "A opção  " + getListaPreferencias().get(position).getPreferencia() + " foi selecionada!", Toast.LENGTH_SHORT).show();


    }
}

