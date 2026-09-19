package com.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  static void main() {
    ArrayList<String> lines = new ArrayList<>();
    String range = "";
    File file = new File("data.txt");
    try(Scanner scanner = new Scanner(file)) {
      range = scanner.nextLine();
      while(scanner.hasNextLine()) {
        lines.add(scanner.nextLine());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    List<String> result = SeriesAnalyse.processingInputLines(range, lines);
    for(String string: result) {
      System.out.println(string.replace(",", "."));
    }
  }
}
