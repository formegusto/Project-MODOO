package com.modoo.wrk.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.modoo.wrk.board.BHDVO;
import com.modoo.wrk.board.BoardVO;
import com.modoo.wrk.board.impl.BoardService;
import com.modoo.wrk.comment.CommentVO;
import com.modoo.wrk.comment.impl.CommentService;
import com.modoo.wrk.data.DataVO;
import com.modoo.wrk.data.SearchVO;
import com.modoo.wrk.data.impl.DataService;
import com.modoo.wrk.frame.FHIVO;
import com.modoo.wrk.frame.FrameVO;
import com.modoo.wrk.frame.impl.FrameService;
import com.modoo.wrk.info.InfoVO;
import com.modoo.wrk.info.impl.InfoService;
import com.modoo.wrk.mdf.impl.MFDService;
import com.modoo.wrk.tm.TVIVO;
import com.modoo.wrk.tm.TmVO;
import com.modoo.wrk.tm.impl.TmService;
import com.modoo.wrk.users.UsersVO;
import com.modoo.wrk.visual.VHIVO;
import com.modoo.wrk.visual.VisualVO;
import com.modoo.wrk.visual.impl.VisualService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private InfoService infoService;
	@Autowired
	private DataService dataService;
	@Autowired
	private FrameService frameService;
	@Autowired
	private TmService tmService;
	@Autowired
	private VisualService visualService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private MFDService mfdService;
	
	@RequestMapping(value = "boardMake.do", method = RequestMethod.GET)
	public String boardMake(HttpSession session,
			Model model) {
		UsersVO user = (UsersVO) session.getAttribute("user");
		
		FrameVO fvo = new FrameVO();
		TmVO tvo = new TmVO();
		VisualVO vvo = new VisualVO();
		
		fvo.setId(user.getId());
		tvo.setId(user.getId());
		vvo.setId(user.getId());
		
		model.addAttribute("frameList", frameService.getFrameList(fvo));
		model.addAttribute("tmList", tmService.getTmList(tvo));
		model.addAttribute("visualList", visualService.getVisualList(vvo));
		
		return "boardMake.jsp";
	}
	
	@RequestMapping(value = "boardMake.do", method = RequestMethod.POST)
	public String boardMake_proc(BoardVO bvo, @RequestParam(defaultValue = "0" ) int fseq,
			@RequestParam(defaultValue = "0" ) int tseq, @RequestParam(defaultValue = "0" ) int vseq, HttpSession session) {
		UsersVO user = (UsersVO) session.getAttribute("user");
		
		bvo.setId(user.getId());
		boardService.insertBoard(bvo);
		int bseq = boardService.getBoardTop(bvo);
		
		if(fseq != 0) {
			FrameVO fvo = new FrameVO();
			fvo.setFseq(fseq);
			
			FrameVO frame = frameService.getFrame(fvo);
			BHDVO frameBHD = new BHDVO();
			
			frameBHD.setBseq(bseq);
			frameBHD.setSeq(frame.getFseq());
			frameBHD.setType("frame");
			
			boardService.insertBHD(frameBHD);
		}
		
		if(tseq != 0) {
			TmVO tvo = new TmVO();
			tvo.setTseq(tseq);
			
			TmVO tm = tmService.getTm(tvo);
			BHDVO tmBHD = new BHDVO();
			
			tmBHD.setBseq(bseq);
			tmBHD.setSeq(tm.getTseq());
			tmBHD.setType("tm");
			
			boardService.insertBHD(tmBHD);
		}
		
		if(vseq != 0) {
			VisualVO vvo = new VisualVO();
			vvo.setVseq(vseq);
			
			VisualVO visual = visualService.getVisual(vvo);
			BHDVO visualBHD = new BHDVO();
			
			visualBHD.setBseq(bseq);
			visualBHD.setSeq(visual.getVseq());
			visualBHD.setType("visual");
			
			boardService.insertBHD(visualBHD);
		}
		
		return "redirect:boardService.do";
	}
	
	@RequestMapping(value = "boardService.do")
	public String boardService(Model model, String keyword) {
		if(keyword == null) {
			model.addAttribute("boardList", boardService.getBoardList());
		} else {
			SearchVO svo = new SearchVO();
			svo.setKeyword(keyword);
			
			model.addAttribute("boardList", boardService.getBoardSearch(svo));
		}
		return "boardService.jsp";
	}
	
	@RequestMapping(value = "boardDetailService.do", method = RequestMethod.GET)
	public String boardDetailService(BoardVO vo, Model model,
			HttpSession session) {
		BoardVO board = boardService.getBoard(vo);
		List<BHDVO> bhdList = boardService.getBHDList(vo);
		
		for(BHDVO bhd : bhdList) {
			if(bhd.getType().equals("frame")) {
				FrameVO fvo = new FrameVO();
				fvo.setFseq(bhd.getSeq());
				
				FrameVO frame = frameService.getFrame(fvo);
				List<FHIVO> fhiList = frameService.getFHIList(fvo);
				
				for(int i=0;i<fhiList.size();i++) {
					FHIVO fhi = fhiList.get(i);
					
					InfoVO ivo = new InfoVO();
					ivo.setIseq(fhi.getIseq());
					
					InfoVO info = infoService.getInfo(ivo);
					
					DataVO dvo = new DataVO();
					dvo.setIseq(fhi.getIseq());
					
					List<DataVO> dataList = dataService.getData(dvo);
					
					fhi.setField(info.getField());
					fhi.setDataList(dataList);
					
					fhiList.set(i, fhi);
				}
				
				model.addAttribute("frame", frame);
				model.addAttribute("fhiList", fhiList);
			} else if(bhd.getType().equals("tm")) {
				TmVO tvo = new TmVO();
				tvo.setTseq(bhd.getSeq());
				
				TmVO tm = tmService.getTm(tvo);
				if(tm.getTtype().equals("sentiment")) {
					TVIVO tvivo = new TVIVO();
					tvivo.setTseq(tm.getTseq());
					tm.setTvi(tmService.getTVI(tvivo));
				}
				model.addAttribute("tm", tm);
			} else if(bhd.getType().equals("visual")) {
				VisualVO vvo = new VisualVO();
				vvo.setVseq(bhd.getSeq());
				
				VisualVO visual = visualService.getVisual(vvo);
				List<VHIVO> vhiList = visualService.getVHIList(visual);
				
				for(VHIVO vhi : vhiList) {
					DataVO dvo = new DataVO();
					dvo.setIseq(vhi.getIseq());
					List<String> dataList = dataService.getDataNotVO(dvo);
					if(vhi.getDtype().equals("datas")) {
						for(int j=0;j<dataList.size();j++) {
							String data = dataList.get(j);
							data = data.replaceAll("[^0-9]", "");
							
							dataList.set(j, data);
						}
						visual.setDatas(dataList);
					}
					if(vhi.getDtype().equals("labels")) {
						for(int j=0;j<dataList.size();j++) {
							String data = dataList.get(j);
							data = "\'" + data + "\'";
							
							dataList.set(j, data);
						}
						visual.setLabels(dataList);
					}
				}
				
				model.addAttribute("visual", visual);
			}
		}
		CommentVO cvo = new CommentVO();
		cvo.setBseq(vo.getBseq());
		
		// MDF Check
		UsersVO user = (UsersVO) session.getAttribute("user");
		Boolean isDevelopers = false;
		if(user != null) {
			Map<String, String> reqPayload = new HashMap<String, String>();
			Map<String, String> resPayload = new HashMap<String, String>();
			{
				reqPayload.put("modoo_id", user.getId());
			}
			{
				resPayload.put("modoo_id", board.getId());
			}
			if(mfdService.isDeveloper(reqPayload) && mfdService.isDeveloper(resPayload)) {
				model.addAttribute("requser", user.getId());
				model.addAttribute("resuser", board.getId());
				isDevelopers = true;
			}
		}
		
		model.addAttribute("isDevelopers", isDevelopers);
		model.addAttribute("commentList", commentService.getCommentList(cvo));
		model.addAttribute("board", board);

		return "boardDetailService.jsp";
	}
}
