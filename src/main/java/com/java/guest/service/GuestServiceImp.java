package com.java.guest.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.java.aop.LogAspect;
import com.java.guest.dao.GuestDao;
import com.java.guest.dto.GuestDto;

@Component
public class GuestServiceImp implements GuestService {
  @Autowired
  private GuestDao guestDao;

  @Override
  public void guestWrite(ModelAndView mav) {
    Map<String, Object> map = mav.getModel();
    HttpServletRequest request = (HttpServletRequest) map.get("request");

    String pageNumber = request.getParameter("pageNumber");
    if (pageNumber == null)
      pageNumber = "1";

    int currentPage = Integer.parseInt(pageNumber);
    LogAspect.logger.info(LogAspect.LogMsg + currentPage);

    int boardSize = 3;
    int startRow = (currentPage - 1) * boardSize + 1;
    int endRow = currentPage * boardSize;

    int count = guestDao.getCount();
    LogAspect.logger.info(LogAspect.LogMsg + count);

    List<GuestDto> guestList = null;
    if (count > 0) {
      guestList = guestDao.guestList(startRow, endRow);
      LogAspect.logger.info(LogAspect.LogMsg + guestList.size());
    }

    mav.addObject("guestList", guestList);
    mav.addObject("count", count);
    mav.addObject("boardSize", boardSize);
    mav.addObject("currentPage", currentPage);

    mav.setViewName("guest/write.tiles");
  }

  @Override
  public void guestWriteOk(ModelAndView mav) {
    Map<String, Object> map = mav.getModelMap();
    GuestDto guestDto = (GuestDto) map.get("guestDto");
    guestDto.setMessage(guestDto.getMessage().replace("\r\n", "<br/>"));

    int check = guestDao.insert(guestDto);
    LogAspect.logger.info(LogAspect.LogMsg + check);

    mav.addObject("check", check);
    mav.setViewName("guest/writeOk.tiles");
  }

  @Override
  public void guestDelete(ModelAndView mav) {
    Map<String, Object> map = mav.getModelMap();
    HttpServletRequest request = (HttpServletRequest) map.get("request");

    int num = Integer.parseInt(request.getParameter("num"));
    int check = guestDao.delete(num);
    LogAspect.logger.info(LogAspect.LogMsg + check);

    mav.addObject("check", check);
    mav.setViewName("guest/delete.tiles");
  }

  @Override
  public void guestUpdate(ModelAndView mav) {
    Map<String, Object> map = mav.getModelMap();
    HttpServletRequest request = (HttpServletRequest) map.get("request");

    int num = Integer.parseInt(request.getParameter("num"));
    GuestDto guestDto = guestDao.select(num);
    guestDto.setMessage(guestDto.getMessage().replace("<br/>", "\r\n"));
    LogAspect.logger.info(LogAspect.LogMsg + guestDto.toString());

    mav.addObject("guestDto", guestDto);
    mav.setViewName("guest/update.tiles");
  }

  @Override
  public void guestUpdateOk(ModelAndView mav) {
    Map<String, Object> map = mav.getModelMap();
    GuestDto guestDto = (GuestDto) map.get("guestDto");
    LogAspect.logger.info(LogAspect.LogMsg + guestDto.toString());

    guestDto.setMessage(guestDto.getMessage().replace("\r\n", "<br/>"));

    int check = guestDao.updateOk(guestDto);
    LogAspect.logger.info(LogAspect.LogMsg + check);

    mav.addObject("check", check);
    mav.setViewName("guest/updateOk.tiles");
  }

}
