package com.crawler.biz.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.crawler.biz.common.JDBCUtil;
import com.crawler.biz.data.DataVO;

@Repository("dataDAO")
public class DataDAO {
	//JDBC 관련 변수
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	//SQL 명령어들
	private final String INSERT_DATA = "insert into data(inum,data) "
			+ "values((SELECT ifnull(max(seq), 0) + 0 FROM info a),?)";
	private final String DATA_GET = "SELECT * FROM data WHERE inum=?";
	private final String DATA_LIST = "SELECT * FROM data";
	
	//CRUD 기능의 메소드 구현
	//DATA 등록
	public void insertData(DataVO vo) {
		System.out.println("===> JDBC 로 insertData() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(INSERT_DATA);
			stmt.setString(1, vo.getData());
			stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	//DATA(List) 등록
	public void insertData(List<DataVO> dataList) {
		System.out.println("===> JDBC 로 insertData(List) 기능 처리");
		try {
			for(DataVO vo : dataList)
				insertData(vo);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	//특정 크롤러의 DATA 조회
	public List<DataVO> getData(DataVO vo){
		System.out.println("===> JDBC 로 getData() 기능 처리");
		List<DataVO> dataList = new ArrayList<DataVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(DATA_GET);
			stmt.setInt(1, vo.getInum());
			rs = stmt.executeQuery();
			while(rs.next()) {
				DataVO data = new DataVO();
				data.setInum(rs.getInt("INUM"));
				data.setData(rs.getString("DATA"));
				dataList.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return dataList;
	}
	
	//전체 DATA 조회
	public List<DataVO> getDataList(DataVO vo){
		System.out.println("===> JDBC 로 getData() 기능 처리");
		List<DataVO> dataList = new ArrayList<DataVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(DATA_LIST);
			rs = stmt.executeQuery();
			while(rs.next()) {
				DataVO data = new DataVO();
				data.setInum(rs.getInt("INUM"));
				data.setData(rs.getString("DATA"));
				dataList.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return dataList;
	}
}
