package my.threadexamples;

public class Warehouse {
  public static final Integer CAPACITY = 3;
  public static final Integer DELAY = 200;

  private int amount = 0;

  public void consume() {
    synchronized(this) {
      while (amount < 1) {
        System.out.println("Warehouse is empty. Waiting...");
        try {
          wait();
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          break;
        }
      }
      amount--;
      System.out.printf("Bought. %d left.\n", amount);
      notify();
    }
    sleepForAWhile();
  }

  public void supply() {
    synchronized(this) {
      while (amount >= CAPACITY) {
      System.out.println("Warehouse is full. Waiting...");
        try {
          wait();
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          break;
        }
      }
      amount++;
      System.out.printf("Supplied. %d in stock.\n", amount);
      notify();
    }
    sleepForAWhile();
  }

  private static void sleepForAWhile() {
    try {
      long delay = (long)(Math.random() * DELAY);
      Thread.sleep(delay);
    } catch (Exception e) {}
  }
}
