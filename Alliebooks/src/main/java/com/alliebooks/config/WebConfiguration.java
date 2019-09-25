package com.alliebooks.config;
//package com.alliebooks.config;
//
//import java.util.concurrent.TimeUnit;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.CacheControl;
//import org.springframework.web.multipart.MultipartResolver;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.resource.WebJarsResourceResolver;
//
//@EnableWebMvc
//@Configuration
//@ComponentScan(basePackages = { "com.alliebooks.controller"})
//public class WebConfiguration implements WebMvcConfigurer {
//	@Bean
//	public MultipartResolver multipartResolver() {
//		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
//		commonsMultipartResolver.setDefaultEncoding("utf-8");
//		commonsMultipartResolver.setMaxUploadSize(20000000);
//		commonsMultipartResolver.setResolveLazily(false);
//		return commonsMultipartResolver;
//	}
//
//	
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
//        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
//		registry.addResourceHandler("/static/**")
//	        .addResourceLocations("/resources/", "/webjars/")
//	        .setCacheControl(
//	                CacheControl.maxAge(30L, TimeUnit.DAYS).cachePublic())
//	        .resourceChain(true)
//	        .addResolver(new WebJarsResourceResolver());
//	}
//}