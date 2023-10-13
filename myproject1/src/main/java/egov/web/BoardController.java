package egov.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egov.service.BoardService;
import egov.service.BoardVO;

// 어노테이션(주석)
@Controller
public class BoardController {

	@Resource(name = "boardService")
	public BoardService boardService;
	
	@RequestMapping(value="/boardWrite.do")
	public String boardWrite() {
		
		return "board/boardWrite";
	}
	
	@RequestMapping(value="/boardWriteSave.do")
	@ResponseBody
	public String insertBoard(BoardVO vo) throws Exception {

		String msg = "";
		String result = boardService.insertBoard(vo);
		if(result == null) {
			System.out.println("============ 저장완료 ===========");
			msg = "ok";
		} else {
			System.out.println("============ 저장실패 ===========");
			msg = "fail";
		}

		return msg;
	}
	
	@RequestMapping(value="/boardList.do")
	public String selectBoardList(BoardVO vo, ModelMap model) throws Exception {
		
		int total = boardService.selectBoardTotal(vo);
		List<?> list = boardService.selectBoardList(vo);
		model.addAttribute("resultList", list);
		model.addAttribute("total",total);
		
		return "board/boardList";
	}
	
	@RequestMapping(value="boardDetail.do")
	public String selectBoardDetail(int unq, ModelMap model) throws Exception {
	
		BoardVO vo = boardService.selectBoardDetail(unq); 
	
		String content = vo.getContent();
		content = content.replace("\n","<br>");
		content = content.replace(" ","&nbsp;");
		
		vo.setContent(content);
		
		model.addAttribute("vo", vo);
		
		// 조회수 증가
		int cnt = boardService.updateBoardHits(unq);
		
		return "board/boardDetail";
	}
	
	@RequestMapping(value="/boardPassWrite.do")
	public String boardPassWrite(int unq, ModelMap model) {
		
		model.addAttribute("unq",unq);
		
		return "board/boardPassWrite";
	}
	
	@RequestMapping(value="/boardDelete.do")
	public String deleteBoard(BoardVO vo) throws Exception {
		
		
		
		// 암호 확인 서비스
		
		int cnt = boardService.selectBoardPass(vo);
		
		if(cnt == 1) {
		// 삭제 서비스
			System.out.println("===== 암호 일치 =====");
			int result = boardService.deleteBoard(vo);
			if( result == 1 ) {
				System.out.println("===== 삭제 성공 =====");
			} else {
				System.out.println("===== 삭제 실패 =====");
			}
		} else {
			System.out.println("===== 암호 불일치 =====");
		}
		
		return "redirect:/boardList.do";
		
		
	}
}





