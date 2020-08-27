package com.modoo.wrk.info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modoo.wrk.data.SearchVO;
import com.modoo.wrk.info.impl.InfoDAO;
import com.modoo.wrk.info.impl.InfoService;

@Service("infoService")
public class InfoServiceImpl implements InfoService {
	@Autowired
	private InfoDAO infoDAO;
	
	@Override
	public void insertInfo(InfoVO vo) {
		// TODO Auto-generated method stub
		infoDAO.insertInfo(vo);
	}

	@Override
	public List<InfoVO> getInfoList(InfoVO vo) {
		// TODO Auto-generated method stub
		return infoDAO.getInfoList(vo);
	}

	@Override
	public InfoVO getInfo(InfoVO vo) {
		// TODO Auto-generated method stub
		return infoDAO.getInfo(vo);
	}

	@Override
	public List<InfoVO> getInfoListSearch(SearchVO search) {
		// TODO Auto-generated method stub
		return infoDAO.getInfoListSearch(search);
	}

	@Override
	public List<InfoVO> getInfoTypeLink(InfoVO vo) {
		// TODO Auto-generated method stub
		return infoDAO.getInfoTypeLink(vo);
	}

	@Override
	public void deleteInfo(InfoVO vo) {
		// TODO Auto-generated method stub
		infoDAO.deleteInfo(vo);
	}
}
