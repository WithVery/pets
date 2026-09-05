package com.example.model;

import javax.swing.*;

public class ClassHolderSingleton {
  private String something;
  private ClassHolderSingleton() {
    something = "something";
  }

  private static class SingletonHolder {
    public static final ClassHolderSingleton INSTANCE = new ClassHolderSingleton();
  }

  public static ClassHolderSingleton getInstance() {
    return SingletonHolder.INSTANCE;
  }

  public String saySomething() {
    return something;
  }
}
