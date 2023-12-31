package com.myweb.www.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.security.MemberVO;
import com.myweb.www.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member/**")
@Controller
public class MemberController {
	
	@Inject
	private BCryptPasswordEncoder bcEncoder;
	
	@Inject
	private MemberService msv;
	
	
	@GetMapping("/register")
	public void register() {}
	
	
	
	@PostMapping("/register")
	public String register(MemberVO mvo) {
		mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
		int isOk = msv.register(mvo);
		return "index";
	}
	
	@GetMapping("/login")
	public void login() {}
	
	
	@PostMapping("/login")
	public String loginPost(HttpServletRequest request,
			RedirectAttributes re) {
		//로그인 실패시 다시 로그인 페이지로 돌아와 오류 메시지 전송
		//디사 로그인 유도
		log.info("컨트롤러 로그인"+request.getAttribute("errMsg"));
		re.addAttribute("errMsg", request.getAttribute("errMsg"));
		re.addAttribute("email", request.getAttribute("email"));
		return "redirect:/member/login";
	}
	
	@GetMapping("list")
	public String getList(Model m) {
		List<MemberVO> memList = msv.getList();
		m.addAttribute("memList", memList);
		log.info("member list"+memList);

		
		return "/member/list";
	}
	
	
	
//	@GetMapping("/list") 선생님
//	public void memberList(Model m) {
//		m.addAttribute("memList", msv.selectList());
//	}
	
	
	@GetMapping("/modify")
	public void modify(@RequestParam("email") String email, Model m) {
		log.info(">>>>> modify >> email >> "+ email);
		MemberVO memList = msv.detail(email);
		m.addAttribute("mvo", msv.detail(email));
	}
	
	
	
	
	
	@PostMapping("/modify")
	public String modify(MemberVO mvo, Model m, HttpServletRequest req, HttpServletResponse res) {
		log.info(">>>>> modify >> mvo >> "+ mvo);
		int isOk=3;
		if(mvo.getPwd().isEmpty()) {
			isOk = msv.modifyPwdEmpty(mvo);
		}else {
			mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
			isOk = msv.modify(mvo);
		}

		logout(req, res);
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		new SecurityContextLogoutHandler().logout(req, res, authentication);
		m.addAttribute("isOk", isOk);
		
		return "/member/login";
	}
	
	
	@GetMapping("/remove")
	public String removeMember(@RequestParam("email") String email, Model m, 
			HttpServletRequest req, HttpServletResponse res) {
		log.info(">>>>> modify >> email >> "+ email);
		int isOk = msv.remove(email);
		logout(req, res);
		m.addAttribute("isOkDel", isOk);
		return "index";
	}
	
	private void logout(HttpServletRequest req, HttpServletResponse res) {
		Authentication authentication = SecurityContextHolder
				.getContext().getAuthentication();
		new SecurityContextLogoutHandler().logout(req, res, authentication);
	}


	
	
}
