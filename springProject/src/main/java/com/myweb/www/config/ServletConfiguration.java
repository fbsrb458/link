package com.myweb.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.expression.StandardBeanExpressionResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@ComponentScan(basePackages = {"com.myweb.www.controller","com.myweb.www.handler"})
public class ServletConfiguration implements WebMvcConfigurer{
	//servlet-context.xml
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		//나중에 파일 업로드 경로 추가
		registry.addResourceHandler("/upload/**").addResourceLocations("file:///D:\\_myweb\\_java\\fileupload\\");
		
	}
	
	//<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	//<beans:property name="prefix" value="/WEB-INF/views/" />
	//<beans:property name="suffix" value=".jsp" />
	//</beans:bean>
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver =
				new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
		
	}

	
	//<beans:bean id="multipartResolver" class="
	//org.springframework.web.multipart.support.StandardServletMultipartResolver"></beans:bean>
	
	
	//bean으로 multipartResolver 설정	
	@Bean(name = "multipartResolver")
	public MultipartResolver getMultipartResolver() {
		StandardServletMultipartResolver multExpressionResolver = 
				new StandardServletMultipartResolver();
		return multExpressionResolver;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
