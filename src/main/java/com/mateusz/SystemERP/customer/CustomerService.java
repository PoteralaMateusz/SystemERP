package com.mateusz.SystemERP.customer;

import com.mateusz.SystemERP.customer.dto.CustomerAddDTO;
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
                .filter(customer -> !customer.isDeleted())
                .map(customerDTOMapper::mapCustomerDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerByName(String name) {
        return customerRepository
                .findCustomerByName(name)
                .filter(customer -> !customer.isDeleted())
                .map(customerDTOMapper::mapCustomerDTO)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer with name " + name + " does not exist"));
    }

    @Transactional
    public CustomerAddDTO addOrUpdateCustomer(CustomerAddDTO toSave) {
        if (toSave == null){
            throw new CustomerNotFoundException("Customer data is null.");
        }
        return customerDTOMapper.mapCustomerAddDTO(customerRepository.save(customerDTOMapper.mapCustomerAddDTO(toSave)));
    }

    @Transactional
    public CustomerDTO deleteById(Long customerId){
        return customerRepository.findCustomerById(customerId)
                .map(customer -> {
                    customerRepository.deleteById(customer.getId());
                    return customerDTOMapper.mapCustomerDTO(customer);
                })
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer with id " + customerId + " does not exist"));
    }


}
