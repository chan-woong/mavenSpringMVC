package com.java.member.service;

import org.springframework.web.servlet.ModelAndView;

public interface MemberService {
	public void memberRegisterOk(ModelAndView mav);

	public void memberLoginOk(ModelAndView mav);

	public void memberIdCheck(ModelAndView mav);

	public void memberZipcode(ModelAndView mav);

	public void memberDeleteOk(ModelAndView mav);
	
	public void memberUpdate(ModelAndView mav);
	
	public void memberUpdateOk(ModelAndView mav);
}
