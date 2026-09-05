package com.example.model;

public class SimpleSingleton {
  private static final SimpleSingleton INSTANCE = new SimpleSingleton();
  private static final String something = "something";

  private SimpleSingleton() {}

  public SimpleSingleton getInstance() {
    return INSTANCE;
  }

  public static String saySomething() {
    return something;
  }
}
