package org.salon.salonadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Administrator
 */
@SpringBootApplication
@ComponentScan(value = "org.salon")
public class SalonAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalonAdminApplication.class, args);
    }

}
