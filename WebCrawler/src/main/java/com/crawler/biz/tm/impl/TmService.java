package com.crawler.biz.tm.impl;

import java.util.List;

import com.crawler.biz.tm.TmHaveInfoVO;
import com.crawler.biz.tm.TmVO;

public interface TmService {
	public void insertTm(TmVO vo);
	public void insertTHI(TmHaveInfoVO vo);
	public List<TmHaveInfoVO> getTHIList(TmVO vo);
	public TmVO getTm(TmVO vo);
	public List<TmVO> getTmList(TmVO vo);
	public TmVO getTmTop(TmVO vo);
}