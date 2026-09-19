package com.example;

public class Decrypt {
  private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
  private static int shiftBase;

  public Decrypt(int shift) {
    this.shiftBase = shift;
  }

  public String decrypt(String toDecrypt) {
    StringBuilder result = new StringBuilder();
    for(int i = 0; i < toDecrypt.length(); i++) {
      char c = toDecrypt.charAt(i);
      if (c == ' ') {
        result.append(c);
      } else {
        int pos = ALPHABET.indexOf(c);
        int shift = pos - shiftBase;
        result.append(ALPHABET.charAt(shift < 0 ? ALPHABET.length() + shift : shift));
      }
    }
    return result.toString();
  }
}
