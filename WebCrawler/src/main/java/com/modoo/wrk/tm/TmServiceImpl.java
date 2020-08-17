package com.modoo.wrk.tm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modoo.wrk.data.SearchVO;
import com.modoo.wrk.tm.impl.TmDAO;
import com.modoo.wrk.tm.impl.TmService;

@Service("tmService")
public class TmServiceImpl implements TmService {
	@Autowired
	private TmDAO tmDAO;
	
	@Override
	public void insertTm(TmVO vo) {
		// TODO Auto-generated method stub
		tmDAO.insertTm(vo);
	}

	@Override
	public void insertTHI(THIVO vo) {
		// TODO Auto-generated method stub
		tmDAO.insertTHI(vo);
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
	public int getTmTop(TmVO vo) {
		// TODO Auto-generated method stub
		return tmDAO.getTmTop(vo);
	}

	@Override
	public List<TmVO> getTmListSearch(SearchVO search) {
		// TODO Auto-generated method stub
		return tmDAO.getTmListSearch(search);
	}
}
