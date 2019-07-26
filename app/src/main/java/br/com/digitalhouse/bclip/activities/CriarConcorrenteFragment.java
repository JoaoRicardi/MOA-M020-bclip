package br.com.digitalhouse.bclip.activities;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.interfaces.CriarConcorrenteListener;
import br.com.digitalhouse.bclip.model.Concorrente;

/**
 * A simple {@link Fragment} subclass.
 */
public class CriarConcorrenteFragment extends Fragment {

    private Button botaoCriarConcorrente;
    private EditText nomeConcorrente;
    CriarConcorrenteListener listener;


    public CriarConcorrenteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        listener = (CriarConcorrenteListener) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_criar_concorrente, container, false);

        nomeConcorrente = view.findViewById(R.id.fragment_nome_concorrente_edit_text);

        botaoCriarConcorrente = view.findViewById(R.id.fragment_concorrente_ok_id);
        botaoCriarConcorrente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criar(nomeConcorrente);
            }
        });

        return view;
    }

    private void criar(EditText nomeConcorrente) {
        Concorrente concorrente = new Concorrente();
        concorrente.setNomeConcorrente(nomeConcorrente.getEditableText().toString());
        listener.criarNovoConcorrente(concorrente);
    }

}
