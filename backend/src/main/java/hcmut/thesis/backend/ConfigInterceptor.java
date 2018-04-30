/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author MinBui
 */
@Configuration
@EnableWebMvc
public class ConfigInterceptor implements WebMvcConfigurer {
    @Autowired
	RequestInterceptor requestInterceptor;
	
    @Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestInterceptor);
	}
}
