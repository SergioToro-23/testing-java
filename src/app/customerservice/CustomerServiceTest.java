package app.customerservice;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import app.employeeschallenge.Employee;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private Repository customerRepository;

    // @InjectMocks
    private CustomerService customerService;

    private Employee expected;

    @BeforeEach
    void setUp() {
        expected = new Employee();
        customerService = new CustomerService(customerRepository);// @InjectMocks same
    }

    @Test
    void shouldFindCustomerByValidECID() {

        when(customerRepository.findByECIDIgnoreCase("ECID-123"))
                .thenReturn(Optional.of(expected));

        Optional<Employee> result = customerService.findCustomer("ECID-123");

        assertTrue(result.isPresent());
        assertSame(expected, result.orElse(null));
        verify(customerRepository).findByECIDIgnoreCase("ECID-123");
    }

    @Test
    void shouldFindCustomerByValidEmail() {
        when(customerRepository.findByEmailIgnoreCase("hola@hotm.com"))
                .thenReturn(Optional.of(expected));

        Optional<Employee> result = customerService.findCustomer("hola@hotm.com");

        assertTrue(result.isPresent());
        assertSame(expected, result.orElse(null));
        verify(customerRepository).findByEmailIgnoreCase("hola@hotm.com");

    }

    @Test
    void shouldFindCustomerByValidNumber() {
        when(customerRepository.findByNumber("1234567893"))
                .thenReturn(Optional.of(expected));

        Optional<Employee> result = customerService.findCustomer("1234567893");

        assertTrue(result.isPresent());
        assertSame(expected, result.orElse(null));
        verify(customerRepository).findByNumber("1234567893");

    }

    @Test 
    void shouldNormalizeCorrectlyTheNumber(){
        when(customerRepository.findByNumber("2065551234"))
                .thenReturn(Optional.of(expected));
    
        Optional<Employee> result = customerService.findCustomer("(206) 555-1234");

        assertTrue(result.isPresent());
        assertSame(expected, result.orElse(null));
        verify(customerRepository).findByNumber("2065551234");
    }

    @Test 
    void shouldVerifyCostumerExists(){
        when(customerRepository.findByNumber("2065551234"))
            .thenReturn(Optional.of(expected));

        boolean result = customerService.findCustomerExists("(206) 555-1234");

        assertTrue(result);
        verify(customerRepository).findByNumber("2065551234");
    }

    @Test 
    void shouldReturnEmptyWhenIdentifierIsNull(){
        Optional<Employee> result = customerService.findCustomer(null);

        assertTrue(result.isEmpty());
        verifyNoInteractions(customerRepository);
    }

    @Test 
    void shouldReturnEmptyWhenIdentifierIsBlank(){
        Optional<Employee> result = customerService.findCustomer("  ");

        assertTrue(result.isEmpty());
        verifyNoInteractions(customerRepository);
    }

    @Test 
    void shouldReturnEmptyWhenIdentifierIsInvalid(){        
        Optional<Employee> result = customerService.findCustomer(" .@asda ");

        assertTrue(result.isEmpty());
        verifyNoInteractions(customerRepository);
    }

}