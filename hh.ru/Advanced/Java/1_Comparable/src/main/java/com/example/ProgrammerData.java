package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProgrammerData {
  private List<String> skills;

  ProgrammerData(String string) {
    skills = new ArrayList<>(Arrays.asList(string.split(",")));
  }

  public int getSkillsCount() {
    return skills.size();
  }
}
