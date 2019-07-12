package com.nexters.cultureland;

import com.nexters.cultureland.model.Culture;
import com.nexters.cultureland.repo.CultureRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CulturelandApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CulturelandApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
	}
}
