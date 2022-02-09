package com.java.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.java.aop.LogAspect;
import com.java.board.dao.BoardDao;
import com.java.board.dto.BoardDto;

@Component
public class BoardServiceImp implements BoardService {
  @Autowired
  private BoardDao boardDao;

	@Override
	public void boardWrite(ModelAndView mav) {
		// 부모글 -ROOT
		int boardNumber = 0; // 글번호 : ROOT 항상 boardNumber 0, 답글인 경우 부모의 boardNumber
		int groupNumber = 1; // 그룹번호
		int sequenceNumber = 0; // 글순서
		int sequenceLevel = 0; // 글레벨

		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		if (request.getParameter("boardNumber") != null) { // 답글인 경우
			boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
			groupNumber = Integer.parseInt(request.getParameter("groupNumber"));
			sequenceNumber = Integer.parseInt(request.getParameter("sequenceNumber"));
			sequenceLevel = Integer.parseInt(request.getParameter("sequenceLevel"));
		}

		mav.addObject("boardNumber", boardNumber);
		mav.addObject("groupNumber", groupNumber);
		mav.addObject("sequenceNumber", sequenceNumber);
		mav.addObject("sequenceLevel", sequenceLevel);

		mav.setViewName("board/write.tiles");
	}

	@Override
	public void boardWriteOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		BoardDto boardDto = (BoardDto) map.get("boardDto");

		writeNumber(boardDto);
		LogAspect.logger.info(LogAspect.LogMsg + boardDto.toString());

		// DB
		int check = boardDao.boardWriteOk(boardDto);
		LogAspect.logger.info(LogAspect.LogMsg + check);

		mav.addObject("check", check);
		mav.setViewName("board/writeOk.tiles");
	}

	public void writeNumber(BoardDto boardDto) {

		// 부모글 : 그룹번호 작업
		// 자식글 : 글순서, 글레벨 작업

		int boardNumber = boardDto.getBoardNumber();
		int groupNumber = boardDto.getGroupNumber();
		int sequenceNumber = boardDto.getSequenceNumber();
		int sequenceLevel = boardDto.getSequenceLevel();

		if (boardNumber == 0) { // ROOT(부모글) : 그룹번호 작업
			int max = boardDao.boardGroupNumberMax();

			if (max != 0) {
				max = max + 1;
				boardDto.setGroupNumber(max);
			}
		} else { // 자식글 : 글순서, 글레벨 작업
			HashMap<String, Integer> hMap = new HashMap<String, Integer>();
			hMap.put("groupNumber", groupNumber);
			hMap.put("sequenceNumber", sequenceNumber);

			int check = boardDao.boardWriteNumber(hMap);
			LogAspect.logger.info(LogAspect.LogMsg + check);

			sequenceNumber = sequenceNumber + 1;
			sequenceLevel = sequenceLevel + 1;

			boardDto.setSequenceNumber(sequenceNumber);
			boardDto.setSequenceLevel(sequenceLevel);
		}

	}

	@Override
	public void boardList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String pageNumber = request.getParameter("pageNumber");
		if (pageNumber == null)
			pageNumber = "1";

		int currentPage = Integer.parseInt(pageNumber);
		LogAspect.logger.info(LogAspect.LogMsg + currentPage);

		// 한 페이지 당 게시물 1page 10개 / start 1, end 10
		int boardSize = 10;
		int startRow = (currentPage - 1) * boardSize + 1;
		int endRow = currentPage * boardSize;

		int count = boardDao.getCount();
		LogAspect.logger.info(LogAspect.LogMsg + count);

		List<BoardDto> boardList = null;
		if (count > 0) {
			boardList = boardDao.boardList(startRow, endRow);
			LogAspect.logger.info(LogAspect.LogMsg + boardList.size());
		}

		mav.addObject("boardSize", boardSize); // 한페이지당 게시물 수
		mav.addObject("currentPage", currentPage); // 요청페이지
		mav.addObject("boardList", boardList); // 게시물 리스트
		mav.addObject("count", count); // 전체 게시물 수

		mav.setViewName("board/list.tiles");
	}

	@Override
	public void boardRead(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		LogAspect.logger.info(LogAspect.LogMsg + boardNumber + "," + pageNumber);

		BoardDto boardDto = boardDao.read(boardNumber);
		LogAspect.logger.info(LogAspect.LogMsg + boardDto.toString());

		request.setAttribute("boardDto", boardDto);
		request.setAttribute("pageNumber", pageNumber);

		mav.setViewName("board/read.tiles");
	}

	@Override
	public void boardDeleteOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		String password = request.getParameter("password");
		LogAspect.logger.info(LogAspect.LogMsg + boardNumber + "," + pageNumber);

		int check = boardDao.boardDeleteOk(boardNumber, password);
		LogAspect.logger.info(LogAspect.LogMsg + check);

		mav.addObject("check", check);
		mav.addObject("pageNumber", pageNumber);
		mav.setViewName("board/deleteOk.tiles");
	}

	@Override
	public void boardUpdate(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		LogAspect.logger.info(LogAspect.LogMsg + boardNumber + "," + pageNumber);

		BoardDto boardDto = boardDao.boardUpdateSelect(boardNumber);
		LogAspect.logger.info(LogAspect.LogMsg + boardDto.toString());

		mav.addObject("boardDto", boardDto);
		mav.addObject("pageNumber", pageNumber);
		mav.setViewName("board/update.tiles");
	}

	@Override
	public void boardUpdateOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		BoardDto boardDto = (BoardDto) map.get("boardDto");
		LogAspect.logger.info(LogAspect.LogMsg + boardDto.toString());

		int check = boardDao.boardUpdateOk(boardDto);
		LogAspect.logger.info(LogAspect.LogMsg + check);

		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));

		mav.addObject("check", check);
		mav.addObject("pageNumber", pageNumber);
		mav.setViewName("board/updateOk.tiles");
	}
}
