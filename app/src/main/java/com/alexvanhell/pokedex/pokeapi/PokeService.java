package com.alexvanhell.pokedex.pokeapi;

import com.alexvanhell.pokedex.models.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Alex on 19/11/2016.
 */
public interface PokeService {

    @GET("pokemon")
    Call<PokemonResponse> getPokemonList(@Query("limit") int limit, @Query("offset") int offset);

}
