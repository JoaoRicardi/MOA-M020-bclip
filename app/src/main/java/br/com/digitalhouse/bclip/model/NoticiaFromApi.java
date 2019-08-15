package br.com.digitalhouse.bclip.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NoticiaFromApi implements Serializable {

    private String title;

    @SerializedName("source")
    private Source name;
    private String urlToImage;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Source getName() {
        return name;
    }

    public void setName(Source name) {
        this.name = name;
    }
}
