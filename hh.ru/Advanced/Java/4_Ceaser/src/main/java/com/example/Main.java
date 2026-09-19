package com.example;

public class Main {
  static void main() {

    int shift = 7;
    String toDecrypt = "дщх ёзтхсх счжшфхл";
    System.out.println(new Decrypt(shift).decrypt(toDecrypt));

  }
}
