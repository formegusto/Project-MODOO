package com.crawler.biz.visual;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crawler.biz.visual.impl.VisualDAOMybatis;
import com.crawler.biz.visual.impl.VisualService;

@Service("visualService")
public class VisualServiceImpl implements VisualService {
	@Autowired
	private VisualDAOMybatis visualDAO;
	
	@Override
	public void insertVisual(VisualVO vo) {
		visualDAO.insertVisual(vo);
	}

	@Override
	public void insertVHI(VisualHaveInfoVO vo) {
		visualDAO.insertVHI(vo);
	}

	@Override
	public List<VisualHaveInfoVO> getVHIList(VisualHaveInfoVO vo) {
		return visualDAO.getVHIList(vo);
	}

	@Override
	public VisualVO getVisual(VisualVO vo) {
		return visualDAO.getVisual(vo);
	}

	@Override
	public List<VisualVO> getVisualList(VisualVO vo) {
		return visualDAO.getVisualList(vo);
	}

}
