package com.myweb.www;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;
//-----------------------------------------------기본적으로 설정해야한는 것 
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)//실행이 되도록 도와주는역할
@ContextConfiguration(classes = {com.myweb.www.config.RootConfig.class})
public class BoardTest {
	//DB권한 X => context 설정
	
	@Inject
	private BoardDAO bdao;
	
	@Test
	public void insertBoard() {
		for(int i=0; i<300; i++) {
			BoardVO bvo = new BoardVO();
			bvo.setTitle("Test Title"+i);
			bvo.setWriter("tester"+(int)((Math.random()*30)+1));
			bvo.setContent("Test Content"+i);
			bdao.insert(bvo); // bdao에게 보낸다.
		}
	}
}
