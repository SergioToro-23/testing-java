import java.math.BigDecimal;
import java.util.Objects;



public class Employee {

    private final Long id;
    public final String name;
    private final String department;
    private final BigDecimal salary;
    private final int age;

    // Constructor, getters

    public Employee(
        Long id,
        String name, 
        String department, 
        BigDecimal salary, 
        int age) {//first and unique assignment
        
        this.id = Objects.requireNonNull(id, "Employee ID cannot be null");
        this.name = name;
        this.department = department;
        this.salary = salary;
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', department='" + department
                + "', salary=" + salary + ", age=" + age + "}";
    }

    String getName() {
        return name;
    }

    int getAge() {
        return age;
    }

    BigDecimal getSalary(){
        return salary;
    }
// Constructor, getters
}





