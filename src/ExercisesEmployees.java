import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

public class ExercisesEmployees {

    public static void printEmployeeNames(List<Employee> employees) {// static works for the class as it doesnt need an
                                                                     // instance to be called
        employees.forEach(employee -> System.out.println(" " + employee.getName()));
    }

    public static List<Employee> getEmployeesOlderThan30(List<Employee> employees) {
        return employees.stream()
                .filter(employee -> employee.getAge() > 30)
                .toList(); // O(n)
    }

    public static String getEmployeeWithBiggestSalary(List<Employee> employees) {
        /*
         * if (employees == null || employees.isEmpty()) {
         * return null;
         * }
         * 
         * Employee top = employees.get(0);
         * for (Employee employee : employees) {
         * if (employee.getSalary().compareTo(top.getSalary()) > 0) {
         * top = employee;
         * }
         * }
         * 
         * return top.getName()+" with salary: "+top.getSalary();
         */

        return employees.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .map(x -> x.getName() + " with salary: " + x.getSalary())
                .orElse("No employees found");

    }

    public static Double getAverageSalary(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            return BigDecimal.ZERO.doubleValue();
        }
        /* 
        return employees.stream()
                .mapToDouble(employee -> employee.getSalary().doubleValue())
                .average()
                .orElse(0.0);*/

        return employees.stream()
                .reduce(employee::employee.getSalary().add)
                .divide(BigDecimal.valueOf(employees.size()),2, RoundingMode.HALF_UP);
    }

    public static List<Employee> getEmployeesBySalaryDescending(List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())// O(n log n) using quicksort or mergesort
                .toList();
    }

}