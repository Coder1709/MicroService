package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;

public interface IAccountService {


    /**
     * This will take the customerDto object to save it to db
     * @param customerDto
     */
    void createAccount(CustomerDto customerDto);

    Accounts createNewAccount(Customer customer);


    CustomerDto fetchCustomerWithMobileNumber(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);


    boolean deleteAccount(String mobileNumber);
}
