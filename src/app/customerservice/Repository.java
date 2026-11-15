package app.customerservice;
import java.util.Optional;

import app.employeeschallenge.Employee;

public class Repository {
    public Optional<Employee> findByECIDIgnoreCase(String id) {
        return Optional.ofNullable(new Employee());
    }
    public Optional<Employee> findByEmailIgnoreCase(String email) {
        return Optional.ofNullable(new Employee());
    }
    public Optional<Employee> findByNumber(String number) {
        return Optional.ofNullable(new Employee());
    }
}
