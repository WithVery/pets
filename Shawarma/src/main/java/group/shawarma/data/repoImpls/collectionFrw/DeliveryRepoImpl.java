package group.shawarma.data.repoImpls.collectionFrw;

import group.shawarma.domain.model.Delivery;
import group.shawarma.domain.repo.DeliveryRepo;

import java.util.ArrayList;
import java.util.List;

public class DeliveryRepoImpl implements DeliveryRepo {

    private final List<Delivery> items = new ArrayList<>();

    @Override
    public Delivery saveDelivery(Delivery delivery) {
        items.add(delivery);
        return delivery;
    }

    @Override
    public Delivery updateDelivery(Delivery delivery) {
        int index = items.indexOf(delivery);
        if(index != -1) items.set(index, delivery);
        return delivery;
    }

    @Override
    public Delivery getDeliveryById(Long id) {
        return items.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
    }
}
