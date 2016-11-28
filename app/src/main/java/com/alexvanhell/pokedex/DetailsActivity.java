package com.alexvanhell.pokedex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Retrofit;

public class DetailsActivity extends AppCompatActivity {

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String pokemonNumber = getIntent().getStringExtra("pokemon_number");
        String pokemonName = getIntent().getStringExtra("pokemon_name");

        setTitle(pokemonNumber + " - " + pokemonName);

        TextView poke_number = (TextView) findViewById(R.id.pokemon_detail_number);
        poke_number.setText(pokemonNumber);

    }
}
