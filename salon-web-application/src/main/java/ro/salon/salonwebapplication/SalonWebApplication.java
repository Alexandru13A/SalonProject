package ro.salon.salonwebapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan( "ro.salon.common.entity")
public class SalonWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalonWebApplication.class, args);
	}

}
