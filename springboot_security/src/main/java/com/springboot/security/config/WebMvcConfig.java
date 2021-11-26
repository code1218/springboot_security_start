package com.springboot.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Value("${file.path.profile}")
	private String profileForder;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry
			.addResourceHandler("/image/**")
			.addResourceLocations("file:///" + profileForder)
			.setCachePeriod(60*60) //캐시 유지시간(60초 * 60 = 1시간)
			.resourceChain(true)
			.addResolver(new PathResourceResolver());
	}
}
