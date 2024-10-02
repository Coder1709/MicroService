package com.eazybytes.accounts.controller;


import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ErrorResponseDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path="/api" , produces = MediaType.APPLICATION_JSON_VALUE)
@Validated //to perform validation
@Tag(
        name = "CRUD RESTs API in Easy Bank",
        description = "CRUD rest api to create , update, read and delete account details"
)
public class AccountsController {



    private IAccountService iAccountService;


    @Operation(
            summary = "Create Account Rest Api",
            description = "Rest APi to Create new Customer and Accounts"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status Created"
    )
    @PostMapping(path="/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        iAccountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_200));
    }


    @Operation(
            summary = "Fetch Account Details Rest Api",
            description = "Rest APi to Fetch  Customer with their Accounts"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status Ok"
    )
    @GetMapping(path="/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam @Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
                                                               String mobileNumber){

        CustomerDto customer = iAccountService.fetchCustomerWithMobileNumber(mobileNumber);
        System.out.println(customer);
        return ResponseEntity.status(HttpStatus.OK).body(customer);

    }


    @Operation(
            summary = "Delete Account Rest Api",
            description = "Rest APi to Delete a Customer and Accounts"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP Status Ok"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)

                            )
                    )

            }


    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountsDetails(@RequestParam @Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits") String mobileNumber)
    {
        boolean isDeleted = iAccountService.deleteAccount(mobileNumber);

        if(isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.MESSAGE_200 , AccountsConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountsConstants.STATUS_500 , AccountsConstants.MESSAGE_500));
        }
    }

    @Operation(
            summary = "Update Account Rest Api",
            description = "Rest APi to Update new Customer and Accounts"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP Status Ok"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class    )

                            )
                    )

            }


    )

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountInformation(@Valid @RequestBody CustomerDto customerDto){

        boolean isUpdated = iAccountService.updateAccount(customerDto);

        if(isUpdated){
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.STATUS_200 , AccountsConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(AccountsConstants.STATUS_500 , AccountsConstants.MESSAGE_500));
        }

    }
}
