package br.com.digitalhouse.bclip.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.interfaces.NoticiaListener;
import br.com.digitalhouse.bclip.model.Noticia;
import br.com.digitalhouse.bclip.model.NoticiaFromApi;

public class NoticiaAdapter extends RecyclerView.Adapter<NoticiaAdapter.ViewHolder> {

    private List<NoticiaFromApi> listaNoticias = new ArrayList<>();
    private NoticiaListener noticiaListerner;


    public void atualizarNoticias(List<NoticiaFromApi> listaNoticias) {
        this.listaNoticias = listaNoticias;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.noticia_celula, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        final NoticiaFromApi noticia = listaNoticias.get(i);
        holder.bindNoticia(noticia);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                noticiaListerner.onNoticiaClicado(noticia);
////            }
////        });

    }

    @Override
    public int getItemCount() {
        return listaNoticias.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagemNoticia;
        private TextView tituloNoticia;
        private TextView descricaoNoticia;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imagemNoticia = itemView.findViewById(R.id.noticia_imagem_image_view);
            tituloNoticia = itemView.findViewById(R.id.noticia_titulo_text_view);
            descricaoNoticia = itemView.findViewById(R.id.noticia_descricao_text_view);
        }

        public void bindNoticia(NoticiaFromApi noticiaFromApi){
            tituloNoticia.setText((noticiaFromApi.getTitle()));
            descricaoNoticia.setText(noticiaFromApi.getDescription());
        }
    }
}
