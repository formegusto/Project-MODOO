package com.modoo.wrk.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.modoo.wrk.chat.ChatVO;
import com.modoo.wrk.chat.impl.ChatService;
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
import com.modoo.wrk.tm.TVIVO;
import com.modoo.wrk.tm.TmVO;
import com.modoo.wrk.tm.impl.TmService;
import com.modoo.wrk.visual.VHIVO;
import com.modoo.wrk.visual.VisualVO;
import com.modoo.wrk.visual.impl.VisualService;

@Controller
public class ChatController {
	@Autowired
	private InfoService infoService;
	@Autowired
	private DataService dataService;
	@Autowired
	private RoomService roomService;
	@Autowired
	private ChatService chatService;
	@Autowired
	private FrameService frameService;
	@Autowired
	private TmService tmService;
	@Autowired
	private VisualService visualService;
	
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
				
				TmVO tm = tmService.getTm(tvo);
				if(tm.getTtype().equals("sentiment")) {
					TVIVO tvivo = new TVIVO();
					tvivo.setTseq(tm.getTseq());
					tm.setTvi(tmService.getTVI(tvivo));
				}
				model.addAttribute("tm", tm);
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
		ChatVO cvo = new ChatVO();
		cvo.setRseq(vo.getRseq());
		
		model.addAttribute("chatList", chatService.getChatList(cvo));
		model.addAttribute("room", room);

		return "chatService.jsp";
	}
}
