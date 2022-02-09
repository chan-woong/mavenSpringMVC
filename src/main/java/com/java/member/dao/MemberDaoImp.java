package com.java.member.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.java.member.dto.MemberDto;
import com.java.member.dto.ZipcodeDto;

@Component
public class MemberDaoImp implements MemberDao {
    @Autowired
	private SqlSessionTemplate sqlSessionTemplate;


	@Override
	public int memberInsert(MemberDto memberDto) {
		return sqlSessionTemplate.insert("memberInsert", memberDto);
	}

	@Override
	public String loginCheck(String id, String password) {
		HashMap<String, Object> hMap = new HashMap<String, Object>();
		hMap.put("id", id);
		hMap.put("password", password);

		return sqlSessionTemplate.selectOne("loginCheck", hMap);
	}

	@Override
	public int idCheck(String id) {
		int check = 0;
		String checkID = sqlSessionTemplate.selectOne("memberIdCheck", id);

		if (checkID != null) {
			check = 1;
		}

		return check;
	}

	@Override
	public List<ZipcodeDto> zipcodeRead(String checkDong) {
		return sqlSessionTemplate.selectList("memberZipcode", checkDong);
	}
	
	@Override
	public int delete(String id, String password) {
		HashMap<String, Object> hMap = new HashMap<String, Object>();
		hMap.put("id", id);
		hMap.put("password", password);
		
		return sqlSessionTemplate.delete("memberDelete", hMap);
	}
	
	@Override
	public MemberDto upDateId(String id) {
		MemberDto memberDto = null;
		memberDto = sqlSessionTemplate.selectOne("memberSelect",id);
		return memberDto;
	}
	
	@Override
	public int updateId(MemberDto memberDto) {
		return sqlSessionTemplate.update("memberUpdate",memberDto);
	}
}
