package com.mateusz.SystemERP.customer;

import com.mateusz.SystemERP.customer.dto.CustomerDTO;
import com.mateusz.SystemERP.customer.dto.CustomerDTOMapper;
import com.mateusz.SystemERP.customer.exceptions.CustomerExistException;
import com.mateusz.SystemERP.customer.exceptions.CustomerNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;
    private final CustomerDTOMapper customerDTOMapper;

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customerList = repository.findAll();
        if (customerList.isEmpty()) {
            throw new CustomerNotFoundException("Customers list is empty.");
        }
        return customerList
                .stream()
                .map(customerDTOMapper::map)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerByName(String name) {
        return repository
                .findCustomerByName(name)
                .map(customerDTOMapper::map)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer with name " + name + " does not exist"));
    }

    @Transactional
    public CustomerDTO addOrUpdateCustomer(CustomerDTO toSave) {
        if (toSave == null){
            throw new CustomerNotFoundException("Customer data is null.");
        }
        return customerDTOMapper.map(repository.save(customerDTOMapper.map(toSave)));
    }


}
