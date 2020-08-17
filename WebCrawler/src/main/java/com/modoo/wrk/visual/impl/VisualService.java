package com.modoo.wrk.visual.impl;

import java.util.List;

import com.modoo.wrk.data.SearchVO;
import com.modoo.wrk.visual.VHIVO;
import com.modoo.wrk.visual.VisualVO;

public interface VisualService {
	public void insertVisual(VisualVO vo);
	public void insertVHI(VHIVO vo);
	public VisualVO getVisual(VisualVO vo);
	public List<VHIVO> getVHIList(VisualVO vo);
	public List<VisualVO> getVisualList(VisualVO vo);
	public List<VisualVO> getVisualListSearch(SearchVO search);
}
