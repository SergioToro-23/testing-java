import java.util.List;

public class ExercisesEmployees {

    public static void printEmployeeNames(List<Employee> employees){//static works for the class as it doesnt need an instance to be called
        employees.forEach(employee ->
            System.out.println(" "+employee.getName())
        );
    }

    public static List<Employee> getEmployeesOlderThan30(List<Employee> employees){
        return employees.stream()
        .filter(employee -> employee.getAge() > 30)
        .toList();       //O(n) 
    }

    public static String getEmployeeWithBiggestSalary(List<Employee> employees){
        
        if (employees == null || employees.isEmpty()) {
            return null; 
        }

        Employee top = employees.get(0);
        for (Employee employee : employees) {
            if (employee.getSalary().compareTo(top.getSalary()) > 0) {
                top = employee;
            }
        }
        return top.getName();
/* 
        List<Employee> auxList = null;

        for (Employee employee : employees) {
            for (Employee AuxEmployee : employees) {
                if(employee.getAge() > AuxEmployee.getAge()){
                    auxList.add(employee);
                }
                
            }
        }  */      
        
    }


}