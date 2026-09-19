package com.example;

public class Programmer implements Comparable<Programmer> {
  private String name;
  private int expYears;
  private ProgrammerData skills;

  public int getExpYears() {
    return expYears;
  }

  public void setExpYears(int expYears) {
    this.expYears = expYears;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Programmer(String name, int expYears, String skills) {
    this.expYears = expYears;
    this.name = name;
    this.skills = new ProgrammerData(skills);
  }

  public int getSkillsCount() {
    return skills.getSkillsCount();
  }
  @Override
  public int compareTo(Programmer programmer) {
    if(expYears < programmer.getExpYears()) {
      return -1;
    } else if (expYears > programmer.getExpYears()) {
      return 1;
    } else if(skills.getSkillsCount() < programmer.getSkillsCount()) {
      return -1;
    } else if (skills.getSkillsCount() > programmer.getSkillsCount()) {
      return 1;
    } else {
      return 0;
    }
  }

  @Override
  public String toString() {
    return String.join("::", getName(), Integer.toString(getExpYears()), Integer.toString(getSkillsCount()));
  }
}
