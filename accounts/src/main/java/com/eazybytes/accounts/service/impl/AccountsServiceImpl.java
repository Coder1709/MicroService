package com.eazybytes.accounts.service.impl;


import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.exception.ResourceNotFoundExxxception;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl  implements IAccountService {


    private AccountRepository accountRepository;

    private CustomerRepository customerRepository;


    /**
     *
     * @param customerDto
     */
    @Override
    public void createAccount(CustomerDto customerDto) {


        Optional<Customer> optionalCustomer  = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer with this phone Number Already exist" + customerDto.getMobileNumber());
        }
        Customer customer = CustomerMapper.mapToCustomer(customerDto , new Customer());
        customer.setCreatedBy("Arpit");
        customer.setCreatedAt(LocalDateTime.now());

        Customer saveCustomer = customerRepository.save(customer);
        Accounts accountForCustomer = accountRepository.save(createNewAccount(saveCustomer));

    }

    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    @Override
    public Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Arpit");
        return newAccount;
    }


    /**
     *
     * @param mobileNumber
     * @return customer dto object
     */
    @Override
    public CustomerDto fetchCustomerWithMobileNumber(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundExxxception("Customer" , "Mobile Number" , mobileNumber)
        );

        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()->new ResourceNotFoundExxxception("Customer" , "Customer Id" , customer.getCustomerId().toString())
        );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        AccountsDto accountsDto = AccountsMapper.mapToAccountsDto(accounts, new AccountsDto());
        customerDto.setAccountsDto(accountsDto);

        return customerDto;

    }

    /**
     *
     * @param customerDto
     * @return boolean , whether the account is updated or not
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {

        boolean isUpdated = false;

        AccountsDto accountsDto = customerDto.getAccountsDto();

        if(accountsDto != null){
            Accounts accounts= accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () ->  new ResourceNotFoundExxxception("Account" , "Account Number" , accountsDto.getAccountNumber().toString())
            );

            AccountsMapper.mapToAccounts(accountsDto, accounts);

            accounts = accountRepository.save(accounts);

            Long customerId = accounts.getCustomerId();

            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundExxxception("Customer" , "customerId" , customerId.toString())

            );

            CustomerMapper.mapToCustomer(customerDto , customer);
            customerRepository.save(customer);
            isUpdated = true;

        }
        return isUpdated;


    }

    /*Yes, there are additional differences in how JPA interprets customerRepository.delete(customer); and customerRepository.deleteByMobileNumber(customer.getMobileNumber());:
    customerRepository.delete(customer);:
    Entity State: JPA requires the entity to be in a managed state (i.e., attached to the persistence context). If the entity is detached, it must be reattached (e.g., by fetching it from the database) before deletion.
    Cascade Operations: If the entity has relationships with other entities, JPA will handle cascading delete operations based on the cascade type defined in the entity mappings.
    customerRepository.deleteByMobileNumber(customer.getMobileNumber());:
    Query Execution: This method translates to a DELETE query executed directly in the database. It does not require the entity to be in a managed state.
    Performance: Direct deletion by a specific attribute can be more efficient as it avoids loading the entity into the persistence context.
    Cascade Operations: Cascading delete operations are not automatically handled. You need to ensure that related entities are deleted if necessary.
    In summary, delete(customer) involves entity state management and potential cascading operations, while deleteByMobileNumber is a direct database operation that can be more efficient but requires manual handling of related entities.*/

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundExxxception("Customer", "mobileNumber", mobileNumber)
        );
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

}
