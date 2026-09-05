package com.example.model;

public class DoubleCheckedLockingSingleton {
  private static volatile DoubleCheckedLockingSingleton INSTANCE;
  private String something;

  private DoubleCheckedLockingSingleton() {
    something = "something";
  }

  public static DoubleCheckedLockingSingleton getInstance() {
    if(INSTANCE == null) {
      synchronized (DoubleCheckedLockingSingleton.class) {
        INSTANCE = new DoubleCheckedLockingSingleton();
      }
    }
    return INSTANCE;
  }

  public String saySomething() {
    return something;
  }
}
