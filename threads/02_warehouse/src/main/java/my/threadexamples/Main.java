package my.threadexamples;

public class Main {

  static void main(String[] args) throws Exception {
    Warehouse w = new Warehouse();
    Thread getter = new Thread(new Consumer(w));
    Thread putter = new Thread(new Supplier(w));

    getter.start();
    putter.start();

    getter.join();
    putter.join();
  }
}
