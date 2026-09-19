package com.example;

public class Order {
  private Integer id;
  private int sum;

  public Integer getId() {
    return id;
  }

  public int getSum() {
    return sum;
  }

public Order(Integer id, int sum) {
    this.id = id;
    this.sum = sum;
  }
}
