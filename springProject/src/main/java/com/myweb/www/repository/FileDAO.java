package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.FileVO;

public interface FileDAO {

	int insertFile(FileVO fvo);

	//List<FileVO> getDetailFile(long bno);

	List<FileVO> getFileList(long bno);

	List<FileVO> getDetailFile(long bno);

	int removeFile(String uuid);

	long getCnt(long bno);

	void fileremove(long bno);

	List<FileVO> selectListAllFiles();

	

	


}
