package br.com.digitalhouse.bclip.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.interfaces.NoticiaListener;
import br.com.digitalhouse.bclip.model.NoticiaFromApi;

public class NoticiaAdapter extends RecyclerView.Adapter<NoticiaAdapter.ViewHolder> {

    private List<NoticiaFromApi> listaNoticias = new ArrayList<>();
    private NoticiaListener noticiaListerner;
    private FirebaseFirestore firebaseDb = FirebaseFirestore.getInstance();
    private FirebaseUser firebaseUser;


    private static final String TAG = "NoticiaFragment";



    public NoticiaAdapter(List<NoticiaFromApi> listaNoticias, NoticiaListener noticiaListener){
        this.listaNoticias = listaNoticias;
        this.noticiaListerner = noticiaListener;
    }

    public void atualizarNoticias(List<NoticiaFromApi> listaNoticias) {
        this.listaNoticias = listaNoticias;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.celula_noticia, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        final NoticiaFromApi noticia = listaNoticias.get(i);
        holder.bindNoticia(noticia);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noticiaListerner.onNoticiaClicado(noticia);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaNoticias.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagemNoticia;
        private TextView tituloNoticia;
        private TextView descricaoNoticia;
        private ImageView compartilharNoticiaButton;
        private ImageView salvarNoticiaButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imagemNoticia = itemView.findViewById(R.id.noticia_imagem_image_view);
            tituloNoticia = itemView.findViewById(R.id.noticia_titulo_text_view);
            descricaoNoticia = itemView.findViewById(R.id.noticia_fonte_text_view);
            compartilharNoticiaButton = itemView.findViewById(R.id.compartilhar_noticia_btn);
            salvarNoticiaButton = itemView.findViewById(R.id.salvar_noticia_btn);
        }

        public void bindNoticia(NoticiaFromApi noticiaFromApi) {
            tituloNoticia.setText((noticiaFromApi.getTitle()));
            descricaoNoticia.setText(noticiaFromApi.getSource().getName());
            Picasso.get().load(noticiaFromApi.getUrlToImage()).into(imagemNoticia);

            compartilharNoticiaButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    noticiaListerner.compartilharNoticia(noticiaFromApi);

                }
            });



            noticiaListerner.setupFavoritoButton(noticiaFromApi, salvarNoticiaButton);

            salvarNoticiaButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                     if (noticiaFromApi.isFavorito()) {
                         noticiaListerner.deletarFavorito(noticiaFromApi);
                         salvarNoticiaButton.setBackgroundResource(R.drawable.ic_favoritas_off);
                     } else {
                         noticiaListerner.salvarFavorito(noticiaFromApi, salvarNoticiaButton);
                         salvarNoticiaButton.setBackgroundResource((R.drawable.ic_favoritas_on));
                     }


                }
            });


        }






        }




    }

