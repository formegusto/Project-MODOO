package com.crawler.biz.visual.impl;

import java.util.List;

import com.crawler.biz.visual.VisualHaveInfoVO;
import com.crawler.biz.visual.VisualVO;

public interface VisualService {
	public void insertVisual(VisualVO vo);
	public void insertVHI(VisualHaveInfoVO vo);
	public List<VisualHaveInfoVO> getVHIList(VisualVO vo);
	public VisualVO getVisual(VisualVO vo);
	public List<VisualVO> getVisualList(VisualVO vo);
}
