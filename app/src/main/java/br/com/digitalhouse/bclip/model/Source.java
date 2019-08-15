package br.com.digitalhouse.bclip.model;


import java.io.Serializable;

public class Source implements Serializable {

    private String name;


    public String getFontName() {
        return name;
    }

    public void setFontName(String fontName) {
        this.name = fontName;
    }
}
