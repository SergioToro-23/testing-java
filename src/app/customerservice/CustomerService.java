package app.customerservice;
/*
Problem: Customer Lookup by Any Identifier

Context
A customer-support team maintains a list of customers. Each customer has three identifiers:
Field	Example
ecid	ECID-1001
email	Jane.Doe@example.com
phone	(206) 555-1234
Support agents look up customers by typing whatever identifier they have on hand — sometimes the ECID, sometimes the email, sometimes a phone number. They don't tell the system which type it is.

Task
Write a method:
 findCustomer()
that finds a customer given a single identifier string, auto-detecting whether it is an ECID, an email, or a phone number.

Requirements
1. ECID — match case-insensitively (ecid-1001 ≡ ECID-1001).
2. Email — match case-insensitively.
3. Phone — agents enter numbers in many formats; all of these must match the same customer:
   - (206) 555-1234
   - 206-555-1234
   - 206.555.1234
   - +1 206 555 1234
   - 2065551234
4. Robustness — null or blank input, or an identifier that matches no known type, must return Optional.empty() (no exceptions).
5. Also provide a convenience method findCustomerExists(...) that returns a boolean.
6. write a few tests

*/

import java.util.Optional;

import app.employeeschallenge.Employee;

public class CustomerService {

    private final String ECID_REGUEX_PATTERN = "(?i)^ECID-\\d+$";
    private final String EMAIL_REGUEX_PATTERN = "(?i)^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$";    
    private final String NUMBER_REGUEX_PATTERN = "^(?:\\+1\\s*)?(?:\\(\\d{3}\\)|\\d{3})[\\s.-]*\\d{3}[\\s.-]*\\d{4}$";

    private final Repository customerRepository;

    public CustomerService(Repository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Employee> findCustomer(String id) {
        if (id == null || id.isBlank()) {
            return Optional.empty();
        }
        id = id.trim();
        
        IdentifierType type = detectType(id);        

        switch (type) {
            case ECID:
                return customerRepository.findByECIDIgnoreCase(id);
            case EMAIL:
                return customerRepository.findByEmailIgnoreCase(id);
            case PHONE:
                return customerRepository.findByNumber(normalizePhone(id));
            default:
                return Optional.empty();
        }
    }

    public boolean findCustomerExists(String id){
        return findCustomer(id).isPresent();
    }

    private IdentifierType detectType(String id) {        
        if (id.matches(ECID_REGUEX_PATTERN)) {
            return IdentifierType.ECID;
        } else if (id.matches(EMAIL_REGUEX_PATTERN)) {
            return IdentifierType.EMAIL;
        } else if (id.matches(NUMBER_REGUEX_PATTERN
            )) {
        return IdentifierType.PHONE;
        } else {
            return IdentifierType.UNKNOWN;
        }
    }

    private String normalizePhone(String id){
        String digits = id.replaceAll("\\D", "").trim();
        if (digits.length() == 11 && digits.startsWith("1")){
            return digits.substring(1);
        }
        return digits;
    }


    


}
