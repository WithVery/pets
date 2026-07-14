package group.shawarma.data.repoImpls.collectionFrw;

import group.shawarma.domain.model.Order;
import group.shawarma.domain.model.OrderStatus;
import group.shawarma.domain.model.User;
import group.shawarma.domain.repo.OrderRepo;

import java.util.ArrayList;
import java.util.List;

public class OrderRepoImpl implements OrderRepo {

    private final List<Order> orders = new ArrayList<>();

    @Override
    public Order saveOrder(Order order) {
        orders.add(order);
        return order;
    }

    @Override
    public Order updateOrder(Order order) {
        int index = orders.indexOf(order);
        if(index != -1) orders.set(index, order);
        return order;
    }

//    @Override
//    public Order updateStatus(Order order) {
//        return null;
//    }

    @Override
    public List<Order> getOrdersByUser(User user) {
        return orders.stream().filter(order -> order.getUser().getId().equals(user.getId())).toList();
    }

    @Override
    public List<Order> getOrdersByStatus(OrderStatus orderStatus) {
        return orders.stream().filter(order -> order.getStatus().name().equals(orderStatus.name())).toList();
    }
}
