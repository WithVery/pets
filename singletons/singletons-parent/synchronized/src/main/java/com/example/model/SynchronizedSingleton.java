package com.example.model;

import java.net.InetSocketAddress;

public class SynchronizedSingleton {
  private static SynchronizedSingleton INSTANCE;
  private String something;

  private SynchronizedSingleton() { something = "something"; }

  public static synchronized SynchronizedSingleton getInstance() {
    if(INSTANCE == null) {
      INSTANCE = new SynchronizedSingleton();
    }
    return INSTANCE;
  }

  public String saySomething() {
    return something;
  }
}
