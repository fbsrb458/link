package com.myweb.www.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class AuthMember  extends User{
	private static final long serialVersionUID=1L;
	
	//인증용 객체 => email, pwd
	private MemberVO mvo;
	
	//extends : 상속
	//생성자
	public AuthMember(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		
	}
	
	//생성자
	public AuthMember(MemberVO mvo) {
		super(mvo.getEmail(), mvo.getPwd(),
				mvo.getAuthList() // mvo.getAuthList() : 컬렉션 형식이 안맞아서 변환
				.stream()
				.map(authVO -> new SimpleGrantedAuthority(authVO.getAuth()))
				.collect(Collectors.toList()));
		
		//결론은 mvo.getEmail(), mvo.getPwd() //형식을 바꾼 mvo.getAuthList() 과 모두 함께 리턴임

		this.mvo = mvo;
	
	}

}
