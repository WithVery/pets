package com.example.model;

public class LazySingleton {
  private static LazySingleton INSTANCE;
  private String something;

  private LazySingleton() {
    something = "something";
  }

  public static LazySingleton getInstance() {
    if(INSTANCE == null) {
      INSTANCE = new LazySingleton();
    }
    return INSTANCE;
  }

  public String saySomething() {
    return something;
  }
}
