package com.example;

import java.util.*;
import java.util.concurrent.Callable;

public class LettersCounter implements Callable<Integer[]> {
  private final Spliterator<String> spltr;
  Integer[] freqs = new Integer[26];
  Random rnd = new Random();
  private int delay;

  public LettersCounter(Spliterator<String> spltr) {
    this.spltr = spltr;
    Arrays.fill(freqs, 0);
  }

  @Override
  public Integer[] call() throws Exception {
    spltr.forEachRemaining(s->countChars(s));
    return freqs;
  }

  private void countChars(String s) {
    s.chars().forEach(
      c-> freqs[c - (int)'A']++
    );
    try {
      delay = rnd.nextInt(1000);
      Thread.sleep(delay);
    } catch (InterruptedException e) {}
//    System.out.println(
//        String.format("%s[%03d]: %s", Thread.currentThread().getName(), delay, s)
//    );
  };

}
