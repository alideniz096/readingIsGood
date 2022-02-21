package com.getir.readingIsGood.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "D_ORDER")
public class Order implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @SequenceGenerator(name = "orderSeq", sequenceName = "orderSequence", allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderSeq")
    @Column(name = "ORDER_ID", unique = true, nullable = false)
    private Long orderId;

    @Column(name = "FKCustomerId", nullable = false)
    private Long customerId;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "ORDER_ITEM_QUANTITY", nullable = false)
    private Long orderItemQuantity;

    @Column(name = "CREATION_DATE")
    @CreationTimestamp
    private Date creationDate;
}
