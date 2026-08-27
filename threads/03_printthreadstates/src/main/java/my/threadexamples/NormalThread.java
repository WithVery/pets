package my.threadexamples;

import static java.lang.Thread.sleep;

public class NormalThread implements Runnable {

  @Override
  public void run() {
    System.out.println("Thread is in " + Thread.currentThread().getState() + " state");
    try {
      sleep(1000);
    } catch (Exception e) {
      Thread.currentThread().interrupt();
    }
  }
}
