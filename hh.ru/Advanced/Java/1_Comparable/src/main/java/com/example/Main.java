package com.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
  static void main() {

    ArrayList<String> lines = new ArrayList<>();
    File file = new File("data.txt");
    try(Scanner scanner = new Scanner(file)) {
      while(scanner.hasNextLine()) {
        lines.add(scanner.nextLine());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    List<String> results = processInputLines(lines);

    results.forEach(System.out::println);
  }

  public static List<String> processInputLines(List<String> inputLines) {
    ArrayList<Programmer> programmers = new ArrayList<>();
    for(String string:inputLines) {
      String[] data = string.trim().split("::");
      if(data.length < 3) {
        System.out.println("Incorrect input.");
        return null;
      }

      programmers.add(new Programmer(data[0], Integer.valueOf(data[1]), data[2]));
    }

    programmers.sort(Comparator.reverseOrder());
    return programmers.stream().map(Programmer::toString).toList();
  }
}
