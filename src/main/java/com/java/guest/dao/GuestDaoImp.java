package com.java.guest.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.java.guest.dto.GuestDto;

@Component
public class GuestDaoImp implements GuestDao {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate;

  @Override
  public int insert(GuestDto guestDto) {
    return sqlSessionTemplate.insert("guestInsert", guestDto);
  }

  @Override
  public int getCount() {
    return sqlSessionTemplate.selectOne("guestCount");
  }

  @Override
  public List<GuestDto> guestList(int startRow, int endRow) {
    HashMap<String, Integer> hMap = new HashMap<String, Integer>();
    hMap.put("startRow", startRow);
    hMap.put("endRow", endRow);

    return sqlSessionTemplate.selectList("guestList", hMap);
  }

  @Override
  public int delete(int num) {
    return sqlSessionTemplate.delete("guestDelete", num);
  }

  @Override
  public GuestDto select(int num) {
    return sqlSessionTemplate.selectOne("guestSelect", num);
  }

  @Override
  public int updateOk(GuestDto guestDto) {
    return sqlSessionTemplate.update("guestUpdate", guestDto);
  }
}


