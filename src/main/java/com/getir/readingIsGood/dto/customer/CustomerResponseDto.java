package com.getir.readingIsGood.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {

    private Long customerId;
    private String customerName;
    private String customerSurname;
    private String customerPhoneNumber;
    private String customerEmail;
    private String customerAddress;
    private Date creationDate;
}
