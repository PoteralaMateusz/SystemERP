package com.mateusz.SystemERP.customer;

import com.mateusz.SystemERP.customer.dto.CustomerDTO;
import com.mateusz.SystemERP.customer.dto.CustomerDTOMapper;
import com.mateusz.SystemERP.customer.exceptions.CustomerNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDTOMapper customerDTOMapper;

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        if (customerList.isEmpty()) {
            throw new CustomerNotFoundException("Customers list is empty.");
        }
        return customerList
                .stream()
                .map(customerDTOMapper::map)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerByName(String name) {
        return customerRepository
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
        return customerDTOMapper.map(customerRepository.save(customerDTOMapper.map(toSave)));
    }

    public CustomerDTO deleteById(String name){
        return customerRepository.findCustomerByName(name)
                .map(customer -> {
                    customerRepository.deleteById(customer.getName());
                    return customerDTOMapper.map(customer);
                })
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer with name " + name + " does not exist"));
    }


}
