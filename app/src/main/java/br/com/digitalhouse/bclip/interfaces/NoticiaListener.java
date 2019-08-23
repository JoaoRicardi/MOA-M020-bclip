package br.com.digitalhouse.bclip.interfaces;


import br.com.digitalhouse.bclip.model.NoticiaFromApi;

public interface NoticiaListener {

    void onNoticiaClicado(NoticiaFromApi noticia);
    void salvarFavoritosFirebase(NoticiaFromApi noticia);
}
