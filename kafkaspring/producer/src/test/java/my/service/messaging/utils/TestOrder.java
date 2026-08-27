package my.service.messaging.utils;

import my.service.messaging.event.OrderSendEvent;

public class TestOrder {
  public static OrderSendEvent getOrderSendEvent() {
    return new OrderSendEvent(
        "something",
        "00001"
    );
  }

}
