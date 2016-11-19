package com.alexvanhell.pokedex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexvanhell.pokedex.models.Pokemon;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by Alex on 19/11/2016.
 */
public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> {

    private ArrayList<Pokemon> dataset;
    private Context context;

    public PokemonListAdapter(Context context){
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public PokemonListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_item , parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PokemonListAdapter.ViewHolder holder, int position) {
        Pokemon poke = dataset.get(position);
        holder.pokeNum.setText(String.valueOf(poke.getNumber()));
        holder.pokeName.setText(poke.getName());

        Glide.with(context)
                .load("http://pokeapi.co/media/sprites/pokemon/" + poke.getNumber() + ".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.pokeImg);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void addPokemonList(ArrayList<Pokemon> pokemonList) {
        dataset.addAll(pokemonList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView pokeImg;
        private TextView pokeNum;
        private TextView pokeName;

        public ViewHolder(View itemView) {
            super(itemView);

            pokeImg = (ImageView) itemView.findViewById(R.id.poke_img);
            pokeNum = (TextView) itemView.findViewById(R.id.poke_num);
            pokeName = (TextView) itemView.findViewById(R.id.poke_name);

        }
    }
}
