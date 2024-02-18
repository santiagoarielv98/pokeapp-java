package com.svillanueva.controllers;

import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import com.svillanueva.pokeapp.pokeapi.client.PokeApiClient;
import com.svillanueva.pokeapp.pokeapi.dto.NamedAPIResourceList;
import com.svillanueva.pokeapp.pokeapi.dto.Pokemon;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class LazyPokemonDataModel extends LazyDataModel<Pokemon> {

  private NamedAPIResourceList pokemonList;
  private List<Pokemon> pokemons;
  @Inject
  private PokeApiClient pokeApiClient;

  @PostConstruct
  public void init() {
    pokemonList = pokeApiClient.getPokemonList(20, 0);
    pokemons = pokemonList.getResults().stream().map(p -> pokeApiClient.getPokemon(p.getName())).toList();
    this.setRowCount(pokemonList.getCount());
  }

  @Override
  public List<Pokemon> load(int first, int pageSize, Map<String, SortMeta> sortField,
      Map<String, FilterMeta> filterField) {

    pokemonList = pokeApiClient.getPokemonList(pageSize, first);
    pokemons = pokemonList.getResults().stream().map(p -> pokeApiClient.getPokemon(p.getName())).toList();
    this.setRowCount(pokemonList.getCount());

    return pokemons;
  }

  @Override
  public String getRowKey(Pokemon pokemon) {
    return pokemon.getName();
  }

  @Override
  public Pokemon getRowData(String rowKey) {
    for (Pokemon pokemon : pokemons) {
      if (pokemon.getName().equals(rowKey))
        return pokemon;
    }
    return null;
  }

  @Override
  public int count(Map<String, FilterMeta> filterBy) {
    return (int) pokemons.stream()
        .count();
  }

}
