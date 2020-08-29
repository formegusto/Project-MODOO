package com.modoo.wrk.room.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.modoo.wrk.data.SearchVO;
import com.modoo.wrk.room.RHDVO;
import com.modoo.wrk.room.RoomVO;

@Repository
public class RoomDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertRoom(RoomVO vo) {
		mybatis.insert("RoomDAO.insertRoom", vo);
	}
	
	public void insertRHD(RHDVO vo) {
		mybatis.insert("RoomDAO.insertRHD", vo);
	}
	
	public RoomVO getRoom(RoomVO vo) {
		return mybatis.selectOne("RoomDAO.getRoom", vo);
	}
	
	public List<RHDVO> getRHDList(RoomVO vo){
		return mybatis.selectList("RoomDAO.getRHDList", vo);
	}
	
	public List<RoomVO> getRoomList(){
		return mybatis.selectList("RoomDAO.getRoomList");
	}
	
	public Integer getRoomTop(RoomVO vo) {
		return mybatis.selectOne("RoomDAO.getRoomTop", vo);
	}
	
	public List<RoomVO> getRoomSearch(SearchVO vo){
		return mybatis.selectList("RoomDAO.getRoomSearch", vo);
	}
}
