package my.threadexamples;

public class Supplier implements Runnable {
  private Warehouse warehouse;

  public Supplier(Warehouse warehouse) {
    this.warehouse = warehouse;
  }

  @Override
  public void run() {
    for(int i = 0; i < Config.LINE; i++) {
      warehouse.supply();
    }
  }

}
