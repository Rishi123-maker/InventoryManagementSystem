package com.project.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	Order findByOrderId(int id);

	Optional<Order> findByStatus(String status);

	@Query("SELECT o FROM Order o JOIN o.product p WHERE p.name = :name")
	List<Optional<Order>> findByProductName(@Param("name") String name);

	@Query(value = "Select * from Orders where order_date = :date ", nativeQuery = true)
	List<Order> getOrderByDate(@Param("date") LocalDate date);

	@Query(value = "Select product_id,sum(quantity) from orders Group by product_id order by sum(quantity) desc", nativeQuery = true)
	List<Object[]> findHighestOrderedProduct(Pageable pageable);

	@Query("DELETE FROM Order o WHERE o.orderId = :id")
	void deleteByOrderId(@Param("id") int id);


}
