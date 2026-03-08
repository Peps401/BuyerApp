package hr.abysalto.hiring.api.junior.manager;

import hr.abysalto.hiring.api.junior.model.OrderItem;
import hr.abysalto.hiring.api.junior.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderItemManagerImpl implements OrderItemManager {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(OrderItem orderItem) {
        
    Short maxItem = jdbcTemplate.queryForObject(
        "SELECT COALESCE(MAX(item_nt),0) FROM order_item WHERE order_nr = ?",
        Short.class,
        orderItem.getOrderNr()
    );

    orderItem.setItemNt((short)(maxItem + 1));

    this.orderItemRepository.save(orderItem);
    }
    }

