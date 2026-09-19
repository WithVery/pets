package com.example;

import java.util.ArrayList;

public class ClientData implements Comparable<ClientData> {
  private Integer id;
  private ArrayList<Order> orders = new ArrayList<>();

  public ClientData(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void addOrder(Order order) {
    orders.add(order);
  }

  public int getOrdersSum() {
    return orders.stream().mapToInt(Order::getSum).sum();
  }

  public int getOrdersCount() {
    return orders.size();
  }
  @Override
  public boolean equals(Object o) {
    if(this == o) return true;
    if(o == null || getClass() != o.getClass()) return false;

    ClientData cd = (ClientData) o;

    return (this.getId() == cd.getId());
  }

  @Override
  public String toString() {
    return String.join(":", getId().toString(), Integer.toString(getOrdersSum()), Integer.toString(getOrdersCount()));
  }

  @Override
  public int compareTo(ClientData o) {
    if(this.getOrdersSum() < o.getOrdersSum()) {
      return -1;
    } else if(this.getOrdersSum() > o.getOrdersSum()) {
      return 1;
    } else if(this.getOrdersCount() < o.getOrdersCount()) {
      return -1;
    } else if(this.getOrdersCount() > o.getOrdersCount()) {
      return 1;
    } else if(this.getId() < o.getId()) {
      return 1;
    } else if(this.getId() > o.getId()) {
      return -1;
    } else {
      return 0;
    }
  }
}
