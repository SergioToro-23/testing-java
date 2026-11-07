import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        App app = new App();
        app.run();
    }
    
    void run() {

        List<Employee> employees = SampleEmployees.getSampleEmployees();

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

}
