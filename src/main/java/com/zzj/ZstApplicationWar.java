package com.zzj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//打包必须放开注解
@SpringBootApplication
@RestController
@MapperScan("com.zzj.mapper")
public class ZstApplicationWar extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ZstApplicationWar.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ZstApplicationWar.class);
	}

}
