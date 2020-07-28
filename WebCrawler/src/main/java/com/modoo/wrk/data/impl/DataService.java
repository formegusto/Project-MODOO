package com.modoo.wrk.data.impl;

import java.util.List;

import com.modoo.wrk.data.DataVO;
import com.modoo.wrk.data.SearchVO;

public interface DataService {
	public void insertData(DataVO vo);
	public List<DataVO> getData(DataVO vo);
	public List<DataVO> getDataSearch(SearchVO vo);
	public List<String> getDataRand(DataVO vo);
	public List<String> getDataNotVO(DataVO vo);
	public void deleteData(DataVO vo);
	public void updateData(DataVO vo);
}