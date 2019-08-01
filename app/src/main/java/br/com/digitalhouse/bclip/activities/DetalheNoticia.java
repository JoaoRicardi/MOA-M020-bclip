package br.com.digitalhouse.bclip.activities;


import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.interfaces.NoticiaListener;
import br.com.digitalhouse.bclip.model.NoticiaFromApi;

public class DetalheNoticia extends AppCompatActivity {

    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_noticia);

        webView = findViewById(R.id.detalhe_noticia_web_view_id);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        NoticiaFromApi noticiaDetalhe = (NoticiaFromApi) bundle.getSerializable("NOTICIA");

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(noticiaDetalhe.getUrl());


    }
}
