package com.svillanueva.pokeapp.pokeapi.dto;

import java.util.List;

public class NamedAPIResourceList {
  private Integer count;
  private String next;
  private String previous;
  private List<NamedAPIResource> results;

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public String getNext() {
    return next;
  }

  public void setNext(String next) {
    this.next = next;
  }

  public String getPrevious() {
    return previous;
  }

  public void setPrevious(String previous) {
    this.previous = previous;
  }

  public List<NamedAPIResource> getResults() {
    return results;
  }

  public void setResults(List<NamedAPIResource> results) {
    this.results = results;
  }
}