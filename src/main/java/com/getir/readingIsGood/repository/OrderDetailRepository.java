package com.getir.readingIsGood.repository;

import com.getir.readingIsGood.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    List<OrderDetail> findOrderDetailByOrderIdIn(List<Long> orderId);

    List<OrderDetail> findOrderDetailByCreationDateBetween(Date startDate, Date endDate);
}
