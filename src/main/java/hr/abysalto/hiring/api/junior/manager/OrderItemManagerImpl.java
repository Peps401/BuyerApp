package hr.abysalto.hiring.api.junior.manager;

import hr.abysalto.hiring.api.junior.model.OrderItem;
import hr.abysalto.hiring.api.junior.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderItemManagerImpl implements OrderItemManager {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void save(OrderItem orderItem) {
        this.orderItemRepository.save(orderItem);
    }
}
