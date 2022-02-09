package com.java.member.dao;

import java.util.List;

import com.java.member.dto.MemberDto;
import com.java.member.dto.ZipcodeDto;

public interface MemberDao {
	public int memberInsert(MemberDto memberDto);

	public String loginCheck(String id, String password);

	public int idCheck(String id);

	public List<ZipcodeDto> zipcodeRead(String checkDong);

	public int delete(String id, String password);
	
	public MemberDto upDateId(String id);
	
	public int updateId(MemberDto memberDto);
}
