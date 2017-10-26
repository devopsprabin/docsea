package org.itglance.docsea;

import javax.servlet.Filter;

import org.itglance.docsea.config.SessionCheckerFilter;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ServerApplication.class);
	}

	@Bean
	public Filter someFilter(){
		SessionCheckerFilter h= new SessionCheckerFilter();
		return h;
	}

	@Bean
	public FilterRegistrationBean someFilterRegistration(){
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(someFilter());
		registration.addUrlPatterns("/api/doctors/soctor");
		return registration;
	}
}

///api/doctors","/api/doctors/addSchedules/*","/api/hospitalDoctors/*/*","/api/schedules
