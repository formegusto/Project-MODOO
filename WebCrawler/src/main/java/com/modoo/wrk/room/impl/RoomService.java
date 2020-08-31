package com.modoo.wrk.room.impl;

import java.util.List;
import java.util.Map;

import com.modoo.wrk.data.SearchVO;
import com.modoo.wrk.room.RHDVO;
import com.modoo.wrk.room.RoomVO;

public interface RoomService {
	public void insertRoom(RoomVO vo);
	public void insertRHD(RHDVO vo);
	public RoomVO getRoom(RoomVO vo);
	public List<RHDVO> getRHDList(RoomVO vo);
	public List<RoomVO> getRoomList();
	public Integer getRoomTop(RoomVO vo);
	public List<RoomVO> getRoomSearch(SearchVO vo);
	public void clearRHD(Map<String, Object> payload);
}
