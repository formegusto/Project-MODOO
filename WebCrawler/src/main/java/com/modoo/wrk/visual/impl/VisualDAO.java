package com.modoo.wrk.visual.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.modoo.wrk.data.SearchVO;
import com.modoo.wrk.visual.VHIVO;
import com.modoo.wrk.visual.VisualVO;

@Repository
public class VisualDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertVisual(VisualVO vo) {
		System.out.println("[VisualDAO] insertVisual() Call");
		mybatis.insert("VisualDAO.insertVisual", vo);
	}
	public void insertVHI(VHIVO vo) {
		System.out.println("[VisualDAO] insertVHI() Call");
		mybatis.insert("VisualDAO.insertVHI", vo);
	}
	public List<VHIVO> getVHIList(VisualVO vo){
		System.out.println("[VisualDAO] getVHIList() Call");
		return mybatis.selectList("VisualDAO.getVHIList",vo);
	}
	public VisualVO getVisual(VisualVO vo) {
		System.out.println("[VisualDAO] getVisual() Call");
		return mybatis.selectOne("VisualDAO.getVisual", vo);
	}
	public List<VisualVO> getVisualList(VisualVO vo){
		System.out.println("[VisualDAO] getVisualList() Call");
		return mybatis.selectList("VisualDAO.getVisualList",vo);
	}
	public List<VisualVO> getVisualListSearch(SearchVO search){
		System.out.println("[VisualDAO] getVisualList(search) Call");
		return mybatis.selectList("VisualDAO.getVisualListSearch",search);
	}
	public void deleteVisual(VisualVO vo) {
		System.out.println("[VisualDAO] deleteVisual() Call");
		mybatis.delete("VisualDAO.deleteVisual", vo);
	}
}
