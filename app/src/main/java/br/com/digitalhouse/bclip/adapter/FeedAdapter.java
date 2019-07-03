package br.com.digitalhouse.bclip.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.digitalhouse.bclip.R;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.celula_feed,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.titulo.setText("Padarias");
        viewHolder.noticia.setText("Setor inova a cada ano, habito de se alimentar cresce entre os brasileiros");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagem;
        private TextView titulo;
        private TextView noticia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            imagem = itemView.findViewById(R.id.imagem_feed);
            titulo = itemView.findViewById(R.id.titulo_text_view);
            noticia =itemView.findViewById(R.id.noticias_text_view);

        }

    }
}
