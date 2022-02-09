package com.java.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.board.dto.BoardDto;
import com.java.board.service.BoardService;

@Controller
public class BoardController {
  @Autowired
  private BoardService boardService;
  
  @RequestMapping(value = "/board/write.do",method = RequestMethod.GET)
  public ModelAndView boardWrite(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("request", request);

    boardService.boardWrite(mav);
    return mav;
  }
  
  @RequestMapping(value = "/board/writeOk.do",method = RequestMethod.POST)
  public ModelAndView boardWriteOk(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("boardDto", boardDto);

    boardService.boardWriteOk(mav);

    return mav;
  }
  
  @RequestMapping(value = "/board/list.do",method = RequestMethod.GET)
  public ModelAndView boardList(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("request", request);

    boardService.boardList(mav);

    return mav;
  }
  
  @RequestMapping(value = "/board/read.do",method = RequestMethod.GET)
  public ModelAndView boardRead(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("request", request);

    boardService.boardRead(mav);
    return mav;
  }
  
  @RequestMapping(value = "/board/delete.do",method = RequestMethod.GET)
  public ModelAndView boardDelete(HttpServletRequest request, HttpServletResponse response) {
    int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
    int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));

    ModelAndView mav = new ModelAndView();
    mav.addObject("boardNumber", boardNumber);
    mav.addObject("pageNumber", pageNumber);

    mav.setViewName("board/delete");
    return mav;
  }
  
  @RequestMapping(value = "/board/deleteOk.do",method = RequestMethod.POST)
  public ModelAndView boardDeleteOk(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("request", request);

    boardService.boardDeleteOk(mav);
    return mav;
  }
  
  @RequestMapping(value = "/board/update.do",method = RequestMethod.GET)
  public ModelAndView boardUpdate(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("request", request);

    boardService.boardUpdate(mav);
    return mav;
  }
  
  @RequestMapping(value = "/board/updateOk.do",method = RequestMethod.POST)
  public ModelAndView boardUpdateOk(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("request", request);
    mav.addObject("boardDto", boardDto);

    boardService.boardUpdateOk(mav);
    return mav;
  }

}
