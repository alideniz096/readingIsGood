package com.getir.readingIsGood.repository;

import com.getir.readingIsGood.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrdersByCustomerId(Long customerId);

    Order findOrderByOrderId(Long orderId);

    @Query(value = "SELECT MONTHNAME(CREATION_DATE) as MonthName, SUM(TOTAL_PRICE) as TotalAmount , COUNT(ORDER_ID) as TotalOrderCount, SUM(ORDER_ITEM_QUANTITY) as TotalBookCount FROM D_ORDER GROUP BY MonthName ORDER BY MonthName", nativeQuery = true)
    List<Tuple> findCustomerOrderStatistic(Long customerId);

}
