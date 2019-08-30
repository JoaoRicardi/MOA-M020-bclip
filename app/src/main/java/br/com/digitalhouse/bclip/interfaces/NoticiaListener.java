package br.com.digitalhouse.bclip.interfaces;


import android.widget.ImageView;

import br.com.digitalhouse.bclip.model.NoticiaFromApi;

public interface NoticiaListener {

    void onNoticiaClicado(NoticiaFromApi noticia);
    void salvarFavorito(NoticiaFromApi noticia, ImageView salvarNoticiaButton);
    void compartilharNoticia(NoticiaFromApi noticia);
    void setupFavoritoButton(NoticiaFromApi noticia, ImageView salvarNoticiaButton);
    void deletarFavorito(NoticiaFromApi noticia);
}
