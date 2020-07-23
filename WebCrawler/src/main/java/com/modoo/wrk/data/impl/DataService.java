package com.modoo.wrk.data.impl;

import java.util.List;

import com.modoo.wrk.data.DataVO;
import com.modoo.wrk.info.InfoVO;

public interface DataService {
	public void insertData(DataVO vo);
	public List<DataVO> getData(DataVO vo);
	public List<String> getDataRand(DataVO vo);
}