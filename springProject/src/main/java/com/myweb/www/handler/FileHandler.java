package com.myweb.www.handler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.FileVO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Component
public class FileHandler {
	private final String UP_DIR = "D:\\_myweb\\_java\\fileupload";
	
	public List<FileVO> uploadFiles(MultipartFile[] files){
		List<FileVO> flist = new ArrayList<>();
		//중간의 파일경로, fvo, set, 파일저장....
		//날짜를 폴더로 생성하여 그날그날 업로드 파일을 관리
		LocalDate date = LocalDate.now(); //localDate 객체
		String today = date.toString(); //2023-10-24
		today = today.replace("-", File.separator); //2023\10\24(win) 2023/10/24(linux, mac)
		
		
		File folders = new File(UP_DIR, today);
		
		if(!folders.exists()) {//!없다면 만든다.
			
			folders.mkdirs(); //mkdirs: 하위 폴더 전체 선택  mkdir : 하위 폴더 단일 선택
		}
		
		
		//files 객체에 대한 설정
		for(MultipartFile file : files) { //들어온 첨부 파일 1개씩 for문 처리
			FileVO fvo = new FileVO();
			//전체 경로는 설정 되어 있기 떄문에 today로만 (나머지는 뒷경로)설정
			fvo.setSaveDir(today);
			fvo.setFileSize(file.getSize());
			//실체 파일이름
			String originalFileName = file.getOriginalFilename();
			log.info(">>>11111 original >>> "+originalFileName);
			//파일 이름에 경로가 존재 할수도 있음
			String fileName = originalFileName.substring(
					originalFileName.lastIndexOf(File.separator)+1);
			log.info(">>>>11111 fileName"+fileName);
			fvo.setFileName(fileName);
			
			UUID uuid = UUID.randomUUID();
			fvo.setUuid(uuid.toString());
			//기본 FileVO 생성 완료 --------------------------------------
	
			//하단부터 디스크에 저장할 파일 객체 생성
			//파일이름 uuid_fileName uuid_th_fileName
			String fullFileName = uuid.toString()+"_"+fileName;
			//파일 객체 생성(저장파일)
			
			File storeFile = new File(folders, fullFileName);
			//file 객체가 저장이 되러면 첫 경로부터 다 설정이 되어 있어야함.

			
			try {
				file.transferTo(storeFile); //저장
				//썸내일 생성 => 이미지 파일만 썸내일 생성
				//이미지 파일인지 확인
				if(isImageFile(storeFile)) {
					fvo.setFileType(1);
					//썸내일 생성
					File thumbNail = new File(folders, 
							uuid.toString()+"_th_"+fileName);
					//Thumbnails.of 사이즈 75로 만들고 thumbNail 경로 저장
					Thumbnails.of(storeFile).size(75, 75).toFile(thumbNail);
				}
				
			}catch (Exception e) {
				// 
				log.debug(">>> file 생성 오류~!!");
				e.printStackTrace();
			}
			
			//flist에 fvo 추가 
			flist.add(fvo);
		
			
		}
		
		
		
		
		
		
		
		return flist;
	}
	
	private boolean isImageFile(File storeFile) throws IOException{
		String mimeType = new Tika().detect(storeFile); //image/jsp
		return mimeType.startsWith("image") ? true : false;
	}
	
}
