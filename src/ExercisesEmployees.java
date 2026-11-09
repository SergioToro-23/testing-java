import java.util.List;
import java.util.stream.Collectors;

public class ExercisesEmployees {

public void utilPrintListNames(List<Employee> employees){
        employees.forEach(Employee -> System.out.println(Employee.getName(Employee)));
    }

public List<Employee> getEmployeesOlderThan30(List<Employee> employees){
        return employees.stream()
        .filter(employee -> employee.getAge(employee) > 30)
        .collect(Collectors.toList());       //O(n) 
    }


}