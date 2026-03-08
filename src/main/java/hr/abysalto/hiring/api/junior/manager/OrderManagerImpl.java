package hr.abysalto.hiring.api.junior.manager;

import hr.abysalto.hiring.api.junior.model.Order;
import hr.abysalto.hiring.api.junior.repository.OrderRepository;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class OrderManagerImpl implements OrderManager {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private BigDecimal calculateTotal(Long orderNr) {

		BigDecimal total = jdbcTemplate.queryForObject(
				"SELECT COALESCE(SUM(quantity * price),0) FROM order_item WHERE order_nr = ?",
				BigDecimal.class,
				orderNr
		);

		return total;
	}

	@Override
	public Iterable<Order> getAllOrders() {
		Iterable<Order> orders = this.orderRepository.findAll();

		for (Order order : orders) {
			order.setTotalPrice(calculateTotal(order.getOrderNr()));
		}

    return orders;
	}

	@Override
	public void save(Order order) {
		this.orderRepository.save(order);
	}

	@Override
	public Order getById(Long id) {
		return this.orderRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long id) {
		this.orderRepository.deleteById(id);
	}
}

