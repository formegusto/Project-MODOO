package com.crawler.biz.room.impl;

import java.util.List;

import com.crawler.biz.room.RoomVO;

public interface RoomService {
	public void insertRoom(RoomVO vo);
	public void deleteRoom(RoomVO vo);
	public RoomVO getRoom(RoomVO vo);
	public List<RoomVO> getRoomList(RoomVO vo);
}
