package hr.abysalto.hiring.api.junior.repository;

import hr.abysalto.hiring.api.junior.model.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
    
}