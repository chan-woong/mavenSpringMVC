package com.java.member.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.java.aop.LogAspect;
import com.java.member.dao.MemberDao;
import com.java.member.dto.MemberDto;
import com.java.member.dto.ZipcodeDto;

@Component
public class MemberServiceImp implements MemberService {
  @Autowired
  private MemberDao memberDao;

  @Override
  public void memberRegisterOk(ModelAndView mav) {
    Map<String, Object> map = mav.getModelMap();
    MemberDto memberDto = (MemberDto) map.get("memberDto");

    if (memberDto.getName().equals("관리자")) {
      memberDto.setMemberLevel("MA");
    } else {
      memberDto.setMemberLevel("BA");
    }
    LogAspect.logger.info(LogAspect.LogMsg + memberDto.toString());

    int check = memberDao.memberInsert(memberDto);
    LogAspect.logger.info(LogAspect.LogMsg + check);

    mav.addObject("check", check);
    mav.setViewName("member/registerOk.tiles");
  }

  @Override
  public void memberLoginOk(ModelAndView mav) {
    Map<String, Object> map = mav.getModelMap();
    HttpServletRequest request = (HttpServletRequest) map.get("request");

    String id = request.getParameter("id");
    String password = request.getParameter("password");
    LogAspect.logger.info(LogAspect.LogMsg + id + "," + password);

    String memberLevel = memberDao.loginCheck(id, password);
    LogAspect.logger.info(LogAspect.LogMsg + memberLevel);

    mav.addObject("memberLevel", memberLevel);
    mav.addObject("id", id);

    mav.setViewName("member/loginOk.tiles");
  }

  @Override
  public void memberIdCheck(ModelAndView mav) {
    Map<String, Object> map = mav.getModelMap();
    HttpServletRequest request = (HttpServletRequest) map.get("request");

    String id = request.getParameter("id");
    LogAspect.logger.info(LogAspect.LogMsg + id);

    // 해당 id 있으면 1,해당 id 없으면 0
    int check = memberDao.idCheck(id);
    LogAspect.logger.info(LogAspect.LogMsg + check);

    mav.addObject("id", id);
    mav.addObject("check", check);

    mav.setViewName("member/idCheck.empty");
  }

  @Override
  public void memberZipcode(ModelAndView mav) {
    Map<String, Object> map = mav.getModelMap();
    HttpServletRequest request = (HttpServletRequest) map.get("request");

    String checkDong = request.getParameter("dong");

    if (checkDong != null) {
      LogAspect.logger.info(LogAspect.LogMsg + checkDong);

      List<ZipcodeDto> zipcodeList = memberDao.zipcodeRead(checkDong);

      LogAspect.logger.info(LogAspect.LogMsg + zipcodeList.size());
      mav.addObject("zipcodeList", zipcodeList);
    }

    mav.setViewName("member/zipcode.empty");
  }

  @Override
  public void memberDeleteOk(ModelAndView mav) {
    Map<String, Object> map = mav.getModelMap();
    HttpServletRequest request = (HttpServletRequest) map.get("request");

    HttpSession session = request.getSession();
    String id = (String) session.getAttribute("id");

    String password = request.getParameter("password");
    LogAspect.logger.info(LogAspect.LogMsg + id + "," + password);

    int check = memberDao.delete(id, password);
    LogAspect.logger.info(LogAspect.LogMsg + check);

    mav.addObject("check", check);
    mav.setViewName("member/deleteOk.tiles");
  }

  @Override
  public void memberUpdate(ModelAndView mav) {
    Map<String, Object> map = mav.getModelMap();
    HttpServletRequest request = (HttpServletRequest) map.get("request");

    HttpSession session = request.getSession();
    String id = (String) session.getAttribute("id");
    LogAspect.logger.info(LogAspect.LogMsg + id);

    MemberDto memberDto = memberDao.upDateId(id);
    LogAspect.logger.info(LogAspect.LogMsg + memberDto.toString());

    mav.addObject("memberDto", memberDto);
    mav.setViewName("member/update.tiles");
  }

  @Override
  public void memberUpdateOk(ModelAndView mav) {
    Map<String, Object> map = mav.getModelMap();
    MemberDto memberDto = (MemberDto) map.get("memberDto");
    LogAspect.logger.info(LogAspect.LogMsg + memberDto.toString());

    int check = memberDao.updateId(memberDto);

    mav.addObject("check", check);
    mav.setViewName("member/updateOk.tiles");
  }
}
