package guru.springframework.api.v1.mapper;

import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerMapperTest {

    private static final String firstname = "John";
    private static final String lastname = "Doe";
    private static final Long id = 1L;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() {
        Customer customer = new Customer();
        customer.setFirstname(firstname);
        customer.setLastname(lastname);
        customer.setId(id);

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertEquals(customer.getFirstname(), customerDTO.getFirstname());
        assertEquals(customer.getLastname(), customerDTO.getLastname());
        assertEquals(customer.getId(), customerDTO.getId());

    }

    @Test
    public void customerDTOtoCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(firstname);
        customerDTO.setLastname(lastname);
        customerDTO.setId(id);

        Customer customer = customerMapper.customerDTOtoCustomer(customerDTO);

        assertEquals(customer.getFirstname(), customerDTO.getFirstname());
        assertEquals(customer.getLastname(), customerDTO.getLastname());
        assertEquals(customer.getId(), customerDTO.getId());

    }
}