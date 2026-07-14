package group.shawarma.domain.repo;

import group.shawarma.domain.model.Delivery;

public interface DeliveryRepo {
    Delivery saveDelivery(Delivery delivery);
    Delivery updateDelivery(Delivery delivery);
    Delivery getDeliveryById(Long id);
}
