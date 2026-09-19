package com.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SeriesAnalyse {
  private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy:HH:mm:ss");
  public static List<String> processingInputLines(String intervalLine, List<String> inputLines) {
    String[] s = intervalLine.split(";");
    LocalDateTime start = LocalDateTime.parse(s[0], formatter);
    LocalDateTime finish = LocalDateTime.parse(s[1], formatter);

    float max = Float.MIN_VALUE;
    float min = Float.MAX_VALUE;
    float total = 0;

    int count = 0;

    for(String string: inputLines) {
        String[] data = string.split(";");
        LocalDateTime moment = LocalDateTime.parse(data[0], formatter);

        if(moment.isBefore(start) || moment.isAfter(finish)) continue;
        float value = Float.parseFloat(data[1]);
        if(max < value) max = value;
        if(min > value) min = value;
        count++;
        total += value;
    }

    List<String> result = new ArrayList<>();
    if(count > 0) {
      result.add(String.format("%.3f", max));
      result.add(String.format("%.3f", min));
      result.add(String.format("%.3f", total / count));
    } else {
      result.add("none");
    }

    return result;
  }
}
