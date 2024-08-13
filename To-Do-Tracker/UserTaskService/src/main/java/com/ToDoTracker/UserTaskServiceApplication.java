package com.ToDoTracker;

import com.ToDoTracker.filter.Filter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
//@EnableDiscoveryClient
public class UserTaskServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserTaskServiceApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean jwtFilter()
	{
		FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new Filter());
		filterRegistrationBean.addUrlPatterns("/api/v1/task/*");

		return filterRegistrationBean;
	}
}
