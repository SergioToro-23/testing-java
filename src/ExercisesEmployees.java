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

    public static BigDecimal getAverageSalary(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            return BigDecimal.ZERO;
        }
        /*
         * return employees.stream()
         * .mapToDouble(employee -> employee.getSalary().doubleValue())
         * .average()
         * .orElse(0.0);
         */

        return employees.stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, (total, salary) -> total.add(salary))
                .divide(BigDecimal.valueOf(employees.size()), 2, RoundingMode.HALF_UP);
    }

    public static List<Employee> getEmployeesBySalaryDescending(List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())// O(n log n) using quicksort or mergesort
                .toList();
    }

    public static List<Employee> getEmployeesBySalaryDexcendingBubble(List<Employee> employees) {
        if (employees == null || employees.size() < 2) {
            return employees;
        }

        List<Employee> result = employees;
        int n = result.size();
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            System.out.println(i);
            for (int j = 0; j < n; j++) {
                System.out.println(j);
                Employee current = result.get(j);
                Employee next = result.get(j+1);
                if (current.getSalary().compareTo(next.getSalary()) < 0) {
                    result.set(j,next);
                    result.set(j+1,current);
                }
            }
        }

        return employees;
    }

}