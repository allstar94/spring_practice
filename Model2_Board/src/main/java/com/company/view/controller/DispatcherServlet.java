package com.company.view.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.company.Model2_Board.board.BoardDAO;
import com.company.Model2_Board.board.BoardDO;
import com.company.Model2_Board.user.UserDAO;
import com.company.Model2_Board.user.UserDO;
import com.sun.xml.internal.bind.v2.runtime.Location;

import java.util.*;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     public DispatcherServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response); //process(request, response) 메소드 호출
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		process(request, response);
	}
	//사용자 정의 메소드 구현 -> [중요]
	@Autowired
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//1. 클라이언트의 요청 path 정보를 추출한다.
		String uri = request.getRequestURI();
		String filepath = uri.substring(uri.lastIndexOf("/"));
		
		
		//2. 클라이언트의 요청 filePath에 따라 적절히 분기 처리한다.
		if(filepath.equals("/login.do")) {
			System.out.println("로그인 처리됨!");
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			UserDO userDO = new UserDO();
			userDO.setId(id);
			userDO.setPassword(password);
			
			UserDAO userDAO = new UserDAO();
			UserDO user = userDAO.getUser(userDO);
			
			if(user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("IdKey", id);
				System.out.println("로그인 성공");
				response.sendRedirect("getBoardList.do");
			}else {
				System.out.println("로그인 실패");
				response.sendRedirect("login.jsp");
			}
			
		}else if(filepath.equals("/getBoardList.do")) {
			System.out.println("전체 게시글 목록 보기 처리됨!");
			
			String searchField = "";
			String searchText = "";
			
			if(request.getParameter("searchCondition") != "" &&
					request.getParameter("searchKeyword") != "") {
				searchField = request.getParameter("searchCondition");
				searchText = request.getParameter("searchKeyword");
			}
			BoardDAO boardDAO = new BoardDAO();
			List<BoardDO> boardList = boardDAO.getBoardList(searchField, searchText);
			
			//검색 결과를 세션에 저장
			HttpSession session = request.getSession();
			session.setAttribute("boardList", boardList);
			
			//포워딩(응답)
			response.sendRedirect("getBoardList.jsp");
		}else if(filepath.equals("/getBoard.do")){
			System.out.println("게시글 상세보기 ");
			
			String seq = request.getParameter("seq");
			BoardDO boardDO = new BoardDO();
			boardDO.setSeq(Integer.parseInt(seq));
			
			BoardDAO boardDAO = new BoardDAO();
			BoardDO board = boardDAO.getBoard(boardDO);
			
			HttpSession session = request.getSession();
			session.setAttribute("board", board);

			//포워딩
			response.sendRedirect("getBoard.jsp");
		}else if(filepath.equals("/insertBoard.do")) {
			System.out.println("게시글 입력 처리됨!");
			
			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
						
			BoardDO boardDO = new BoardDO();
			boardDO.setTitle(title);
			boardDO.setWriter(writer);
			boardDO.setContent(content);
			
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.insertBoard(boardDO);
			
			//포워딩
			response.sendRedirect("getBoardList.do");
			
		}else if(filepath.equals("/updateBoard.do")) {
			System.out.println("게시글 수정 처리됨!");
			
			request.setCharacterEncoding("EUC-KR");
			
			String seq = request.getParameter("seq");
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			BoardDO boardDO = new BoardDO();
			boardDO.setSeq(Integer.parseInt(seq));
			boardDO.setTitle(title);
			boardDO.setContent(content);
			
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.updateBoard(boardDO);
			
			//포워딩
			response.sendRedirect("getBoardList.do");
		}else if (filepath.equals("/deleteBoard.do")) {
			System.out.println("게시글 삭제 처리됨!");
			
			String seq = request.getParameter("seq");
			
			BoardDO boardDO = new BoardDO();
			boardDO.setSeq(Integer.parseInt(seq));
			
			
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.deleteBoard(boardDO);
			
			response.sendRedirect("getBoardList.do");
		}else if(filepath.equals("/logout.do")) {
			System.out.println("로그아웃 처리됨");
			
			HttpSession session = request.getSession();
			session.invalidate();
			
			response.sendRedirect("login.jsp");
		}
	}
}
