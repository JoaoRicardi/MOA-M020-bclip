package br.com.digitalhouse.bclip.interfaces;


import br.com.digitalhouse.bclip.model.Noticia;
import br.com.digitalhouse.bclip.model.NoticiaFromApi;

public interface NoticiaListener {

    void onNoticiaClicado(NoticiaFromApi noticia);
}
