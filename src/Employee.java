import java.math.BigDecimal;



public class Employee {

    private final Long id;
    public final String name;
    private final String department;
    private final BigDecimal salary;
    private final int age;

    // Constructor, getters

    public Employee(Long id, String name, String department, BigDecimal salary, int age) {//first and unique assignment
        
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
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





