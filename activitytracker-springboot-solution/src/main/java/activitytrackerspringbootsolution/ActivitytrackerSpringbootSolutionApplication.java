package activitytrackerspringbootsolution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class ActivitytrackerSpringbootSolutionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivitytrackerSpringbootSolutionApplication.class, args);
    }

}
