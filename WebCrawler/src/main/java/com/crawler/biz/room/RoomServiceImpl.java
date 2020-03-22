package com.crawler.biz.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crawler.biz.room.impl.RoomDAOMybatis;
import com.crawler.biz.room.impl.RoomService;

@Service("roomService")
public class RoomServiceImpl implements RoomService {
	@Autowired
	private RoomDAOMybatis roomDAO;

	@Override
	public void insertRoom(RoomVO vo) {
		roomDAO.insertRoom(vo);
	}

	@Override
	public RoomVO getRoom(RoomVO vo) {
		return roomDAO.getRoom(vo);
	}

	@Override
	public List<RoomVO> getRoomList(RoomVO vo) {
		return roomDAO.getRoomList(vo);
	}

}
