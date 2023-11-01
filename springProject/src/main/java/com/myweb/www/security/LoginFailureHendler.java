package com.myweb.www.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Getter
@Setter
public class LoginFailureHendler implements AuthenticationFailureHandler {
	private String authEamil;
	private String errorMassage;
	
	
	//exception : 인증실패
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		//실패한 이메일
		setAuthEamil(request.getParameter("email")); //SecurityConfig
		
		
		//exception 발생시 메시지 저장
		//BadCredentialsException : 비밀번호가 불일치
		if(exception instanceof BadCredentialsException ||
				exception instanceof InternalAuthenticationServiceException) {
			setErrorMassage(exception.getMessage().toString());
		}
		log.info(">>>> errMsg1"+ getErrorMassage());
		request.setAttribute("email", getAuthEamil());
		request.setAttribute("errMsg", getErrorMassage());
		//에러 메세지 가지고 /memger/login로 이동 => 컨트롤러 값을 가져감
		log.info(">>>> errMsg2");
		request.getRequestDispatcher("/member/login?error").forward(request, response);

	
	}

}
