package com.eazybytes.accounts.dto;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message = "Name cant be null or empty")
    @Size(min = 5 , max =30 , message = "Length of customer name should be between 5 and 30")
    private String name;

    @NotEmpty(message = "Email address can not be null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
    private String mobileNumber;


    private AccountsDto accountsDto;

}


