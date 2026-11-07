import java.math.BigDecimal;



public class Employee {
    private Long id;
    private String name;
    private String department;
    private BigDecimal salary;
    private int age;

    // Constructor, getters

    public Employee(Long id, String name, String department, BigDecimal salary, int age) {
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
}





