package com.modoo.wrk.info.impl;

import java.util.List;

import com.modoo.wrk.data.SearchVO;
import com.modoo.wrk.info.InfoVO;

public interface InfoService {
	public void insertInfo(InfoVO vo);
	public List<InfoVO> getInfoList(InfoVO vo);
	public InfoVO getInfo(InfoVO vo);
	public List<InfoVO> getInfoListSearch(SearchVO search);
	public List<InfoVO> getInfoTypeLink(InfoVO vo);
	public void deleteInfo(InfoVO vo);
}
