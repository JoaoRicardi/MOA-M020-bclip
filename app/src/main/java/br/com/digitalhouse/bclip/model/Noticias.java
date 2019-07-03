package br.com.digitalhouse.bclip.model;

import android.widget.ImageView;
import android.widget.TextView;

public class Noticias {

    private ImageView imagem;
    private TextView titulo;
    private TextView noticia;

    public Noticias(ImageView imagem, TextView titulo, TextView noticia) {
        this.imagem = imagem;
        this.titulo = titulo;
        this.noticia = noticia;
    }

    public ImageView getImagem() {
        return imagem;
    }

    public void setImagem(ImageView imagem) {
        this.imagem = imagem;
    }

    public TextView getTitulo() {
        return titulo;
    }

    public void setTitulo(TextView titulo) {
        this.titulo = titulo;
    }

    public TextView getNoticia() {
        return noticia;
    }

    public void setNoticia(TextView noticia) {
        this.noticia = noticia;
    }
}
