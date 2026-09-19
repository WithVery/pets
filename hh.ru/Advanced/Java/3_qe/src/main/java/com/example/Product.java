package com.example;

import java.time.LocalDate;

public class Product {
  private final String name;
  private final int price;
  private final int stock;
  private final String category;
  private final LocalDate releaseDate;

  public String getCategory() {
    return category;
  }

  public String getName() {
    return name;
  }

  public int getPrice() {
    return price;
  }

  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  public int getStock() {
    return stock;
  }

  public Product(String name, int price, int stock, String category, LocalDate releaseDate) {
    this.category = category;
    this.name = name;
    this.price = price;
    this.stock = stock;
    this.releaseDate = releaseDate;
  }

  @Override
  public String toString() {
    return String.join(";", name, Integer.toString(price), Integer.toString(stock), category, releaseDate.toString());
  }
}
