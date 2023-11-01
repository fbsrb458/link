package com.myweb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;

public interface CommentDAO {

	int insert(CommentVO cvo);

	List<CommentVO> getList(int bno);

	int remove(int cno);

	int update(CommentVO cvo);

	int selectOntBnoTotalCount(long bno);

	
	//파리미터을 2개보낼때는 @Param을 붙어야 전달이 된다 예외는 없다.

	List<CommentVO> selectListPaging(@Param("bno") long bno, @Param("pgvo")PagingVO pgvo);

	void commentremove(long bno);

	

}
