package com.svillanueva.controllers;

import java.util.ArrayList;
import java.util.List;

import com.svillanueva.pokeapp.pokeapi.client.PokeApiClient;
import com.svillanueva.pokeapp.pokeapi.dto.NamedAPIResource;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@ViewScoped
@Named
public class PokemonController {
  @Inject
  private PokeApiClient pokeApiClient;

  private List<NamedAPIResource> pokemons;

  @PostConstruct
  public void init() {
    pokemons = new ArrayList<>();
  }

  public List<NamedAPIResource> getPokemons() {
    if (pokemons.isEmpty()) {
      pokemons = pokeApiClient.getPokemonList().getResults();
    }
    return pokemons;
  }

}
