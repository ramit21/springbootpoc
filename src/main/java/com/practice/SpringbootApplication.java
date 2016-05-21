package com.practice;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.practice.jpa.ReservationRepository;
import com.practice.model.Reservation;

@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(SpringbootApplication.class, args);
		System.out.println("Let's inspect the beans provided by Spring Boot:");
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
	}

	@Bean
	CommandLineRunner runner(final ReservationRepository repo) {
		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {
				List<String> names = Arrays.asList("Amit,Ramit,Sumit,Rohit,Virat".split(","));
				for (String name : names) {
					repo.save(new Reservation(name));
				}
				List<Reservation> reservations = repo.findAll();
				for (Reservation reservation : reservations) {
					System.out.println(reservation);
				}
			}
		};
	}
}
