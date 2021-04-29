package hu.nive.ujratervezes.kepesitovizsga.jurassic;
/*
import com.mysql.cj.jdbc.MysqlDataSource;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JurassicParkTest {


    private JurassicPark jurassicPark;

    @BeforeEach
    void init() throws SQLException {
        MysqlDataSource dataSource;
        dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/employees?useUnicode=true");
        dataSource.setUser("employees");
        dataSource.setPassword("employees");

        Flyway flyway = Flyway.configure().dataSource(dataSource).load();

        flyway.clean();
        flyway.migrate();

        jurassicPark = new JurassicPark(dataSource);

    }


    @Test
    void test_check() {
        assertEquals(List.of("Hypsilophodontida", "Maiasaurus", "Velociraptor"), jurassicPark.checkOverpopulation());
    }


}*/