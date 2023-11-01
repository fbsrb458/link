package com.myweb.www.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.myweb.www.repository.MemberDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthMemberService implements UserDetailsService {
	
	@Inject
	private MemberDAO mdao;
	
	
	@Override //username : email
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// username DB에 설정되어있는 email인지를 체크해서
		// 인증하여 해당 객체를 AuthMember로 리턴
		
		MemberVO mvo = mdao.selectEmail(username); //DB에서 해당 이메일이 존재하는지 체크 해서 객체 받음
		if(mvo == null) {
			throw new UsernameNotFoundException(username);
		}
		mvo.setAuthList(mdao.selectAuths(username)); //set해서 채우고 전달
		log.info(">>>>>>>>>>>>>>>>>>>>>> usreDetaile 이메일 "+mvo);
		
		
		return new AuthMember(mvo); //mvo: 로그인 객체 로그인 객체의 권한을 받아서 리턴
	}

}
