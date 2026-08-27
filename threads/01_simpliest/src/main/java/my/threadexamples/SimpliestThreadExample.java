package my.threadexamples;

public class SimpliestThreadExample {
  private final Object locker = new Object();
  private boolean ready = false;

  void waiter() throws InterruptedException {
    synchronized(locker) {
      while (!ready) {
          locker.wait();
      }
      System.out.println("Thread is runnin now.");
    }
  }

  void notifier() {
    synchronized(locker) {
        ready = true;
        locker.notify();
    }
  }

  static void main(String[] args) throws Exception {
    SimpliestThreadExample ste = new SimpliestThreadExample();
    Thread t = new Thread(() -> {
        try {
          System.out.println("Set thread to wait.");
          ste.waiter();
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
    });

    t.start();
    Thread.sleep(100);
    ste.notifier();
    t.join();
    System.out.println("Closing everything.");
  }
}
