package th.ac.ku.kinkao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class VeggieApplication {

	public static void main(String[] args) {
		SpringApplication.run(VeggieApplication.class, args);
	}

}
