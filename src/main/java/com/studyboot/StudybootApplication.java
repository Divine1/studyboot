package com.studyboot;

import com.studyboot.service.JsonPlaceholderService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class StudybootApplication {

	Logger log = LoggerFactory.getLogger(StudybootApplication.class);
	public static void main(String[] args) {

		ApplicationContext act = SpringApplication.run(StudybootApplication.class, args);


		//for(String name  : act.getBeanDefinitionNames()){
		//	System.out.println(name);
		//}
	}


	@Bean
	CommandLineRunner commandLineRunner(){
		return (args)->{
			//System.out.println("in commandLineRunner ");
			//System.out.println(args);


		};
	}

	@Bean
	JsonPlaceholderService jsonPlaceholderService(){
		RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");
		HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
		return httpServiceProxyFactory.createClient(JsonPlaceholderService.class);
	}

}
