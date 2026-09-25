package com.example;

import java.util.*;
import java.util.concurrent.FutureTask;

public class Main {
  static void main() {

    int LENGTH = 200;
    List<String> list = new ArrayList<>();
    StringBuilder builder = new StringBuilder();
    Random random = new Random();
    for(int i = 0; i < 10; i++) {
      int len  = 10 + random.nextInt(LENGTH);
      builder.setLength(len);
      for(int j = 0; j < len; j++) {
        builder.setCharAt(j, (char) (random.nextInt(26) + 'A'));
      }
      list.add(builder.toString());
    }

//    list.forEach(System.out::println);

    Spliterator<String> splitr1 = list.spliterator();
    Spliterator<String> splitr2 = splitr1.trySplit();


    var result1 = new FutureTask<Integer[]>(new LettersCounter(splitr1));
    var result2 = new FutureTask<Integer[]>(new LettersCounter(splitr2));

    var t1 = new Thread(result1);
    var t2 = new Thread(result2);
    t2.start();
    t1.start();

    try {
      Integer[] freqs1 = result1.get();
      Integer[] freqs2 = result2.get();
      if(freqs1.length != freqs2.length) throw new Exception("Calculation failed");
      int total = 0;
      for(int i = 0; i < freqs1.length; i++) {
        total += freqs1[i] + freqs2[i];
      }

      System.out.println("Frequencies:");
      for(int i = 0; i < freqs1.length; i++) {
        System.out.println("" + (char)((int)'A' + i) + ": " + String.format("%.3f", 1.* (freqs1[i] + freqs2[i])  / total));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
