package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardService {

//	int register(BoardVO bvo);

	List<BoardVO> getList(PagingVO pagingVO);

	BoardVO getDetail(long bno);

	int modify(BoardVO bvo);

	int remove(int bno);

	int getTotalCount(PagingVO pagingVO);

	int register(BoardDTO boardDTO);

	BoardDTO getDetail2(long bno);

	int removeFile(String uuid);

	int modifyFile(BoardDTO bdto);



	

}
