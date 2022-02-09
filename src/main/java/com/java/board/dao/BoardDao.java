package com.java.board.dao;

import java.util.HashMap;
import java.util.List;

import com.java.board.dto.BoardDto;

public interface BoardDao {

	public int boardGroupNumberMax();

	public int boardWriteNumber(HashMap<String, Integer> hMap);

	public int boardWriteOk(BoardDto boardDto);

	public int getCount();

	public List<BoardDto> boardList(int startRow, int endRow);
	
	public BoardDto read(int boardNumber);
	
	public int boardDeleteOk(int boardNumber,String password);
	
	public BoardDto boardUpdateSelect(int boardNumber);
	
	public int boardUpdateOk(BoardDto boardDto);
}
