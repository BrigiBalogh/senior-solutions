package employees;

import java.util.Scanner;

public class EmployeesController {



    // csak itt scanner, sout
    // nem csinál mást ez az oszt. csak bekkér adatokat,és továbbhív egy másik oszt-ba.
    private Scanner scanner = new Scanner(System.in);

   // private EmployeesService employeesService = new EmployeesService(new MySqlEmployeesRepository());

    private EmployeesService employeesService = new EmployeesService(new InMemoryEmployeesRepository());

    public static void main(String[] args) {
        new EmployeesController().start();
    }

    public void start(){
        System.out.println("");

        for (int i = 0; i <5; i++) {
            String name = scanner.nextLine();
            employeesService.save(name);
        }

        System.out.println(employeesService.listEmployee());
    }


}
