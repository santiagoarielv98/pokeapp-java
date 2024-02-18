package com.svillanueva.pokeapp.pokeapi.dto;

public class Pokemon {
  private int id;
  private String name;
  private PokemonSprites sprites;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Pokemon [id=" + id + ", name=" + name + "]";
  }

  public PokemonSprites getSprites() {
    return sprites;
  }

  public void setSprites(PokemonSprites sprites) {
    this.sprites = sprites;
  }

}
