package com.modoo.wrk.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modoo.wrk.data.SearchVO;
import com.modoo.wrk.room.impl.RoomDAO;
import com.modoo.wrk.room.impl.RoomService;

@Service("roomService")
public class RoomServiceImpl implements RoomService {
	@Autowired
	private RoomDAO roomDAO;
	
	@Override
	public void insertRoom(RoomVO vo) {
		// TODO Auto-generated method stub
		roomDAO.insertRoom(vo);
	}

	@Override
	public void insertRHD(RHDVO vo) {
		// TODO Auto-generated method stub
		roomDAO.insertRHD(vo);
	}

	@Override
	public RoomVO getRoom(RoomVO vo) {
		// TODO Auto-generated method stub
		return roomDAO.getRoom(vo);
	}

	@Override
	public List<RHDVO> getRHDList(RoomVO vo) {
		// TODO Auto-generated method stub
		return roomDAO.getRHDList(vo);
	}

	@Override
	public List<RoomVO> getRoomList() {
		// TODO Auto-generated method stub
		return roomDAO.getRoomList();
	}

	@Override
	public Integer getRoomTop(RoomVO vo) {
		// TODO Auto-generated method stub
		return roomDAO.getRoomTop(vo);
	}

	@Override
	public List<RoomVO> getRoomSearch(SearchVO vo) {
		// TODO Auto-generated method stub
		return roomDAO.getRoomSearch(vo);
	}
	
}
