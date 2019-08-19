package br.com.digitalhouse.modules.CadastroEmpresa.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.activities.HomeActivity;
import br.com.digitalhouse.bclip.adapters.PreferenciasEmpresasAdapter;
import br.com.digitalhouse.bclip.interfaces.OnPreferenciaEmpresaListener;
import br.com.digitalhouse.bclip.model.PreferenciaEmpresas;
import br.com.digitalhouse.modules.CadastroEmpresa.viewmodel.PreferenciaEmpresaViewModel;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PreferenciaEmpresaActivity extends AppCompatActivity
        implements OnPreferenciaEmpresaListener {

    private RecyclerView recyclerView;
    private Button buttonNovoConcorrente;
    private Button buttonOkCadastro;
    private PreferenciaEmpresaViewModel viewModel;
    private PreferenciasEmpresasAdapter preferenciasEmpresasAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencia_empresa);

        buttonNovoConcorrente = findViewById(R.id.btn_novo_concorrente);
        buttonOkCadastro = findViewById(R.id.btn_ok_cadastro);

        viewModel = ViewModelProviders.of(this).get(PreferenciaEmpresaViewModel.class);
        viewModel.getListaPreferenciaEmpresas();

        viewModel.getListPreferenciasEmpresa()
                .observe(this, listpreferenciaEmpresas -> {
                    preferenciasEmpresasAdapter.atualizarPreferenciaEmpresa(listpreferenciaEmpresas);
                });


        preferenciasEmpresasAdapter = new PreferenciasEmpresasAdapter( this);
        setupRecyclerView(); // verificar melhor ordem para gerar o recyclerview


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
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        viewModel.getListaPreferenciaEmpresas();
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

                viewModel.inserirPreferenciasEmpresa(preferenciaEmpresas);
//                viewModel.getListaPreferenciaEmpresas();

                preferenciaEmpresas.setAtivado(true);

                dialog.dismiss();

                viewModel.getListaPreferenciaEmpresas();
            }
        });

        viewModel.getListaPreferenciaEmpresas();
        dialog.show();
        viewModel.getListaPreferenciaEmpresas();
    }



    @Override
    public void deletarEmpresa(PreferenciaEmpresas preferenciaEmpresas) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("bClip");
        builder.setMessage("Deseja deletar a preferência " + preferenciaEmpresas.getPreferenciaEmpresa() + "?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Completable.fromAction(() -> viewModel.deletarPreferenciaEmpresa(preferenciaEmpresas))
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe();
                dialog.dismiss();
                viewModel.getListaPreferenciaEmpresas();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

      //  viewModel.getListaPreferenciaEmpresas();
        builder.create().show();


    }


    private void setupRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recycler_preferencia_empresas_id);
        recyclerView.setAdapter(preferenciasEmpresasAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

}