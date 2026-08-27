package my.threadexamples;

public class WaitingThread implements Runnable {
  private static Object locker = new Object();

  public static Object getLocker() {
    return locker;
  }

  @Override
  public void run() {
    try {
      synchronized (locker) {
        locker.wait();
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
