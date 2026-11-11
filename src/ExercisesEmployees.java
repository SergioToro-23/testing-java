import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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

    public static Optional<String> getEmployeeWithBiggestSalary(List<Employee> employees) {
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

        return Optional.of(employees.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .map(x -> x.getName() + " with salary: " + x.getSalary())
                .orElse("No employees found"));

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
                .reduce(BigDecimal.ZERO, (totaly, salary) -> totaly.add(salary))
                .divide(BigDecimal.valueOf(employees.size()), 2, RoundingMode.HALF_UP);
    }

    public static List<Employee> getEmployeesBySalaryDescending(List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())// O(n log n) using quicksort or mergesort
                .toList();
    }

    public static List<Employee> getEmployeesBySalaryDexcendingBubble(List<Employee> employees) { //O(n^2) using bubble sort
        if (employees == null || employees.size() < 2) {
            return employees;
        }

        List<Employee> result = new ArrayList<>(employees);
        int n = result.size();
        boolean swapped = false;

        for (int i = 0; i < n; i++) {
            swapped = false;
            //System.out.println("nuevo i: "+i);

            for (int j = 0; j < n - 1 - i; j++) {
                //System.out.println("nuevo j: "+j);
                Employee current = result.get(j);
                Employee next = result.get(j+1);
                //System.out.println("salarios "+current.getSalary()+" , "+next.getSalary());

                if (current.getSalary().compareTo(next.getSalary()) < 0) {
                    //System.out.println("entro swap i ,j: "+i+","+j);
                    result.set(j,next);
                    result.set(j+1,current);
                    swapped = true;
                }
            }
            if (!swapped) break; // If no swaps were made, the list is already sorted
        }

        return result;
    }

}