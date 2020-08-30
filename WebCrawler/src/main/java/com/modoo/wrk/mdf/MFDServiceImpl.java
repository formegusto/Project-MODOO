package com.modoo.wrk.mdf;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modoo.wrk.mdf.impl.MFDDAO;
import com.modoo.wrk.mdf.impl.MFDService;

@Service("MFDService")
public class MFDServiceImpl implements MFDService {
	@Autowired
	private MFDDAO mfdDAO;
	
	@Override
	public Boolean isDeveloper(Map<String, String> payload) {
		// TODO Auto-generated method stub
		return mfdDAO.isDeveloper(payload);
	}

	@Override
	public void reqTransfer(MFDTransferVO vo) {
		// TODO Auto-generated method stub
		mfdDAO.reqTransfer(vo);
	}

}
