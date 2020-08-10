package com.modoo.wrk.frame.impl;

import java.util.List;

import com.modoo.wrk.data.SearchVO;
import com.modoo.wrk.frame.FHIVO;
import com.modoo.wrk.frame.FrameVO;

public interface FrameService {
	public void insertFrame(FrameVO vo);
	public void insertFHI(FHIVO vo);
	public void deleteFrame(FrameVO vo);
	public List<FHIVO> getFHIList(FrameVO vo);
	public FrameVO getFrame(FrameVO vo);
	public List<FrameVO> getFrameList(FrameVO vo);
	public List<FrameVO> getFrameListSearch(SearchVO search);
}
