package com.gn.mvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MvcApplication implements WebMvcConfigurer{
	
	// 정적으로 다른곳에서 쉽게 불러서 쓸 수 있게 (불필요한 반복작업+)
	@Value("${ffupload.location}")
	private String fileDir;
	
	
	public static void main(String[] args) {
		SpringApplication.run(MvcApplication.class, args);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/uploads/**")
			.addResourceLocations("file:///"+fileDir);
	}
	
}
