package com.modoo.wrk.frame;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modoo.wrk.data.SearchVO;
import com.modoo.wrk.frame.impl.FrameDAO;
import com.modoo.wrk.frame.impl.FrameService;

@Service("frameService")
public class FrameServiceImpl implements FrameService {
	@Autowired
	private FrameDAO frameDAO;
	
	@Override
	public void insertFrame(FrameVO vo) {
		// TODO Auto-generated method stub
		frameDAO.insertFrame(vo);
	}

	@Override
	public void insertFHI(FHIVO vo) {
		// TODO Auto-generated method stub
		frameDAO.insertFHI(vo);
	}

	@Override
	public void deleteFrame(FrameVO vo) {
		// TODO Auto-generated method stub
		frameDAO.deleteFrame(vo);
	}

	@Override
	public List<FHIVO> getFHIList(FrameVO vo) {
		// TODO Auto-generated method stub
		return frameDAO.getFHIList(vo);
	}

	@Override
	public FrameVO getFrame(FrameVO vo) {
		// TODO Auto-generated method stub
		return frameDAO.getFrame(vo);
	}

	@Override
	public List<FrameVO> getFrameList(FrameVO vo) {
		// TODO Auto-generated method stub
		return frameDAO.getFrameList(vo);
	}

	@Override
	public List<FrameVO> getFrameListSearch(SearchVO search) {
		// TODO Auto-generated method stub
		return frameDAO.getFrameListSearch(search);
	}
}
