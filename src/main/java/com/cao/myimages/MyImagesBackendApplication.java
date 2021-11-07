package com.cao.myimages;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@MapperScan("com.cao.myimages.mapper")
public class MyImagesBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyImagesBackendApplication.class, args);
	}

}
