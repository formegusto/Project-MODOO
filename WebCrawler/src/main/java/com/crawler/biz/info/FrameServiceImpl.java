package com.crawler.biz.info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crawler.biz.info.impl.FrameDAOMybatis;
import com.crawler.biz.info.impl.FrameService;

@Service("frameService")
public class FrameServiceImpl implements FrameService {
	@Autowired
	private FrameDAOMybatis frameDAO;
	
	@Override
	public void insertFrame(FrameVO vo) {
		frameDAO.insertFrame(vo);
	}

	@Override
	public void insertFHI(FrameHaveInfoVO vo) {
		frameDAO.insertFHI(vo);
	}

	@Override
	public void deleteFrame(FrameVO vo) {
		frameDAO.deleteFrame(vo);
	}

	@Override
	public List<FrameHaveInfoVO> getFHIList(FrameVO vo){
		return frameDAO.getFHIList(vo);
	}

	@Override
	public FrameVO getFrame(FrameVO vo) {
		return frameDAO.getFrame(vo);
	}

	@Override
	public List<FrameVO> getFrameList(FrameVO vo) {
		return frameDAO.getFrameList(vo);
	}

}
