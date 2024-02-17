package com.svillanueva.pokeapp.pokeapi.client;

import com.google.gson.Gson;
import com.svillanueva.pokeapp.pokeapi.dto.NamedAPIResourceList;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
public class PokeApiClient {
  private Client client;
  private WebTarget webTarget;
  private Gson gson;

  @PostConstruct
  public void init() {
    client = ClientBuilder.newClient();
    webTarget = client.target("https://pokeapi.co/api/v2");
    gson = new Gson();
  }

  @PreDestroy
  public void destroy() {
    client.close();
  }

  public <T> T get(String path, Class<T> responseType) {
    return gson.fromJson(webTarget
        .path(path)
        .queryParam("limit", 1000)
        .request(MediaType.APPLICATION_JSON)
        .get(String.class),
        responseType);
  }

  public NamedAPIResourceList getPokemonList() {
    return get("/pokemon", NamedAPIResourceList.class);
  }

}