package br.com.digitalhouse.bclip.model;

import java.io.Serializable;

public class Source implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
