package com.example.model;

public enum EnumSingleton {
  INSTANCE("something");

  private final String something;
  EnumSingleton(String something) {
    this.something = something;
  }

  public String saySomething() {
    return something;
  }

}
