import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        App app = new App();
        app.run();
    }
    
    void run() {

        List<Employee> employees = SampleEmployees.getSampleEmployees();

        System.out.println("Older than 30");
        for(Employee var:employees){ //O(n)
            if(var.getAge() > 30){
            System.out.println(" "+var.getName()); 
            }
        }

        System.out.println("Older than 30 again with streams and methods");
        ExercisesEmployees.printEmployeeNames(ExercisesEmployees.getEmployeesOlderThan30(employees));

        System.out.println("Oldest one");
        System.out.println(" "+ExercisesEmployees.getEmployeeWithBiggestSalary(employees));
    }

}
