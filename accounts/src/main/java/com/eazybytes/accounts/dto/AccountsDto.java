package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name="Account",
        description = "Schema to hold  Account Information"
)
public class AccountsDto {

    @Schema(
            description = "Account Number , 10 digit Numeric Value",
            example = "1234567899"
    )
    @Pattern(regexp = "\\d{10}", message = "Account number must be 10 digits")
    @NotEmpty(message = "Account Number cant be empty")
    private Long accountNumber;



    @Schema(
            description = "Type of account i.e Saving , Current",
            example = "Saving"
    )
    @NotEmpty(message = "Account Type cant be empty or  null")
    private String accountType;

    @Schema(
            description = "Branch Address of the account",
            example = "DELHI"
    )
    @NotEmpty(message = "Branch Address cant be empty or  null")
    private String branchAddress;
}