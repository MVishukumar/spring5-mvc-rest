package guru.springframework.services;

import guru.springframework.api.v1.mapper.CustomerMapper;
import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.domain.Customer;
import guru.springframework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }


    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList
                .stream()
                .map(customer -> {
                    return customerMapper.customerToCustomerDTO(customer);
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()) {
            return customerMapper.customerToCustomerDTO(customer.get()) ;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOtoCustomer(customerDTO);

        Customer customerSaved = customerRepository.save(customer);
        return customerMapper.customerToCustomerDTO(customerSaved);
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOtoCustomer(customerDTO);
        customer.setId(id);

        Customer customerSaved = customerRepository.save(customer);
        CustomerDTO customerDTOSaved = customerMapper.customerToCustomerDTO(customerSaved);

        return customerDTOSaved;
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        customerRepository.findById(id)
                .map(customer -> {
                    if(customerDTO.getFirstname() != null) {
                        customer.setFirstname(customerDTO.getFirstname());
                    }

                    if(customerDTO.getLastname() != null) {
                        customer.setLastname(customerDTO.getLastname());
                    }
                    Customer customerSaved = customerRepository.save(customer);
                    return customerMapper.customerToCustomerDTO(customerSaved);
                })
                .orElseThrow(ResourceNotFoundException::new);
        return null;
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
