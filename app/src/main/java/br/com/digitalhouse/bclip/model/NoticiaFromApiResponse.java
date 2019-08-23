package br.com.digitalhouse.bclip.model;

import java.util.List;

public class NoticiaFromApiResponse {

    private List<NoticiaFromApi> articles;

    public List<NoticiaFromApi> getArticles() {
        return articles;
    }

    public void setArticles(List<NoticiaFromApi> articles) {
        this.articles = articles;
    }
}
