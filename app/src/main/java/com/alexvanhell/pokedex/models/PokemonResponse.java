package com.alexvanhell.pokedex.models;


import java.util.ArrayList;

/**
 * Created by Alex on 19/11/2016.
 */
public class PokemonResponse {

    private ArrayList<Pokemon> results;

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }
}
