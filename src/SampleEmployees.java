import java.math.BigDecimal;
import java.util.List;

public class SampleEmployees {
    
    static List<Employee> getSampleEmployees() {
    return List.of(
            new Employee(8L, "Sergio", "IT", new BigDecimal("6000"), 40),
            new Employee(1L, "Carlos", "IT", new BigDecimal("5000"), 25),
            new Employee(2L, "Ana", "HR", new BigDecimal("4000"), 30),
            new Employee(3L, "Pedro", "IT", new BigDecimal("7000"), 35),
            new Employee(4L, "Laura", "Finance", new BigDecimal("6000"), 28),
            new Employee(5L, "Andres", "IT", new BigDecimal("3000"), 22),
            new Employee(6L, "Maria", "HR", new BigDecimal("8000"), 40),
            new Employee(7L, "Juan", "Finance", new BigDecimal("4500"), 32)
        );
 }
}
