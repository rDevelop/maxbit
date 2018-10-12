package us.rlit;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import us.rlit.web.users.MaxUsers;

/**
 * @author Rob Litvin
 */
@SpringBootApplication
public class MaxApplication {

    public static void main(String[] args) {
        new MaxUsers().createUsers();
        ConfigurableApplicationContext ctx = SpringApplication.run(MaxApplication.class, args);
    }


}