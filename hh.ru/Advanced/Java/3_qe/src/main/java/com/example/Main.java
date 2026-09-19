package com.example;

import java.io.File;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.*;

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

    if(results != null) {
      results.forEach(System.out::println);
    }
  }

  public static List<String> processInputLines(List<String> inputLines) {

    ArrayList<Product> data = new ArrayList<>();
    String[] settings = inputLines.get(0).split(" ");
    String field = settings[0];
    boolean asc = settings[1].equals("asc");

    for(int i = 1; i < inputLines.size(); i++) {
      String[] product = inputLines.get(i).split(";");
      if(product.length < 5) {
        return null;
      }

      data.add(new Product(product[0], Integer.parseInt(product[1]), Integer.parseInt(product[2]), product[3],
          LocalDate.parse(product[4])));
    }

    Comparator<Product> comparator = new QueriableExtension<>(field, asc);
    return data.stream()
        .sorted(comparator)
        .map(Product::toString)
        .toList();
  }
}

class QueriableExtension<T> implements Comparator<T> {

  private final String fieldToSort;
  private final boolean sortAsc;

  public QueriableExtension(String fieldToSort, boolean sortAsc) {
    this.fieldToSort = fieldToSort;
    this.sortAsc = sortAsc;
  }

  @Override
  public int compare(T o1, T o2) {
    try {
      int result = compareField(o1, o2, fieldToSort, sortAsc);
      if (result != 0) return result;

      return compareField(o1, o2, "name", true);

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private int compareField(T o1, T o2, String aField, boolean sortAsc) throws Exception {
    Class<?> aClass = o1.getClass();
    Field field = aClass.getDeclaredField(aField.toLowerCase());
    field.setAccessible(true);

    Comparable val1 = (Comparable) field.get(o1);
    Comparable val2 = (Comparable) field.get(o2);

    int sortCoeff  = sortAsc ? 1 : -1;

    if(val1 == null && val2 == null) return 0;
    if(val1 == null) return -1 * sortCoeff;
    if(val2 == null) return 1 * sortCoeff;
    return val1.compareTo(val2);
  }
}
