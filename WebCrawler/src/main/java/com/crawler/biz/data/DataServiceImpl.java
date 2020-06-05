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
	public void insertDataAll(DataVO vo) {
		dataDAO.insertDataAll(vo);
	}
	@Override
	public void insertDataInum(DataVO vo) {
		dataDAO.insertDataInum(vo);
	}
	@Override
	public void insertDataInum(List<DataVO> dataList) {
		dataDAO.insertDataInum(dataList);
	}
	@Override
	public void updateData(DataVO vo) {
		dataDAO.updateData(vo);
	}
	@Override
	public DataVO getDataDseq(DataVO vo) {
		return dataDAO.getDataDseq(vo);
	}
	@Override
	public List<DataVO> getData(DataVO vo) {
		return dataDAO.getData(vo);
	}
	
	@Override
	public List<String> getDataStr(DataVO vo){
		return dataDAO.getDataStr(vo);
	}

	@Override
	public List<DataVO> getDataList(DataVO vo) {
		return dataDAO.getDataList(vo);
	}
	
	@Override
	public void deleteDataSeq(DataVO vo) {
		dataDAO.deleteDataSeq(vo);
	}
	
	@Override
	public void deleteData(DataVO vo) {
		dataDAO.deleteData(vo);
	}
	
	@Override
	public void deleteDataContain(ContainVO vo) {
		dataDAO.deleteDataContain(vo);
	}
	
	@Override
	public void deleteDataLength(LengthVO vo,String condition) {
		dataDAO.deleteDataLength(vo,condition);	
	}
}
