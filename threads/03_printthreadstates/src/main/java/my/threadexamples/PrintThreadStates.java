package my.threadexamples;

public class PrintThreadStates {

  static void main(String[] args) throws Exception {
    Thread nt = new Thread(new NormalThread());
    Thread wt = new Thread(new WaitingThread());
    printThreadState(nt);
    nt.start();
    wt.start();
    Thread.sleep(500);
    printThreadState(wt);
    printThreadState(nt);

    Object locker = WaitingThread.getLocker();
    synchronized (locker) {
      locker.notify();
    }
    printThreadState(wt);

    nt.join();
    printThreadState(nt);
    wt.join();
  }

  private static void printThreadState(Thread thread) {
    System.out.println(new StringBuilder().append("Thread is in ").append(thread.getState()).append(" state"));
  }
}
