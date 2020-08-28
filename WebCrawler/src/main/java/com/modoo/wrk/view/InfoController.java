package com.modoo.wrk.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.modoo.wrk.data.DataVO;
import com.modoo.wrk.data.SearchVO;
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
		List<DataVO> dataList = null;
		if(vo.getItype().equals("text")) {
			dataList = modooCrawler.getDataTypeText(vo);
		} else if(vo.getItype().equals("link")) {
			dataList = modooCrawler.getDataTypeLink(vo);
		} else if(vo.getItype().equals("linklist")) {
			dataList = modooCrawler.getDataTypeLinkList(vo);
		}
		
		vo.setId(((UsersVO) session.getAttribute("user")).getId());
		model.addAttribute("info", vo);
		model.addAttribute("dataList", dataList);
		model.addAttribute("processType", "new");
	
		return "infoConfirm.jsp";
	}
	
	@RequestMapping(value = "/insertInfo.do", method = RequestMethod.POST)
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
	
	@RequestMapping(value = "/appendInfo.do", method = RequestMethod.POST)
	public String appendInfo(@RequestParam(value="data", required=true) List<String> dataList,
			InfoVO vo, @RequestParam(defaultValue = "read") String mode) {
		DataVO dvo = new DataVO();
		for(String data : dataList) {
			dvo.setIseq(vo.getIseq());
			dvo.setData(data);
			dataService.appendData(dvo);
		}
		
		return "redirect:dataService.do?iseq=" + vo.getIseq() + "&mode=" + mode;
	}
	
	@RequestMapping(value = "/appendInfoByFrame.do", method = RequestMethod.POST)
	public String appendInfoByFrame(@RequestParam(value="data", required=true) List<String> dataList,
			InfoVO vo, @RequestParam(defaultValue = "read") String mode, int fseq) {
		DataVO dvo = new DataVO();
		for(String data : dataList) {
			String iseq = data.split(",")[0];
			String realData = data.split(",")[1];
			dvo.setIseq(Integer.parseInt(iseq));
			dvo.setData(realData);
			dataService.appendData(dvo);
		}
		
		return "redirect:dataServiceByFrame.do?fseq=" + fseq + "&mode=" + mode;
	}
	
	@RequestMapping(value = "/replaceInfo.do", method = RequestMethod.POST)
	public String replaceInfo(@RequestParam(value="data", required=true) List<String> dataList,
			InfoVO vo) {
		DataVO delDvo = new DataVO();
		delDvo.setIseq(vo.getIseq());
		dataService.deleteDataIseq(delDvo);
		
		DataVO dvo = new DataVO();
		for(String data : dataList) {
			dvo.setIseq(vo.getIseq());
			dvo.setData(data);
			dataService.appendData(dvo);
		}
		
		return "redirect:dataService.do?iseq=" + vo.getIseq() + "&mode=read";
	}
	
	@RequestMapping(value="/infoLinkList.do")
	public String infoLinkList(Model model, HttpSession session) {
		UsersVO user = (UsersVO) session.getAttribute("user");
		InfoVO vo = new InfoVO();
		vo.setId(user.getId());
		
		List<InfoVO> infoList = infoService.getInfoTypeLink(vo);
		
		DataVO dvo = new DataVO();
		for(int i=0;i<infoList.size();i++) {
			dvo.setIseq(infoList.get(i).getIseq());
			infoList.get(i).setDataList(dataService.getDataRand(dvo));
		}
		model.addAttribute("infoList", infoList);
		
		return "infoLinkList.jsp";
	}
	
	@RequestMapping(value = "/infoInsert.do", method = RequestMethod.GET)
	public String infoInsert(String type, Model model,
			HttpServletRequest req) {
		if(type.equals("linklist")) {
			String link = req.getParameter("iseq");
			model.addAttribute("link", link);
		}
		
		model.addAttribute("itype", type);
		model.addAttribute("type", type.toUpperCase());
		
		return "infoInsert.jsp";
	}
	
	@RequestMapping(value={"/infoService.do", "/infoServiceByTm.do", "/infoServiceByVisual.do", "/infoServiceByFrame.do"})
	public String infoService(HttpSession session,HttpServletRequest req,
			Model model) {
		UsersVO user = (UsersVO) session.getAttribute("user");
		String forwardPage = "";
		String URI = req.getRequestURI();
		String keyword = req.getParameter("keyword");
		
		InfoVO vo = new InfoVO();
		vo.setId(user.getId());
		
		List<InfoVO> infoList = null;
		
		if(keyword != "" && keyword != null) {
			SearchVO search = new SearchVO();
			search.setId(vo.getId());
			search.setKeyword(keyword);
			infoList = infoService.getInfoListSearch(search);
		} else {
			infoList = infoService.getInfoList(vo);
		}
		
		
		DataVO dvo = new DataVO();
		for(int i=0;i<infoList.size();i++) {
			dvo.setIseq(infoList.get(i).getIseq());
			infoList.get(i).setDataList(dataService.getDataRand(dvo));
		}
		model.addAttribute("infoList", infoList);
		
		if(URI.equals("/MODOO/infoService.do")) {
			forwardPage = "infoService.jsp";
		} else if(URI.equals("/MODOO/infoServiceByTm.do")) {
			String ttype = req.getParameter("ttype");
			model.addAttribute("ttype", ttype);
			
			forwardPage = "tmMake.jsp";
		} else if(URI.equals("/MODOO/infoServiceByVisual.do")){
			String vtype = req.getParameter("vtype");
			
			model.addAttribute("vtype", vtype);
			forwardPage = "visualMake.jsp";
		} else {
			forwardPage = "frameMake.jsp";
		}
		
		return forwardPage;
	}
	
	@RequestMapping("deleteInfo.do")
	public String deleteInfo(InfoVO vo) {
		infoService.deleteInfo(vo);
		
		return "redirect:infoService.do";
	}
	
	@RequestMapping("recrawl.do")
	public String recrawl(InfoVO vo, Model model) {
		InfoVO info = infoService.getInfo(vo);
		List<DataVO> dataList = null;
		
		if(info.getItype().equals("text")) {
			dataList = modooCrawler.getDataTypeText(info);
		} else if(info.getItype().equals("linklist")) {
			dataList = modooCrawler.getDataTypeLinkList(info);
		}
		
		model.addAttribute("info", info);
		model.addAttribute("dataList", dataList);
		model.addAttribute("processType", "re");
		
		return "infoConfirm.jsp";
	}
}
