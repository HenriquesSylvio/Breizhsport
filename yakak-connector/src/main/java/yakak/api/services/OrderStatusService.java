package yakak.api.services;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import yakak.api.models.entities.OrderStatus;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class OrderStatusService {
    
    public OrderStatus findById(String id) {
        return OrderStatus.findById(UUID.fromString(id));
    }

    public List<OrderStatus> findAll() {
        return OrderStatus.listAll(Sort.by("name"));
    }

    public OrderStatus createOrderStatus(OrderStatus orderStatus) {
        orderStatus.persist();
        return orderStatus;
    }

    public OrderStatus updateOrderStatus(String id, OrderStatus updatedOrderStatus) {
        OrderStatus currentOrderStatus = findById(id);

        if (currentOrderStatus != null) {
            currentOrderStatus.setName(updatedOrderStatus.getName());
            currentOrderStatus.setDescription(updatedOrderStatus.getDescription());
            currentOrderStatus.persist();
            return currentOrderStatus;
        } else {
            return null;
        }
    }

    public boolean deleteOrderStatus(String id) {
        return OrderStatus.deleteById(UUID.fromString(id));
    }
}
