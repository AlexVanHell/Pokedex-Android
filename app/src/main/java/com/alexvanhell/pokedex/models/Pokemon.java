package com.alexvanhell.pokedex.models;

/**
 * Created by Alex on 19/11/2016.
 */
public class Pokemon {
    private int number;
    private String name;
    private String url;

    public int getNumber() {
        String[] urlParts = url.split("/");
        number = Integer.parseInt(urlParts[urlParts.length - 1]);
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
