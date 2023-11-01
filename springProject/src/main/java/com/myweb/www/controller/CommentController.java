package com.myweb.www.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/comment/*")
@RestController
public class CommentController {
	
	@Inject
	private CommentService csv;
	
	//ResponseEntity 객체 사용
		//@RequestBody : body값 추출
		//value = "mappling name" , consumes : 가져오는 데이터의 형태
		//produces : 보내는 데이터의 형태(형식) 	나가는 데이터 타입: MediaType.
		// json : application/json text : text_plain
		
	@PostMapping(value = "/post", consumes ="application/json", 
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> post(@RequestBody CommentVO cvo){
		log.info(">>>>> cvo >> "+cvo);
		//DB연결
		int isOk = csv.post(cvo);
		
		//리턴시 respose의 통신 상태를 같이 리턴한다.
		return isOk>0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@GetMapping(value = "/{bno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE) //들어오는 값만있음
	public ResponseEntity<PagingHandler> spread(@PathVariable("bno") int bno, 
			@PathVariable("page")int page){
		log.info(">>>>> comment List bno / page>> "+ bno+"    "+page);
		
		//DB 요청
		PagingVO pgvo = new PagingVO(page, 5);
		PagingHandler ph = csv.getList(bno, pgvo);
		log.info(" >>> "+ph);
		return new ResponseEntity<PagingHandler>(ph,HttpStatus.OK);
	}
	
	
	
	// 삭제 
	// 들어오는 값은 X
	// 나가는 값 : produces(String)
	@DeleteMapping(value = "/{cno}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> remove(@PathVariable("cno")int cno) 
	{
		log.info(">>>> cno / cvo >> "+cno+"  /  "+cno);
		
		int isOk = csv.remove(cno);
		return isOk > 0? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	//수정
	@PutMapping(value = "/{cno}", consumes = "application/json", 
		produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> edit(@PathVariable("cno")int cno,
			@RequestBody CommentVO cvo) {
		log.info(">>>> cno / cvo >> "+cno+" / "+cvo);
		int isOk = csv.edit(cvo);
		return isOk > 0? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
}
