package com.myweb.www.handler;

import java.util.List;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;




@ToString
@Setter
@Getter
public class PagingHandler {

	//1~10 / 11~20 / 21~30...
	//화면에 보여지는 시작 페이지네이션 번호
	private int StartPage;
	private int endPage;
	private int realEndPage;
	private boolean prev, next; //이전 , 다음의 존재여부
	
	private int totalCount; //총글수
	private PagingVO pgvo;
	
	private List<CommentVO> cmtList; //1023
	
	
	//현재 페이지 값 가져오는 용도 / totalcount DB에서 조회 매개변수로 입력 받기
	public PagingHandler(PagingVO pgvo, int totalCount) {
		//pgvo.qty : 한페이지에 보이는 게시글의 수 10개
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		//1~10 / 11~20 / 21~30..
		//1페이지부터 10페이지까지 어떤 페이지가 선택되고 EndPage는 10여야한다.
		//11페이지부터 20페이지까지 어떤 페이지가 선택되고 EndPage는 20여야한다.
		//1 2 3 4 ... 10 /10으로 나누어서 나머지을 올려(ceil) 1로 만듬 *10 
		//화면에 표시되어야 하는 페이지 개수 (1 2 3 4 5 6 7 8 9 10) =10개
		 // 10 20 30 화면에 표시되어야 하는 페이지 개수 조절할거면 endPage에서 설정해야한다.
		this.endPage = 
				(int)Math.ceil(pgvo.getPageNo() / (double)pgvo.getQty())*pgvo.getQty();
		
		
		this.StartPage = endPage - 9; //1 11 21
		
		//전체 글수에서 / 한페이지에 표시되는 게시글수 올림(올려야하는이유는 페이지 개수가 딱 들어맞지않을수가있으므로 하나을 올려야함 7개만 있을수도있어서)
		this.realEndPage = (int)Math.ceil(totalCount/(double)pgvo.getQty());
		if (realEndPage < endPage) { //realEndPage 실제 마지막 페이지가 되어야한다.
			this.endPage = realEndPage;
		}
		
		this.prev = this.StartPage > 1;
		this.next = this.endPage < realEndPage;
	}
	
		public PagingHandler(PagingVO pgvo, int totalCount, List<CommentVO> cmtList) {
			this(pgvo, totalCount);
			this.cmtList = cmtList;
		}
	
}
