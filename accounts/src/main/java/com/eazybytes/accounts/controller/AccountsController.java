package com.eazybytes.accounts.controller;


import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountService;
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
public class AccountsController {

    private IAccountService iAccountService;


    @PostMapping(path="/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        iAccountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_200));
    }


    @GetMapping(path="/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam @Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
                                                               String mobileNumber){

        CustomerDto customer = iAccountService.fetchCustomerWithMobileNumber(mobileNumber);
        System.out.println(customer);
        return ResponseEntity.status(HttpStatus.OK).body(customer);

    }

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
}
