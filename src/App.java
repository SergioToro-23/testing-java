import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        App app = new App();
        app.run();
    }

    void run() {
        // employeesExecution();
        concurrencyExecution();
    }

    void concurrencyExecution() {
        CounterExercise counterExercise = new CounterExercise();
        Runnable task1 = () -> {
            
            System.out.println("Executing task1");
            for(int i = 0; i<100000;i++){
                counterExercise.increment();
            }
        };
        Runnable task2 = () -> {
            System.out.println("Executing task2");
            for(int i = 0; i<100000;i++){
                counterExercise.increment();
            }
        };
        Thread thread1 = new Thread(task1);
        thread1.start();
        Thread thread2 = new Thread(task2);
        thread2.start();
        
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Counter after task: " + counterExercise.getCounter());
    }

    void employeesExecution() {
        List<Employee> employees = SampleEmployees.getSampleEmployees();
        /*
         * System.out.println("Older than 30");
         * for(Employee var:employees){ //O(n)
         * if(var.getAge() > 30){
         * System.out.println(" "+var.getName());
         * }
         * }
         */

        System.out.println("Older than 30 again with streams and methods");
        ExercisesEmployees.printEmployeeNames(ExercisesEmployees.getEmployeesOlderThan30(employees));

        System.out.println("Biggest salary");
        System.out.println(" " + ExercisesEmployees.getEmployeeWithBiggestSalary(employees).orElse("ninguno"));

        System.out.println("List with all employees");
        ExercisesEmployees.printEmployeeNames(employees);

        System.out.println("Average Salary");
        System.out.println(" " + ExercisesEmployees.getAverageSalary(employees));

        System.out.println("Employees by salary descending");
        ExercisesEmployees.printEmployeeNames(ExercisesEmployees.getEmployeesBySalaryDescending(employees));

        System.out.println("Sorting by bubble method");
        ExercisesEmployees.printEmployeeNames(ExercisesEmployees.getEmployeesBySalaryDexcendingBubble(employees));
    }

}
