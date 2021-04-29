package hu.nive.ujratervezes.kepesitovizsga.jurassic;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JurassicPark {

private final DataSource  dataSource;

    public JurassicPark(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<String>  checkOverpopulation () {

        List<String > names = new ArrayList<>();

            try (Connection conn = dataSource.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("select 'breed' from 'dinosaur'  where 'actual' > 'expected'  order by'breed' ")
            ) {
                while (rs.next()) {
                    String name = rs.getString("breed");
                    names.add(name);
                }
                return names;
            }
            catch (SQLException se) {
                throw new IllegalStateException("Cannot select employees", se);
            }
    }
}
