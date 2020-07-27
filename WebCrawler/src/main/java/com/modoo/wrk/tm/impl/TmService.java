package com.modoo.wrk.tm.impl;

import java.util.List;

import com.modoo.wrk.tm.THIVO;
import com.modoo.wrk.tm.TmVO;

public interface TmService {
	public void insertTm(TmVO vo);
	public void insertTHI(THIVO vo);
	public List<TmVO> getTmList (TmVO vo);
	public int getTmTop (TmVO vo);
}