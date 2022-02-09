package com.java.board.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.java.aop.LogAspect;
import com.java.board.dto.BoardDto;

@Component
public class BoardDaoImp implements BoardDao {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate;



	@Override
	public int boardGroupNumberMax() {
		return sqlSessionTemplate.selectOne("boardGroupNumberMax");
	}

	@Override
	public int boardWriteNumber(HashMap<String, Integer> hMap) {
		return sqlSessionTemplate.update("boardWriterNumber", hMap);
	}

	@Override
	public int boardWriteOk(BoardDto boardDto) {
		return sqlSessionTemplate.insert("boardInsert", boardDto);
	}

	@Override
	public int getCount() {
		return sqlSessionTemplate.selectOne("boardCount");
	}

	@Override
	public List<BoardDto> boardList(int startRow, int endRow) {
		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("startRow", startRow);
		hMap.put("endRow", endRow);

		return sqlSessionTemplate.selectList("boardList", hMap);
	}

	@Override
	public BoardDto read(int boardNumber) {
		BoardDto boardDto = null;

		int check = sqlSessionTemplate.update("boardReadCount", boardNumber);
		LogAspect.logger.info(LogAspect.LogMsg + check);

		boardDto = sqlSessionTemplate.selectOne("boardRead", boardNumber);

		return boardDto;
	}

	@Override
	public int boardDeleteOk(int boardNumber, String password) {
		HashMap<String, Object> hMap = new HashMap<String, Object>();
		hMap.put("boardNumber", boardNumber);
		hMap.put("password", password);

		return sqlSessionTemplate.delete("boardDelete", hMap);
	}

	@Override
	public BoardDto boardUpdateSelect(int boardNumber) {
		return sqlSessionTemplate.selectOne("boardRead", boardNumber);
	}
	
	@Override
	public int boardUpdateOk(BoardDto boardDto) {
		return sqlSessionTemplate.update("boardUpdate",boardDto);
	}
}
