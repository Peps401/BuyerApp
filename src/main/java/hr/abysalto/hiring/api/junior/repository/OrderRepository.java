package hr.abysalto.hiring.api.junior.repository;

import hr.abysalto.hiring.api.junior.model.Order;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> { //PagingAndSortingRepository

	@Modifying
	@Query("UPDATE orders SET first_name = :name WHERE order_id = :id")
	boolean updateByFirstName(@Param("id") Long id, @Param("name") String name);
}

