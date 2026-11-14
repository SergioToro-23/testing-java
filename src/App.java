import java.time.Clock;
import java.util.List;

/*
Problem: Customer Lookup by Any Identifier

Context
A customer-support team maintains a list of customers. Each customer has three identifiers:
Field	Example
ecid	ECID-1001
email	Jane.Doe@example.com
phone	(206) 555-1234
Support agents look up customers by typing whatever identifier they have on hand — sometimes the ECID, sometimes the email, sometimes a phone number. They don't tell the system which type it is.

Task
Write a method:
 findCustomer()
that finds a customer given a single identifier string, auto-detecting whether it is an ECID, an email, or a phone number.

Requirements
1. ECID — match case-insensitively (ecid-1001 ≡ ECID-1001).
2. Email — match case-insensitively.
3. Phone — agents enter numbers in many formats; all of these must match the same customer:
   - (206) 555-1234
   - 206-555-1234
   - 206.555.1234
   - +1 206 555 1234
   - 2065551234
4. Robustness — null or blank input, or an identifier that matches no known type, must return Optional.empty() (no exceptions).
5. Also provide a convenience method findCustomerExists(...) that returns a boolean.
6. write a few tests

*/


public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        App app = new App();
        app.run();
    }

    void run() {
        // employeesExecution();
        // concurrencyExecution();
        // FibonacciExecution();
        // primeNumbers();
        /*
        Clock clock = Clock.systemUTC();
        RateLimiter limit = new RateLimiter(5,clock);//bean singleton to not change the map of the users using more instances
        System.out.println(limit.allow("12345"));
        */
        CustomerService customerService = new CustomerService();
        customerService.findCustomer();
    }

    void concurrencyExecution() {
        CounterExerciseClass counterExercise = new CounterExerciseClass();
        Runnable task1 = () -> {

            System.out.println("Executing task1");
            for (int i = 0; i < 100000; i++) {
                counterExercise.increment();
            }
        };
        Runnable task2 = () -> {
            System.out.println("Executing task2");
            for (int i = 0; i < 100000; i++) {
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
            e.printStackTrace();
        }
        System.out.println("Counter after task: " + counterExercise.getCounter());
    }

    void employeesExecution() {
        List<Employee> employees = SampleEmployees.getSampleEmployees();        

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

    void FibonacciExecution() {
        int num1 = 0, num2 = 1, aux = 0;
        System.out.println("Fibonacci sequence: ");
        System.out.println("0");
        for (int i = 0; i < 50; i++) {
            aux = num1 + num2;
            num2 = num1;
            num1 = aux;
            System.out.println(num1);
        }
    }

    void primeNumbers() {
        System.out.println("Prime numbers: ");
        for (int i = 1; i <= 100; i++) {
            if (isPrime(i)) {
                System.out.println(i);
            }
        }

    }

    Boolean isPrime(int num) {
        if (num <= 1) {            
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {                
                return false;
            }
        }
        return true;
    }
}
