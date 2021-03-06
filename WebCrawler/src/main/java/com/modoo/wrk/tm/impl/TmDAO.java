package com.modoo.wrk.tm.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.modoo.wrk.data.SearchVO;
import com.modoo.wrk.tm.TCVO;
import com.modoo.wrk.tm.THIVO;
import com.modoo.wrk.tm.TVIVO;
import com.modoo.wrk.tm.TmVO;

@Repository
public class TmDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertTm(TmVO vo) {
		System.out.println("[TmDAO Log] insertTM() Call ");
		mybatis.insert("TmDAO.insertTm", vo);
	}
	
	public void insertTHI(THIVO vo) {
		System.out.println("[TmDAO Log] insertTHI() Call ");
		mybatis.insert("TmDAO.insertTHI", vo);
	}
	
	public TmVO getTm(TmVO vo) {
		System.out.println("[TmDAO Log] getTm() Call ");
		return mybatis.selectOne("TmDAO.getTm", vo);
	}
	
	public List<TmVO> getTmList (TmVO vo) {
		System.out.println("[TmDAO Log] getTmList() Call ");
		return mybatis.selectList("TmDAO.getTmList", vo);
	}
	
	public int getTmTop (TmVO vo) {
		System.out.println("[TmDAO Log] getTmTop() Call ");
		return mybatis.selectOne("TmDAO.getTmTop", vo);
	}
	
	public TCVO getComment (TmVO vo) {
		System.out.println("[TmDAO Log] getComment() Call ");
		return mybatis.selectOne("TmDAO.getComment", vo);
	}
	
	public List<TmVO> getTmListSearch (SearchVO search) {
		System.out.println("[TmDAO Log] getTmList(Search) Call ");
		return mybatis.selectList("TmDAO.getTmListSearch", search);
	}
	
	public void insertTVI(TVIVO vo) {
		System.out.println("[TmDAO Log] insertTVI(Search) Call ");
		mybatis.insert("TmDAO.insertTVI", vo);
	}
	
	public TVIVO getTVI(TVIVO vo) {
		System.out.println("[TmDAO Log] getTVI() Call ");
		return mybatis.selectOne("TmDAO.getTVI", vo);
	}
	
	public void deleteTm(TmVO vo) {
		System.out.println("[TmDAO Log] deleteTm() Call ");
		mybatis.delete("TmDAO.deleteTm", vo);
	}
}
