package com.getir.readingIsGood.controller.vo.customer;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    @NotNull(message = "Customer name can not be null")
    @NotBlank(message = "Customer name can not be empty")
    private String customerName;

    @NotNull(message = "Customer surname can not be null")
    @NotBlank(message = "Customer surname can not be empty")
    private String customerSurname;

    @NotNull(message = "Customer phone can not be null")
    @NotBlank(message = "Customer phone can not be empty")
    private String customerPhoneNumber;

    @NotNull(message = "Customer email can not be null")
    @NotBlank(message = "Customer email can not be empty")
    @Email(message = "Email Should be valid")
    private String customerEmail;

    @NotNull(message = "Customer address can not be null")
    @NotBlank(message = "Customer address can not be empty")
    private String customerAddress;
}