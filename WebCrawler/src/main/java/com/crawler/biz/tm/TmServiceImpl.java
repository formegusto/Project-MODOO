package com.crawler.biz.tm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crawler.biz.tm.impl.TmDAOMybatis;
import com.crawler.biz.tm.impl.TmService;

@Service("tmService")
public class TmServiceImpl implements TmService {
	@Autowired
	private TmDAOMybatis tmDAO;
	
	@Override
	public void insertTm(TmVO vo) {
		// TODO Auto-generated method stub
		tmDAO.insertTm(vo);
	}

	@Override
	public void insertTHI(TmHaveInfoVO vo) {
		// TODO Auto-generated method stub
		tmDAO.insertTHI(vo);
	}

	@Override
	public List<TmHaveInfoVO> getTHIList(TmVO vo) {
		// TODO Auto-generated method stub
		return tmDAO.getTHIList(vo);
	}

	@Override
	public TmVO getTm(TmVO vo) {
		// TODO Auto-generated method stub
		return tmDAO.getTm(vo);
	}

	@Override
	public List<TmVO> getTmList(TmVO vo) {
		// TODO Auto-generated method stub
		return tmDAO.getTmList(vo);
	}

	@Override
	public TmVO getTmTop(TmVO vo) {
		// TODO Auto-generated method stub
		return tmDAO.getTmTop(vo);
	}

}
