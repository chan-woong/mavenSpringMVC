package com.java.fileboard.dao;

import java.util.HashMap;
import java.util.List;
import com.java.fileboard.dto.FileBoardDto;

public interface FileBoardDao {
  public int fileBoardGroupNumberMax();

  public int fileBoardWriteNumber(HashMap<String, Integer> hMap);

  public int fileBoardWriteNumber(FileBoardDto fileBoardDto);
  
  public int getCount();
  
  public List<FileBoardDto> fileBoardList(int startRow, int endRow);
  
  public FileBoardDto fileRead(int boardNumber);
  
  public FileBoardDto fileBoardSelect(int boardNumber);
  
  public int update(FileBoardDto fileBoardDto);

  public int fileBoardDeleteOk(int boardNumber,String password);
}
