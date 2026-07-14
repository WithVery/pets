package group.shawarma.domain.interactor;

import group.shawarma.domain.model.Order;
import group.shawarma.domain.model.OrderStatus;
import group.shawarma.domain.model.User;
import group.shawarma.domain.repo.OrderRepo;

import java.util.List;

public class OrderInteractor {
    private final OrderRepo orderRepo;

    public OrderInteractor(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public Order createOrder(Order order) {
        return orderRepo.saveOrder(order);
    }

    public Order changeOrder(Order order) {
        return orderRepo.updateOrder(order);
    }

    public List<Order> getOrdersByUser(User user) {
        return orderRepo.getOrdersByUser(user);
    }

    public List<Order> getOrdersByStatus(OrderStatus orderStatus) {
        return orderRepo.getOrdersByStatus(orderStatus);
    }


}
