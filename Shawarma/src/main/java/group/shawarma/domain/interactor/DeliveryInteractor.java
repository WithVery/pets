package group.shawarma.domain.interactor;

import group.shawarma.domain.model.Delivery;
import group.shawarma.domain.repo.DeliveryRepo;

public class DeliveryInteractor {
    private final DeliveryRepo deliveryRepo;

    public DeliveryInteractor(DeliveryRepo deliveryRepo) {
        this.deliveryRepo = deliveryRepo;
    }

    public Delivery createDelivery(Delivery delivery) {
        return deliveryRepo.saveDelivery(delivery);
    }

    public Delivery changeDelivery(Delivery delivery) {
        return deliveryRepo.updateDelivery(delivery);
    }

    public Delivery getDeliveryById(Long id) {
        return deliveryRepo.getDeliveryById(id);
    }

}
