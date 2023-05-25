package com.mateusz.SystemERP.service;

import com.mateusz.SystemERP.customer.Customer;
import com.mateusz.SystemERP.customer.CustomerRepository;
import com.mateusz.SystemERP.customer.CustomerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.*;

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
        when(mockCustomerRepository.findAll()).thenReturn(emptyList);

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
    @DisplayName("Should throw 200 status and customers list in body")
    void getAllCustomers_customersList_noEmpty() {
        //given
        CustomerRepository mockCustomerRepository = mock(CustomerRepository.class);
        List<Customer> customerList = List.of(new Customer("Company1"), new Customer("Company2"));
        when(mockCustomerRepository.findAll()).thenReturn(customerList);

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
        when(mockCustomerRepository.findCustomerByName(anyString())).thenReturn(Optional.empty());

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
        when(mockCustomerRepository.findCustomerByName("Company")).thenReturn(Optional.of(customer));

        //when
        CustomerService toTest = new CustomerService(mockCustomerRepository);
        ResponseEntity<Customer> result = toTest.getCustomerByName("Company");

        //then
        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatusCode.valueOf(200));

        assertThat(result.getBody().getName())
                .isEqualTo("Company");
    }

    @Test
    @DisplayName("Should throw 400 status when name is empty")
    void addCustomer_nameIsBlank() {
        //given
        CustomerService toTest = new CustomerService(inMemoryCustomerRepository());
        String name = " ";
        //when
        ResponseEntity<?> result = toTest.addCustomer(name);
        //then

        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatusCode.valueOf(400));
    }

    @Test
    @DisplayName("Should throw 409 status when customer exist in DB")
    void addCustomer_customerNameExistInDB() {
        //given
        CustomerService toTest = new CustomerService(inMemoryCustomerRepository());
        String name = "Company";
        toTest.addCustomer(name);
        //when
        ResponseEntity<?> result = toTest.addCustomer(name);
        //then

        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatusCode.valueOf(409));
    }
    @Test
    @DisplayName("Should throw 201 status and add customer to DB when name is correct")
    void addCustomer_nameIsCorrect() {
        //given
        CustomerService toTest = new CustomerService(inMemoryCustomerRepository());
        String name = "Company";
        //when
        ResponseEntity<?> result = toTest.addCustomer(name);
        ResponseEntity<Customer> findAddedCustomer = toTest.getCustomerByName(name);
        //then

        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(findAddedCustomer.getStatusCode())
                .isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(findAddedCustomer.getBody().getName())
                .isEqualTo(name);

    }

    private CustomerRepository inMemoryCustomerRepository(){
        return new CustomerRepository() {

            private Map<String,Customer> mapDB = new HashMap<>();
            @Override
            public List<Customer> findAll() {
                return new ArrayList<>(mapDB.values());
            }

            @Override
            public Optional<Customer> findCustomerByName(String name) {
                return Optional.ofNullable(mapDB.get(name));
            }

            @Override
            public Customer save(Customer customer) {
                mapDB.put(customer.getName(), customer);
                return customer;
            }
        };
    }
}