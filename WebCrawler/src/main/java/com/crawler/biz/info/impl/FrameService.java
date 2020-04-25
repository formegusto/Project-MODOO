package com.crawler.biz.info.impl;

import java.util.List;

import com.crawler.biz.info.FrameHaveInfoVO;
import com.crawler.biz.info.FrameVO;

public interface FrameService {
	public void insertFrame(FrameVO vo);
	public void insertFHI(FrameHaveInfoVO vo);
	public void deleteFrame(FrameVO vo);
	public List<FrameHaveInfoVO> getFHIList(FrameVO vo);
	public FrameVO getFrame(FrameVO vo);
	public List<FrameVO> getFrameList(FrameVO vo);
}
