package com.java.fileboard.dao;

import java.util.HashMap;
import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.java.aop.LogAspect;
import com.java.fileboard.dto.FileBoardDto;

@Component
public class FileBoardDaoImp implements FileBoardDao {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate;

  @Override
  public int fileBoardGroupNumberMax() {
    return sqlSessionTemplate.selectOne("boardGroupNumberMax");
  }

  @Override
  public int fileBoardWriteNumber(HashMap<String, Integer> hMap) {
    return sqlSessionTemplate.update("boardWriterNumber", hMap);
  }

  @Override
  public int fileBoardWriteNumber(FileBoardDto fileBoardDto) {
    int check;

    if (fileBoardDto.getFileSize() == 0) {
      check = sqlSessionTemplate.insert("boardInsert", fileBoardDto);
    } else {
      check = sqlSessionTemplate.insert("fileboardInsert", fileBoardDto);
    }
    return check;
  }

  public int getCount() {
    return sqlSessionTemplate.selectOne("boardCount");
  }

  @Override
  public List<FileBoardDto> fileBoardList(int startRow, int endRow) {
    HashMap<String, Integer> hMap = new HashMap<String, Integer>();
    hMap.put("startRow", startRow);
    hMap.put("endRow", endRow);

    return sqlSessionTemplate.selectList("boardList", hMap);
  }

  @Override
  public FileBoardDto fileRead(int boardNumber) {
    FileBoardDto fileBoardDto = null;

    int check = sqlSessionTemplate.update("boardReadCount", boardNumber);
    LogAspect.logger.info(LogAspect.LogMsg + check);

    fileBoardDto = sqlSessionTemplate.selectOne("fileBoardRead", boardNumber);

    return fileBoardDto;
  }

  @Override
  public FileBoardDto fileBoardSelect(int boardNumber) {
    return sqlSessionTemplate.selectOne("fileBoardRead", boardNumber);
  }

  @Override
  public int update(FileBoardDto fileBoardDto) {
    int check = 0;
    if (fileBoardDto.getFileName() == null) {
      check = sqlSessionTemplate.update("boardUpdate", fileBoardDto);
    } else {
      check = sqlSessionTemplate.update("fileboardUpdate", fileBoardDto);
    }
    return check;
  }
  
  @Override
  public int fileBoardDeleteOk(int boardNumber, String password) {
    HashMap<String, Object> hMap = new HashMap<String, Object>();
    hMap.put("boardNumber", boardNumber);
    hMap.put("password", password);

    return sqlSessionTemplate.delete("boardDelete", hMap);
  }
}
