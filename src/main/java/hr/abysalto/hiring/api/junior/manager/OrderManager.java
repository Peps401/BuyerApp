package hr.abysalto.hiring.api.junior.manager;

import hr.abysalto.hiring.api.junior.model.Order;

public interface OrderManager {
	Iterable<Order> getAllOrders();
	void save(Order buyer);
	Order getById(Long id);
	void deleteById(Long id);
}
