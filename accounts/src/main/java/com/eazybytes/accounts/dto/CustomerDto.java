package com.eazybytes.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(
        name="Customer",
        description = "Schema to hold  Customer Information"
)
public class CustomerDto {

    @Schema(
            description = "Name of the Customer",
            example = "Arpit Pathak"
    )
    @NotEmpty(message = "Name cant be null or empty")
    @Size(min = 5 , max =30 , message = "Length of customer name should be between 5 and 30")
    private String name;


    @Schema(
            description = "Email of the Customer",
            example = "arpit@gmail.com"
    )
    @NotEmpty(message = "Email address can not be null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Schema(
            description = "Phone Number of the Customer",
            example = "6633556611"
    )
    @Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
    private String mobileNumber;


    @Schema(
            description = "Accounts Details of the Customer"
    )
    private AccountsDto accountsDto;

}


