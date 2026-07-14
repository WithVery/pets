package group.shawarma.domain.repo;

import group.shawarma.domain.model.Order;
import group.shawarma.domain.model.OrderStatus;
import group.shawarma.domain.model.User;

import java.util.List;

public interface OrderRepo {
    Order saveOrder(Order order);
    Order updateOrder(Order order);
//    Order updateStatus(Order order);
    List<Order> getOrdersByUser(User user);
    List<Order> getOrdersByStatus(OrderStatus orderStatus);

}
