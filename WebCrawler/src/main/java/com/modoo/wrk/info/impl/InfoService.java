package com.modoo.wrk.info.impl;

import java.util.List;

import com.modoo.wrk.info.InfoVO;

public interface InfoService {
	public void insertInfo(InfoVO vo);
	public List<InfoVO> getInfoList(InfoVO vo);
	public InfoVO getInfo(InfoVO vo);
}
