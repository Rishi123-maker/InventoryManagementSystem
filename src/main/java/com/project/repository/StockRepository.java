package com.project.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
	List<Stock> findByReorderLevel(String reorderLevel);
	@Query(value = "Select * from stocks order by quantity desc", nativeQuery = true)
	List<Object[]> findHighestQuantity(Pageable pageable);
	@Query("Select count(O) from Order O where O.product.productId=:id")
	int getOrderQuantity(@Param("id") int id);
}
