package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data

public class AccountsDto {


    @Pattern(regexp = "\\d{10}", message = "Account number must be 10 digits")
    @NotEmpty(message = "Account Number cant be empty")
    private Long accountNumber;


    @NotEmpty(message = "Account Type cant be empty or  null")
    private String accountType;

    @NotEmpty(message = "Branch Address cant be empty or  null")
    private String branchAddress;
}