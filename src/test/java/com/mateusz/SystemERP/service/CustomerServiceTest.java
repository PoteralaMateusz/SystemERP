package com.mateusz.SystemERP.service;

import com.mateusz.SystemERP.entity.Customer;
import com.mateusz.SystemERP.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    @Test
    @DisplayName("Should throw 404 status when returned customers list is empty")
    void getAllCustomers_customersList_empty() {
        //given
        CustomerRepository mockCustomerRepository = mock(CustomerRepository.class);
        List<Customer> emptyList = new ArrayList<>();
        when(mockCustomerRepository.getAllCustomers()).thenReturn(emptyList);

        //when
        CustomerService toTest = new CustomerService(mockCustomerRepository);
        ResponseEntity<List<Customer>> result = toTest.getAllCustomers();

        //then
        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatusCode.valueOf(404));

        assertThat(result.getBody())
                .isEqualTo(null);
    }

    @Test
    @DisplayName("Should throw 201 status and customers list in body")
    void getAllCustomers_customersList_noEmpty() {
        //given
        CustomerRepository mockCustomerRepository = mock(CustomerRepository.class);
        List<Customer> customerList = List.of(new Customer("Company1"), new Customer("Company2"));
        when(mockCustomerRepository.getAllCustomers()).thenReturn(customerList);

        //when
        CustomerService toTest = new CustomerService(mockCustomerRepository);
        ResponseEntity<List<Customer>> result = toTest.getAllCustomers();

        //then

        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatusCode.valueOf(200));

        assertThat(result.getBody())
                .isEqualTo(customerList);

        assertThat(result.getBody().get(0).getName())
                .isEqualTo("Company1");

        assertThat(result.getBody().get(1).getName())
                .isEqualTo("Company2");

    }

    @Test
    @DisplayName("Should throw 404 status when given customer name dont exist")
    void getCustomerByName_nameDontExist() {
        //given
        CustomerRepository mockCustomerRepository = mock(CustomerRepository.class);
        when(mockCustomerRepository.getCustomerByName(anyString())).thenReturn(Optional.empty());

        //when
        CustomerService toTest = new CustomerService(mockCustomerRepository);
        ResponseEntity<Customer> result = toTest.getCustomerByName("foo");

        //then
        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatusCode.valueOf(404));

        assertThat(result.getBody())
                .isEqualTo(null);
    }

    @Test
    @DisplayName("Should throw 200 status and customer in body")
    void getCustomerByName_nameExist() {
        //given
        CustomerRepository mockCustomerRepository = mock(CustomerRepository.class);
        Customer customer = new Customer("Company");
        when(mockCustomerRepository.getCustomerByName("Company")).thenReturn(Optional.of(customer));

        //when
        CustomerService toTest = new CustomerService(mockCustomerRepository);
        ResponseEntity<Customer> result = toTest.getCustomerByName("Company");

        //then
        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatusCode.valueOf(200));

        assertThat(result.getBody().getName())
                .isEqualTo("Company");
    }
}