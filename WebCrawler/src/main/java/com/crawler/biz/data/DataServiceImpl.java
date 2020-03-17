package com.crawler.biz.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crawler.biz.data.impl.DataDAOMybatis;
import com.crawler.biz.data.impl.DataService;

@Service("dataService")
public class DataServiceImpl implements DataService {
	@Autowired
	DataDAOMybatis dataDAO;
	
	@Override
	public void insertData(DataVO vo) {
		dataDAO.insertData(vo);
	}
	@Override
	public void insertData(List<DataVO> dataList) {
		dataDAO.insertData(dataList);
	}
	@Override
	public List<DataVO> getData(DataVO vo) {
		return dataDAO.getData(vo);
	}

	@Override
	public List<DataVO> getDataList(DataVO vo) {
		return dataDAO.getDataList(vo);
	}
	@Override
	public void deleteData(DataVO vo) {
		dataDAO.deleteData(vo);
	}
}
