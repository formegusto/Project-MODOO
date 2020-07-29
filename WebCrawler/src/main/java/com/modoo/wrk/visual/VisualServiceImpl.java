package com.modoo.wrk.visual;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modoo.wrk.visual.impl.VisualDAO;
import com.modoo.wrk.visual.impl.VisualService;

@Service("visualService")
public class VisualServiceImpl implements VisualService {
	@Autowired
	private VisualDAO visualDAO;
	
	@Override
	public void insertVisual(VisualVO vo) {
		// TODO Auto-generated method stub
		visualDAO.insertVisual(vo);
	}

	@Override
	public void insertVHI(VHIVO vo) {
		// TODO Auto-generated method stub
		visualDAO.insertVHI(vo);
	}

	@Override
	public List<VHIVO> getVHIList(VisualVO vo) {
		// TODO Auto-generated method stub
		return visualDAO.getVHIList(vo);
	}

	@Override
	public List<VisualVO> getVisualList(VisualVO vo) {
		// TODO Auto-generated method stub
		return visualDAO.getVisualList(vo);
	}

}
