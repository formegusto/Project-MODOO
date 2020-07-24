package com.modoo.wrk.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.modoo.wrk.data.DataVO;
import com.modoo.wrk.data.impl.DataService;
import com.modoo.wrk.info.InfoVO;
import com.modoo.wrk.info.ModooCrawler;
import com.modoo.wrk.info.impl.InfoService;
import com.modoo.wrk.users.UsersVO;

@Controller
public class InfoController {
	@Autowired
	private InfoService infoService;
	@Autowired
	private DataService dataService;
	@Autowired
	private ModooCrawler modooCrawler;
	
	@RequestMapping("/confirmData.do")
	public String confirmInfo(InfoVO vo, Model model,
			HttpSession session) {
		List<DataVO> dataList = modooCrawler.getDataTypeText(vo);
		
		vo.setId(((UsersVO) session.getAttribute("user")).getId());
		model.addAttribute("info", vo);
		model.addAttribute("dataList", dataList);
	
		return "infoConfirm.jsp";
	}
	
	@RequestMapping("/insertInfo.do")
	public String insertInfo(@RequestParam(value="data", required=true) List<String> dataList,
			InfoVO vo) {
		infoService.insertInfo(vo);
		
		DataVO dvo = new DataVO();
		for(String data : dataList) {
			dvo.setData(data);
			dataService.insertData(dvo);
		}
		
		return "redirect:infoService.do";
	}
	
	@RequestMapping(value={"/infoService.do", "/infoServiceByTm.do"})
	public String infoService(HttpSession session,HttpServletRequest req,
			Model model) {
		UsersVO user = (UsersVO) session.getAttribute("user");
		String forwardPage = "";
		String URI = req.getRequestURI();
		
		InfoVO vo = new InfoVO();
		vo.setId(user.getId());
		List<InfoVO> infoList = infoService.getInfoList(vo);
		
		DataVO dvo = new DataVO();
		for(int i=0;i<infoList.size();i++) {
			dvo.setIseq(infoList.get(i).getIseq());
			infoList.get(i).setDataList(dataService.getDataRand(dvo));
		}
		model.addAttribute("infoList", infoList);
		
		if(URI.equals("/MODOO/infoService.do")) {
			forwardPage = "infoService.jsp";
		} else {
			forwardPage = "tmMake.jsp";
		}
		
		return forwardPage;
	}
}
