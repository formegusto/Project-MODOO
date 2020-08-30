package com.modoo.wrk.mdf.impl;

import java.util.Map;

import com.modoo.wrk.mdf.MFDTransferVO;

public interface MFDService {
	public Boolean isDeveloper(Map<String,String> payload);
	public void reqTransfer(MFDTransferVO vo);
}
