package com.java.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.member.dto.MemberDto;
import com.java.member.service.MemberService;

@Controller
public class MemberController {
  @Autowired
  private MemberService memberService;

  @RequestMapping(value = "/member/register.do", method = RequestMethod.GET)
  public ModelAndView memberRegister(HttpServletRequest request, HttpServletResponse response) {
    return new ModelAndView("member/register.tiles");
  }

  @RequestMapping(value = "/member/registerOk.do", method = RequestMethod.POST)
  public ModelAndView memberRegisterOk(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("memberDto", memberDto);

    memberService.memberRegisterOk(mav);
    return mav;
  }

  @RequestMapping(value = "/member/login.do", method = RequestMethod.GET)
  public ModelAndView memberLogin(HttpServletRequest request, HttpServletResponse response) {
    return new ModelAndView("member/login.tiles");
  }

  @RequestMapping(value = "/member/loginOk.do", method = RequestMethod.POST)
  public ModelAndView memberLoginOk(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("request", request);

    memberService.memberLoginOk(mav);
    return mav;
  }

  @RequestMapping(value = "/member/main.do", method = RequestMethod.GET)
  public ModelAndView memberMain(HttpServletRequest request, HttpServletResponse response) {
    return new ModelAndView("member/Main.tiles");
  }

  @RequestMapping(value = "/member/idCheck.do", method = RequestMethod.GET)
  public ModelAndView memberIdCheck(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("request", request);

    memberService.memberIdCheck(mav);
    return mav;
  }

  @RequestMapping(value = "/member/zipcode.do", method = RequestMethod.GET)
  public ModelAndView memberZipcode(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("request", request);

    memberService.memberZipcode(mav);
    return mav;
  }

  @RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
  public ModelAndView memberLogout(HttpServletRequest request, HttpServletResponse response) {
    return new ModelAndView("member/logout.tiles");
  }

  @RequestMapping(value = "/member/delete.do", method = RequestMethod.GET)
  public ModelAndView memberDelete(HttpServletRequest request, HttpServletResponse response) {
    return new ModelAndView("member/delete.tiles");
  }

  @RequestMapping(value = "/member/deleteOk.do", method = RequestMethod.POST)
  public ModelAndView memberDeleteOk(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("request", request);

    memberService.memberDeleteOk(mav);
    return mav;
  }

  @RequestMapping(value = "/member/update.do", method = RequestMethod.GET)
  public ModelAndView memberUpdate(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("request", request);

    memberService.memberUpdate(mav);
    return mav;
  }

  @RequestMapping(value = "/member/updateOk.do", method = RequestMethod.POST)
  public ModelAndView memberUpdateOk(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("memberDto", memberDto);

    memberService.memberUpdateOk(mav);
    return mav;
  }

}
