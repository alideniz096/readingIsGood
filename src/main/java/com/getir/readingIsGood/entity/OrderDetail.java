package com.getir.readingIsGood.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "D_ORDER_DETAIL")
public class OrderDetail {

    @Id
    @SequenceGenerator(name = "orderDetailSeq", sequenceName = "orderDetailSequence", allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderDetailSeq")
    @Column(name = "ORDER_DETAIL_ID")
    private Long orderDetailId;

    @Column(name = "FKORDER_ID", nullable = false)
    private Long orderId;

    @Column(name = "FKCUSTOMER_ID", nullable = false)
    private Long customerId;

    @Column(name = "FKBOOK_ID", nullable = false)
    private Long bookId;

    @Column(name = "QUANTITY", nullable = false)
    private Long quantity;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "CREATION_DATE", nullable = false)
    @CreationTimestamp
    private Date creationDate;
}
