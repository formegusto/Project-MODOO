package com.modoo.wrk.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.modoo.wrk.mdf.MFDTransferVO;
import com.modoo.wrk.mdf.impl.MFDService;

@Controller
public class MFDController {
	@Autowired
	private MFDService mfdService;
	
	@RequestMapping("mfdRequest.do")
	public String mfdRequest(MFDTransferVO vo) {
		mfdService.reqTransfer(vo);
		
		return "redirect:boardDetailService.do?bseq=" + vo.getBseq();
	}
}
