package br.com.digitalhouse.bclip.model;

import java.util.List;

public class NoticiaFromApiResponse {

    private List<NoticiaFromApi> articles;
    private Source name;

    public Source getName() {
        return name;
    }

    public void setName(Source name) {
        this.name = name;
    }

    public List<NoticiaFromApi> getArticles() {
        return articles;
    }

    public void setArticles(List<NoticiaFromApi> articles) {
        this.articles = articles;
    }
}
