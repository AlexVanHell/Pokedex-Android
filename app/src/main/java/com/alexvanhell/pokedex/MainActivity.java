package com.alexvanhell.pokedex;

import android.util.Log;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alexvanhell.pokedex.models.Pokemon;
import com.alexvanhell.pokedex.models.PokemonResponse;
import com.alexvanhell.pokedex.pokeapi.PokeService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "POKEDEX";

    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private PokemonListAdapter pokemonListAdapter;
    private int offset;
    private boolean loadReady;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.item_pokemon);
        pokemonListAdapter = new PokemonListAdapter(this);
        recyclerView.setAdapter(pokemonListAdapter);
        recyclerView.setHasFixedSize(true);

        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0){
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (loadReady){
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG, " Llegamos al final.");

                            loadReady = false;
                            offset += 20;
                            getData(offset);
                        }
                    }

                }

            }

        });

        retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loadReady = true;
        offset = 0;
        getData(offset);

    }

    private void getData(int offset) {
        PokeService pokeService = retrofit.create(PokeService.class);
        Call<PokemonResponse> pokemonResponseCall = pokeService.getPokemonList(20, offset);

        pokemonResponseCall.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                loadReady = true;
                if (response.isSuccessful()){
                    PokemonResponse pokemonResponse = response.body();
                    ArrayList<Pokemon> pokemonList = pokemonResponse.getResults();

                    pokemonListAdapter.addPokemonList(pokemonList);
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                loadReady = true;
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

    }
}
