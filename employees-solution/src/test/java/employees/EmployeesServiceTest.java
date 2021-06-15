package employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeesServiceTest {

    EmployeesService employeesService = new EmployeesService(new MySqlEmployeesRepository());

    @BeforeEach
    void init() {
        employeesService.deleteAll();
    }

    @Test
    void testSaveThanList() {

        employeesService.save("John Doe");

        List<Employee> employees = employeesService.listEmployee();
        assertEquals(1, employees.size());
        assertEquals("john Doe", employees.get(0).getName());
    }

    @Test
    void testSaveTwoThanList() {


        employeesService.deleteAll();

        employeesService.save("John Doe");
        employeesService.save("Jane Doe");

        List<Employee> employees = employeesService.listEmployee();
        assertEquals(2, employees.size());
        assertEquals("john Doe", employees.get(0).getName());
        assertEquals("jane Doe", employees.get(1).getName());
    }

    @Test
    void listEmployee() {
    }
}