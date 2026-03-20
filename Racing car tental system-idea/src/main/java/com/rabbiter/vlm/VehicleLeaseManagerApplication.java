package com.rabbiter.vlm;

import com.rabbiter.vlm.config.StartupExceptionHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Racing Car Rental System
 * Main Application Entry
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.rabbiter.vlm.dao"})
public class VehicleLeaseManagerApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(VehicleLeaseManagerApplication.class);
		application.addListeners(new StartupExceptionHandler());
		application.run(args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
        return applicationBuilder.sources(VehicleLeaseManagerApplication.class);
    }
}
