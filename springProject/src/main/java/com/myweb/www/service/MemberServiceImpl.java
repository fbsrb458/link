package com.myweb.www.service;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.myweb.www.repository.MemberDAO;
import com.myweb.www.security.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
	@Inject
	private MemberDAO mdao;
	
	@Transactional
	@Override
	public int register(MemberVO mvo) {
		log.info("register 2");
		int isOk = mdao.insertMember(mvo);
		return mdao.insertAuthInit(mvo.getEmail());
	}

	@Override
	public boolean updateLastLogin(String authEmail) {
		log.info("updateLastLogin 2");
		return mdao.updateLestLogin(authEmail) > 0 ? true: false; //왜 0보다 크냐 성공하면 1이기떄문에
	}

	@Override
	public List<MemberVO> getList() {
		log.info("Member getList 2");
		
		return mdao.getList();
	}
	
	@Transactional
	@Override
	public MemberVO detail(String email) {
		log.info("Member detail 2");
		MemberVO mvo = mdao.selectEmail(email);
		mvo.setAuthList(mdao.selectAuths(email));
		return mvo;
	}

	@Override
	public int modifyPwdEmpty(MemberVO mvo) {
		log.info("Member modifyPwdEmpty 2");
		
		return mdao.modifyPwdEmpty(mvo);
	}

	@Override
	public int modify(MemberVO mvo) {
		log.info("Member modify 2");
		return mdao.modify(mvo);
	}

	@Override
	public int remove(String email) {
		mdao.removeAuth(email);
		return mdao.remove(email);
	}

	




	
	
	
}
