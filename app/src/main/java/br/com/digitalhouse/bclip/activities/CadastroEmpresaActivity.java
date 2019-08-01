package br.com.digitalhouse.bclip.activities;

import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.concurrent.TimeUnit;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.adapters.PreferenciasEmpresasAdapter;
import br.com.digitalhouse.bclip.database.AppDatabase;
import br.com.digitalhouse.bclip.model.PreferenciaEmpresas;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CadastroEmpresaActivity extends AppCompatActivity
        implements PreferenciasEmpresasAdapter.OnPreferenciaEmpresaListener{

    private RecyclerView recyclerView;
    private AppDatabase db;

    private Button btnSalvarPreferencias;

    private Button buttonNovoConcorrente;
    private Button buttonOkCadastro;

    private PreferenciasEmpresasAdapter preferenciasEmpresasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_empresa);


        db = Room.databaseBuilder(this,
                AppDatabase.class, AppDatabase.DATABASE_NAME).build();



//        recyclerView = findViewById(R.id.recycler_preferencia_empresas_id);


        preferenciasEmpresasAdapter = new PreferenciasEmpresasAdapter( this);

        atualizarListaPreferenciaEmpresa();


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.recycler_preferencia_empresas_id);

        recyclerView.setAdapter(preferenciasEmpresasAdapter);
        recyclerView.setLayoutManager(layoutManager);

        buttonNovoConcorrente = findViewById(R.id.btn_novo_concorrente);
        buttonOkCadastro = findViewById(R.id.btn_ok_cadastro);




        buttonNovoConcorrente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                novoConcorrenteClicado();
            }
        });

        buttonOkCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botaoOkClicado();
            }
        });



    }

    private void botaoOkClicado() {


        atualizarListaPreferenciaEmpresa();
        Intent intent = new Intent(this, FeedActivity.class);
        startActivity(intent);

    }

    private void novoConcorrenteClicado() {

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_nova_preferencia);

        Button button = dialog.findViewById(R.id.ok_nova_preferencia_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenciaEmpresas preferenciaEmpresas = new PreferenciaEmpresas();

                TextInputEditText preferenciaEmpresaEditText = dialog.findViewById(R.id.preferencia_empresa_edit_text);
                String preferenciaDigitada = preferenciaEmpresaEditText.getEditableText().toString();
                preferenciaEmpresas.setPreferenciaEmpresa(preferenciaDigitada);


                preferenciaEmpresas.setAtivado(true);

                gravarPreferenciasEmpresa(preferenciaEmpresas);

                atualizarListaPreferenciaEmpresa();

                dialog.dismiss();
            }
        });

        dialog.show();

    }



    private void atualizarListaPreferenciaEmpresa() {
        db.preferenciaEmpresasDao()
                .getAll()
                .delaySubscription(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(preferenciaEmpresaList -> {
                    preferenciasEmpresasAdapter.atualizarPreferenciaEmpresa(preferenciaEmpresaList);
                }, throwable -> throwable.printStackTrace());

    }




    private void gravarPreferenciasEmpresa(PreferenciaEmpresas preferenciaEmpresas){
        Completable.fromAction(() -> db.preferenciaEmpresasDao().inserir(preferenciaEmpresas))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(() -> exibirPreferenciaEmpresa());
    }

    private void exibirPreferenciaEmpresa() {
        db.preferenciaEmpresasDao()
                .getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(preferenciaEmpresas -> preferenciasEmpresasAdapter.atualizarPreferenciaEmpresa(preferenciaEmpresas),
                        throwable -> throwable.printStackTrace());

    }


    @Override
    public void onPreferenciaLongClick(PreferenciaEmpresas preferenciaEmpresas) {
        Completable.fromAction(() -> db.preferenciaEmpresasDao().delete(preferenciaEmpresas))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(() -> exibirPreferenciaEmpresa());
    }


    private void recuperaPreferencias(){

        Flowable<List<PreferenciaEmpresas>> listaPref = db.preferenciaEmpresasDao().getAll();

        return;
    }
}