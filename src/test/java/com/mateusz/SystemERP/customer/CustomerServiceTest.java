package com.mateusz.SystemERP.customer;

import com.mateusz.SystemERP.customer.dto.CustomerDTO;
import com.mateusz.SystemERP.customer.dto.CustomerDTOMapper;
import com.mateusz.SystemERP.customer.exceptions.CustomerNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    @Test
    @DisplayName("Should throw CustomerNotFoundException when returned customers list is empty")
    void getAllCustomersWhenCustomersListIsEmpty() {
        //given
        CustomerRepository mockCustomerRepository = mock(CustomerRepository.class);
        List<Customer> emptyCustomerList = new ArrayList<>();
        //when
        when(mockCustomerRepository.findAll()).thenReturn(emptyCustomerList);
        CustomerService customerService = new CustomerService(mockCustomerRepository,new CustomerDTOMapper(mockCustomerRepository));
        //then
        try{
            customerService.getAllCustomers();
        }catch (Exception e) {
            assertThat(e)
                    .isInstanceOf(CustomerNotFoundException.class)
                    .hasMessage("Customers list is empty.");
        }
    }

    @Test
    @DisplayName("Should return customer list when are in database")
    void getAllCustomersReturnCustomersList() {
        //given
        CustomerRepository mockCustomerRepository = mock(CustomerRepository.class);
        List<Customer> customerListFromDB = new ArrayList<>();
        customerListFromDB.add(new Customer("Customer","Poland","Warszawa","Główna",123,123,new ArrayList<>()));

        //when
        when(mockCustomerRepository.findAll()).thenReturn(customerListFromDB);
        CustomerService customerService = new CustomerService(mockCustomerRepository,new CustomerDTOMapper(mockCustomerRepository));
        List<CustomerDTO> result = customerService.getAllCustomers();
        //then
        assertEquals(1,result.size());
        assertEquals("Customer",result.get(0).name());
    }

    @Test
    @DisplayName("Should throw CustomerNotFoundException when customer by id dont exist")
    void getCustomerByIdWhenCustomerDontExist() {
        //given
        CustomerRepository mockCustomerRepository = mock(CustomerRepository.class);
        //when
        when(mockCustomerRepository.findCustomerById(anyLong())).thenReturn(Optional.empty());
        CustomerService customerService = new CustomerService(mockCustomerRepository,new CustomerDTOMapper(mockCustomerRepository));
        //then
        try{
            customerService.getCustomerById(123L);
        }catch (Exception e) {
            assertThat(e)
                    .isInstanceOf(CustomerNotFoundException.class)
                    .hasMessageContaining("Customer with id ")
                    .hasMessageContaining(" does not exist");
        }
    }

    @Test
    @DisplayName("Should return exist customer")
    void getCustomerByIdWhenCustomerExist() {
        //given
        CustomerRepository mockCustomerRepository = mock(CustomerRepository.class);
        Customer customer = new Customer(1L,"Customer","Poland","Warszawa","Główna",123,123,new ArrayList<>());
        //when
        when(mockCustomerRepository.findCustomerById(1L)).thenReturn(Optional.of(customer));
        CustomerService customerService = new CustomerService(mockCustomerRepository,new CustomerDTOMapper(mockCustomerRepository));
        CustomerDTO result = customerService.getCustomerById(1L);
        //then
        assertEquals("Customer",result.name());
        assertEquals(1L,result.customerId());
        assertEquals("Warszawa",result.city());

    }
}