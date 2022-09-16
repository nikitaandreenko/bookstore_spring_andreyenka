package com.company;

import com.company.controller.filter.AuthorizationFilter;
import com.company.controller.interceptor.MyInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BookstoreAndreyenkaApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreAndreyenkaApplication.class, args);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(myInterceptor())
				.addPathPatterns("/**");
	}

	@Bean
	public MyInterceptor myInterceptor() {
		return new MyInterceptor();
	}

	@Bean
	public FilterRegistrationBean<AuthorizationFilter> authorizationFilter() {
		FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new AuthorizationFilter());
		registrationBean.addUrlPatterns("/users/getAll", "/users/create", "/users/delete",
				"/books/delete", "/books/delete", "/orders/getAll", "/cart/cart");
		registrationBean.setOrder(2);
		return registrationBean;
	}
}