package com.studyboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudybootApplication {

	public static void main(String[] args) {

		ApplicationContext act = SpringApplication.run(StudybootApplication.class, args);


		for(String name  : act.getBeanDefinitionNames()){
			System.out.println(name);
		}
	}


	@Bean
	CommandLineRunner commandLineRunner(){
		return (args)->{
			System.out.println("in commandLineRunner ");
			System.out.println(args);
		};
	}

}
