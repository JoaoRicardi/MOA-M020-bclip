package br.com.digitalhouse.bclip.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.model.Concorrente;

public class ConcorrentesAdapter extends RecyclerView.Adapter<ConcorrentesAdapter.ViewHolder> {

    private List<Concorrente> listaConcorrentes = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_concorrente, parent, false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Concorrente concorrente = listaConcorrentes.get(i);
        holder.bind(concorrente);
    }

    @Override
    public int getItemCount() {
        return listaConcorrentes.size();
    }

    public void adicionarConcorrente(Concorrente concorrente){
        listaConcorrentes.add(concorrente);
        notifyItemChanged(getItemCount());
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        TextView nomeConcorrenteTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeConcorrenteTextView = itemView.findViewById(R.id.fragment_nome_concorrente);

        }

        public void bind (Concorrente concorrente){
            nomeConcorrenteTextView.setText(concorrente.getNomeConcorrente());
        }
    }









}
