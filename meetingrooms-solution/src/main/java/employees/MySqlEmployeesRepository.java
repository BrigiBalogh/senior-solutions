package employees;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

public class MySqlEmployeesRepository implements EmployeesRepository {

    private JdbcTemplate jdbcTemplate;

    public MySqlEmployeesRepository()  {
        try{
            MysqlDataSource dataSource;
          dataSource = new MysqlDataSource();
          dataSource.setUrl("jdbc:mysql://localhost:3306/employees?useUnicode=true");
          dataSource.setUser("employees");
          dataSource.setPassword("employees");

            Flyway flyway = Flyway.configure().dataSource(dataSource).load();

            flyway.clean();
            flyway.migrate();
            jdbcTemplate = new JdbcTemplate(dataSource);

      } catch (SQLException se) {
          throw new IllegalStateException("Cannot create datasource", se);
      }

    }

    @Override
    public void save(String name) {
        jdbcTemplate.update("insert into employees(emp_name) values(?)", name);

    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query("select id, emp_name from employees order By emp_name",
                (rs,i) -> new Employee(rs.getLong("id"), rs.getString("emp_name")));
    }


    @Override
    public void deleteAll() {
        jdbcTemplate.update("delete from employees");
    }
}
