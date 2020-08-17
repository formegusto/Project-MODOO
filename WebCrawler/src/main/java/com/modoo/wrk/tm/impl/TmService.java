package com.modoo.wrk.tm.impl;

import java.util.List;

import com.modoo.wrk.data.SearchVO;
import com.modoo.wrk.tm.THIVO;
import com.modoo.wrk.tm.TmVO;

public interface TmService {
	public void insertTm(TmVO vo);
	public void insertTHI(THIVO vo);
	public TmVO getTm(TmVO vo);
	public List<TmVO> getTmList (TmVO vo);
	public int getTmTop (TmVO vo);
	public List<TmVO> getTmListSearch (SearchVO search);
}
