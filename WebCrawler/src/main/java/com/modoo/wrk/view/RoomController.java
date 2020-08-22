package com.modoo.wrk.view;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.modoo.wrk.comment.CommentVO;
import com.modoo.wrk.data.DataVO;
import com.modoo.wrk.data.impl.DataService;
import com.modoo.wrk.frame.FHIVO;
import com.modoo.wrk.frame.FrameVO;
import com.modoo.wrk.frame.impl.FrameService;
import com.modoo.wrk.info.InfoVO;
import com.modoo.wrk.info.impl.InfoService;
import com.modoo.wrk.room.RHDVO;
import com.modoo.wrk.room.RoomVO;
import com.modoo.wrk.room.impl.RoomService;
import com.modoo.wrk.tm.TmVO;
import com.modoo.wrk.tm.impl.TmService;
import com.modoo.wrk.users.UsersVO;
import com.modoo.wrk.visual.VHIVO;
import com.modoo.wrk.visual.VisualVO;
import com.modoo.wrk.visual.impl.VisualService;

@Controller
public class RoomController {
	@Autowired
	private RoomService roomService;
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
	
	@RequestMapping(value = "roomMake.do", method = RequestMethod.POST)
	public String roomMake_proc(RoomVO rvo, FrameVO fvo,
			TmVO tvo, VisualVO vvo, HttpSession session) {
		UsersVO user = (UsersVO) session.getAttribute("user");
		
		FrameVO frame = frameService.getFrame(fvo);
		TmVO tm = tmService.getTm(tvo);
		VisualVO visual = visualService.getVisual(vvo);
		rvo.setId(user.getId());
		roomService.insertRoom(rvo);
		
		RHDVO frameRHD = new RHDVO();
		RHDVO tmRHD = new RHDVO();
		RHDVO visualRHD = new RHDVO();
		
		int rseq = roomService.getRoomTop(rvo);
		
		frameRHD.setRseq(rseq);
		frameRHD.setSeq(frame.getFseq());
		frameRHD.setType("frame");
		tmRHD.setRseq(rseq);
		tmRHD.setSeq(tm.getTseq());
		tmRHD.setType("tm");
		visualRHD.setRseq(rseq);
		visualRHD.setSeq(visual.getVseq());
		visualRHD.setType("visual");
		
		roomService.insertRHD(frameRHD);
		roomService.insertRHD(tmRHD);
		roomService.insertRHD(visualRHD);
		
		return "redirect:roomService.do";
	}
	
	@RequestMapping(value = "roomService.do")
	public String roomService(Model model) {
		model.addAttribute("roomList", roomService.getRoomList());
		
		return "roomService.jsp";
	}
	
	@RequestMapping(value = "chatService.do", method = RequestMethod.GET)
	public String boardDetailService(RoomVO vo, Model model) {
		RoomVO room = roomService.getRoom(vo);
		List<RHDVO> rhdList = roomService.getRHDList(vo);
		
		for(RHDVO rhd : rhdList) {
			if(rhd.getType().equals("frame")) {
				FrameVO fvo = new FrameVO();
				fvo.setFseq(rhd.getSeq());
				
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
			} else if(rhd.getType().equals("tm")) {
				TmVO tvo = new TmVO();
				tvo.setTseq(rhd.getSeq());
				
				model.addAttribute("tm", tmService.getTm(tvo));
			} else if(rhd.getType().equals("visual")) {
				VisualVO vvo = new VisualVO();
				vvo.setVseq(rhd.getSeq());
				
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
		cvo.setBseq(vo.getRseq());
		
		model.addAttribute("room", room);

		return "chatService.jsp";
	}
}
