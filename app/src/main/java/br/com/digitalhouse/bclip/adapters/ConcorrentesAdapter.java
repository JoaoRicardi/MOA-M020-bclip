package br.com.digitalhouse.bclip.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.model.Concorrente;

public class ConcorrentesAdapter extends RecyclerView.Adapter<ConcorrentesAdapter.ViewHolder> {

    private List<Concorrente> listaConcorrentes;

    public ConcorrentesAdapter(List<Concorrente> listaConcorrentes) {
        this.listaConcorrentes = listaConcorrentes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.celula_concorrente, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return listaConcorrentes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextInputEditText nomeConcorrenteEditText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            nomeConcorrenteEditText = itemView.findViewById(R.id.nome_novo_concorrente_edit_text);

        }

        public void bindConcorrentes(Concorrente concorrente){
            nomeConcorrenteEditText.setText(concorrente.getNomeConcorrente());
        }
    }
}
