package com.slokam.Myproject.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.slokam.FirstProgram.Util.StringUtil;

@SpringBootApplication

// When We Provide all packages as child Class of the main package then not required to provide these below three annotations.
@EnableJpaRepositories({"com.slokam.FirstProgram.Dao"})
@ComponentScan({"com.slokam.FirstProgram.Controller","com.slokam.FirstProgram.Service","com.slokam.FirstProgram.Util"})
@EntityScan({"com.slokam.FirstProgram.Pojo"})
public class FirstProgramApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstProgramApplication.class, args);
	}
	
	@Bean
	public StringUtil stringUtil()
	{
		return new StringUtil();
		
	}
}
