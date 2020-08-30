package com.modoo.wrk.mdf.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.modoo.wrk.mdf.MFDTransferVO;

@Repository
public class MFDDAO {
	@Autowired
	private SqlSessionTemplate mybatis;

	public Boolean isDeveloper(Map<String,String> payload) {
		return mybatis.selectOne("MFDDAO.isDeveloper", payload);
	}
	public void reqTransfer(MFDTransferVO vo) {
		mybatis.insert("MFDDAO.reqTransfer", vo);
	}
}
