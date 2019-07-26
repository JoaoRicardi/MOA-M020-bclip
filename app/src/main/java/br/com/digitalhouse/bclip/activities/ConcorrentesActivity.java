package br.com.digitalhouse.bclip.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.adapter.ConcorrentesAdapter;
import br.com.digitalhouse.bclip.interfaces.CriarConcorrenteListener;
import br.com.digitalhouse.bclip.model.Concorrente;

public class ConcorrentesActivity extends AppCompatActivity implements CriarConcorrenteListener {


    private ConcorrentesAdapter adapter;
    private CriarConcorrenteFragment fragment;
    private Button buttonAdicionar;
    private Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concorrentes);

        RecyclerView recyclerView = findViewById(R.id.concorrentes_recycler_id);
        adapter = new ConcorrentesAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonAdicionar = findViewById(R.id.concorrentes_adicionar_button);

        buttonAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                fragment = new CriarConcorrenteFragment();
                transaction.replace(R.id.cardView2, fragment);
                transaction.commit();
            }
        });

        okButton = findViewById(R.id.concorrentes_ok_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irParaPreferenciasAcitivity();
            }
        });
    }

    private void irParaPreferenciasAcitivity() {
        Intent intent = new Intent(this, PreferenciasActivity.class);
        startActivity(intent);


    }


    @Override
    public void criarNovoConcorrente(Concorrente concorrente) {
        adapter.adicionarConcorrente(concorrente);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.remove(fragment);

        transaction.commit();

    }
}
