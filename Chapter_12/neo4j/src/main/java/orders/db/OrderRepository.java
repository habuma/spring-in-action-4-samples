package orders.db;

import java.util.List;

import orders.Order;

import org.springframework.data.neo4j.repository.GraphRepository;

public interface OrderRepository extends GraphRepository<Order> {
	
	List<Order> findByCustomer(String customer);
	
	List<Order> findByCustomerLike(String customer);

	List<Order> findByCustomerAndType(String customer, String type);

	List<Order> getByType(String type);
		
//	@Query("{customer:'Chuck Wagon'}")
//	List<Order> findChucksOrders();

}
