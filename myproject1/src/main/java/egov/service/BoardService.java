package egov.service;

import java.util.List;

// 명세처리 파일
public interface BoardService {

	/*
	 * 게시판 저장처리
	 */
	String insertBoard(BoardVO vo) throws Exception;
	/*
	 * 게시판 목록화면
	 */
	List<?> selectBoardList(BoardVO vo) throws Exception;
	/*
	 * 게시판 총 개수
	 */
	int selectBoardTotal(BoardVO vo) throws Exception;
	
	/*
	 * 게시판 상세보기
	 */
	BoardVO selectBoardDetail(int unq) throws Exception;

	
	/*
	 * 조회수 증가
	 */
	int updateBoardHits(int unq) throws Exception;
	
	/*
	 * 암호 확인
	 */
	int selectBoardPass(BoardVO vo) throws Exception;
	
	
	/*
	 * 게시글 삭제
	 */
	int deleteBoard(BoardVO vo) throws Exception;
	
}



