package com.crawler.biz.room.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crawler.biz.room.RoomVO;

@Repository
public class RoomDAOMybatis {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertRoom(RoomVO vo) {
		System.out.println("===> Mybatis로 insertRoom() 등록 기능 처리");
		mybatis.insert("RoomDAO.insertRoom", vo);
	}
	public void deleteRoom(RoomVO vo) {
		System.out.println("===> Mybatis로 deleteRoom() 삭제 기능 처리");
		mybatis.delete("RoomDAO.deleteRoom", vo);
	}
	public RoomVO getRoom(RoomVO vo) {
		System.out.println("===> Mybatis로 getRoom() 조회 기능 처리");
		return mybatis.selectOne("RoomDAO.getRoom", vo);
	}
	public List<RoomVO> getRoomList(RoomVO vo) {
		System.out.println("===> Mybatis로 getRoomList() 조회 기능 처리");
		return mybatis.selectList("RoomDAO.getRoomList", vo);
	}
}
