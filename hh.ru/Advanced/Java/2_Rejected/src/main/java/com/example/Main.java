package com.example;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
  private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

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

    Map<Integer, ClientData> data = new HashMap<Integer, ClientData>();
    String[] settings = inputLines.get(0).split(" ");
    int month = Integer.valueOf(settings[0]);
    int nclients = Integer.valueOf(settings[1]);

    for(int i = 1; i < inputLines.size(); i++) {
      String[] orderData = inputLines.get(i).split(":");
      if(orderData.length < 5) {
        return null;
      }
      if(!orderData[2].equals("rejected")) {
        continue;
      }

      LocalDate date = LocalDate.parse(orderData[4], formatter);
      if(date.getMonth().getValue() != month) {
        continue;
      }

      Integer clientId = Integer.valueOf(orderData[0]);
      ClientData clientData = data.get(clientId);
      if(clientData == null) {
        clientData = new ClientData(clientId);
        data.put(clientId, clientData);
      }
      clientData.addOrder(new Order(Integer.parseInt(orderData[1]), Integer.parseInt(orderData[3])));
    }

    List<String> result = data.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .map(entry -> entry.getValue().toString())
        .toList();

    if(result.size() < nclients) {
      return Arrays.stream(new String[] {"none"}).toList();
    } else {
      return result;
    }
  }

}
