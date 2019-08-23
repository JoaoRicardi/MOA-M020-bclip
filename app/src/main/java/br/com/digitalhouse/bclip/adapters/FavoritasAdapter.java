package br.com.digitalhouse.bclip.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.model.NoticiaFromApi;

public class FavoritasAdapter extends RecyclerView.Adapter<FavoritasAdapter.ViewHolder> {

    private List<NoticiaFromApi> listaNoticiasFavoritas = new ArrayList<>();


    public FavoritasAdapter(List<NoticiaFromApi> listaNoticiasFavoritas) {
        this.listaNoticiasFavoritas = listaNoticiasFavoritas;
    }

    @NonNull
    @Override
    public FavoritasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.celula_favorita, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritasAdapter.ViewHolder holder, int position) {
        final NoticiaFromApi noticiaFavorita = listaNoticiasFavoritas.get(position);
        holder.bindNoticiaFavorita(noticiaFavorita);

    }

    @Override
    public int getItemCount() {
        return listaNoticiasFavoritas.size();
    }

    public void atualizarFavoritas(List<NoticiaFromApi> listaNoticiasFavoritas){
        this.listaNoticiasFavoritas = listaNoticiasFavoritas;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView favoritaImagemImageView;
        private TextView favoritaTituloTextView;
        private TextView favoritaFonteTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            favoritaImagemImageView = itemView.findViewById(R.id.favorita_imagem_image_view);
            favoritaTituloTextView = itemView.findViewById(R.id.favorita_titulo_text_view);
            favoritaFonteTextView = itemView.findViewById(R.id.favorita_fonte_text_view);
        }

        public void bindNoticiaFavorita(NoticiaFromApi noticiaFromApi) {
            favoritaTituloTextView.setText((noticiaFromApi.getTitle()));
            favoritaFonteTextView.setText(noticiaFromApi.getSource().getName());
            Picasso.get().load(noticiaFromApi.getUrlToImage()).into(favoritaImagemImageView);


        }
    }



}














