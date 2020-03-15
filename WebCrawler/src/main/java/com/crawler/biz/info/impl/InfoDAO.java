package com.crawler.biz.info.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.crawler.biz.common.JDBCUtil;
import com.crawler.biz.info.InfoVO;

@Repository("infoDAO")
public class InfoDAO {
	//JDBC 관련 변수
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	//SQL 명령어들
	private final String INFO_INSERT = "insert into info(seq,title,link,content,field,cssquery)"
			+ "values((SELECT ifnull(max(seq), 0) + 1 FROM info a),?,?,?,?,?)";
	private final String INFO_GET = "select * from info where seq=?";
	private final String INFO_LIST = "select * from info order by seq desc";
	
	//CRUD 기능의 메소드 구현
	//크롤링 정보 등록
	public void insertInfo(InfoVO vo) {
		System.out.println("===> JDBC로 insertInfo() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(INFO_INSERT);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getLink());
			stmt.setString(3, vo.getContent());
			stmt.setString(4, vo.getField());
			stmt.setString(5, vo.getCssQuery());
			stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	// 크롤링 정보 상세 보기
	public InfoVO getInfo(InfoVO vo) {
		System.out.println("===> JDBC로 getInfo 기능 처리");
		InfoVO info = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(INFO_GET);
			stmt.setInt(1, vo.getSeq());
			rs = stmt.executeQuery();
			if(rs.next()) {
				info = new InfoVO();
				info.setSeq(rs.getInt("SEQ"));
				info.setTitle(rs.getString("TITLE"));
				info.setLink(rs.getString("LINK"));
				info.setContent(rs.getString("CONTENT"));
				info.setField(rs.getString("FIELD"));
				info.setCssQuery(rs.getString("CSSQUERY"));
				info.setRegDate(rs.getDate("REGDATE"));
			} 
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return info;
	}
	
	//크롤링 정보 리스트 보기
	public List<InfoVO> getInfoList(InfoVO vo){
		System.out.println("===> JDBC로 getInfoList 기능 처리");
		List<InfoVO> infoList = new ArrayList<InfoVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(INFO_LIST);
			rs = stmt.executeQuery();
			while(rs.next()) {
				InfoVO info = new InfoVO();
				info.setSeq(rs.getInt("SEQ"));
				info.setTitle(rs.getString("TITLE"));
				info.setLink(rs.getString("LINK"));
				info.setContent(rs.getString("CONTENT"));
				info.setField(rs.getString("FIELD"));
				info.setCssQuery(rs.getString("CSSQUERY"));
				info.setRegDate(rs.getDate("REGDATE"));
				infoList.add(info);
			} 
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return infoList;
	}
}
