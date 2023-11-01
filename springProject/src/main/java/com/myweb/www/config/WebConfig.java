package com.myweb.www.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//web.xml
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{
	
	//클래스 객체를 배열로 리턴 (클래스가 여러개 리턴될수 있다.)
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {RootConfig.class, SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {ServletConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}

	@Override
	protected Filter[] getServletFilters() {
	// encoding filter 설정
	// 내가원하는 설정의 인코딩 필터가 어니면 내가 원하는 설정으로 바꿔야함
	CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
	//인코딩 설정
	encodingFilter.setEncoding("utf-8");
	//setEncoding : 들어올떄 값을 처리한다.
	//setForceEncoding: 나갈떄 값을처리한다.
	encodingFilter.setForceEncoding(true);  // 외부에서 나가는 데이터도  인코딩 설정
	//true 값을 처리한다. false 값을 처리하지않는다.
	return new Filter[] {encodingFilter};
	}

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		// 그 외 기타 사용자 설정
		// 사용자 지정 익셉션 설정을 할 것인지 처리
		registration.setInitParameter("throwExceptionIfNotHandlerFound","true");
		
		
		// 파일 업로드 설정
		// 경로, maxFileSize, maxReqSize, fileSizeThreshold (클래스 파일이기때문에 계산가능)
		String uploadLocation = "D:\\_myweb\\_java\\fileupload";
		int maxFileSize = 1024*1024*20; //20MB
		int maxReqSize = maxFileSize*2; //40MB
		int fileSizeThreshold = maxFileSize; //20MB
		
		MultipartConfigElement multipartConfig = 
				new MultipartConfigElement(uploadLocation,maxFileSize,maxReqSize,fileSizeThreshold);
		
		registration.setMultipartConfig(multipartConfig);
		
	}
	
	
	
	
}
