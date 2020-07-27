package com.modoo.wrk.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modoo.wrk.data.impl.DataDAO;
import com.modoo.wrk.data.impl.DataService;

@Service("dataService")
public class DataServiceImpl implements DataService {
	@Autowired
	private DataDAO dataDAO;
	
	@Override
	public void insertData(DataVO vo) {
		dataDAO.insertData(vo);
	}

	@Override
	public List<DataVO> getData(DataVO vo) {
		return dataDAO.getData(vo);
	}
	
	@Override
	public List<DataVO> getDataSearch(SearchVO vo) {
		// TODO Auto-generated method stub
		return dataDAO.getDataSearch(vo);
	}

	@Override
	public List<String> getDataRand(DataVO vo) {
		return dataDAO.getDataRand(vo);
	}

	@Override
	public void deleteData(DataVO vo) {
		dataDAO.deleteData(vo);
	}
}
