package my.threadexamples;

public class Consumer implements Runnable {

  private Warehouse warehouse;

  public Consumer(Warehouse warehouse) {
    this.warehouse = warehouse;
  }

  @Override
  public void run() {
    for(int i = 0; i < Config.LINE; i++) {
      warehouse.consume();
    }
  }
}
