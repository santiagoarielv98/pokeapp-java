package com.svillanueva.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.svillanueva.pokeapp.pokeapi.client.PokeApiClient;
import com.svillanueva.pokeapp.pokeapi.dto.NamedAPIResource;
import com.svillanueva.pokeapp.pokeapi.dto.Pokemon;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@ViewScoped
@Named
public class PokemonController implements Serializable {
  @Inject
  private PokeApiClient pokeApiClient;
  private String inputAutoComplete;
  private List<NamedAPIResource> pokemons;
  private HashMap<String, Pokemon> pokemonMap;

  // private LazyDataModel<Pokemon> lazyModel;

  @PostConstruct
  public void init() {
    // lazyModel = new LazyPokemonDataModel();
    pokemons = pokeApiClient.getPokemonList().getResults();
    pokemonMap = new HashMap<>();
  }

  public List<NamedAPIResource> getPokemons() {
    return pokemons;
  }

  public List<String> completeText(String query) {
    String queryLowerCase = query.toLowerCase();
    List<String> pokemonList = new ArrayList<>();
    for (NamedAPIResource pokemon : pokemons) {
      pokemonList.add(pokemon.getName());
    }

    return pokemonList.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
  }

  public String getInputAutoComplete() {
    return inputAutoComplete;
  }

  public void setInputAutoComplete(String inputAutoComplete) {
    this.inputAutoComplete = inputAutoComplete;
  }

  public Pokemon getPokemon(String name) {
    if (pokemonMap.containsKey(name)) {
      return pokemonMap.get(name);
    } else {
      System.out.println("getPokemon: " + name);
      Pokemon pokemon = pokeApiClient.getPokemon(name);
      pokemonMap.put(name, pokemon);
      return pokemon;
    }
  }

  public HashMap<String, Pokemon> getPokemonMap() {
    return pokemonMap;
  }
}
