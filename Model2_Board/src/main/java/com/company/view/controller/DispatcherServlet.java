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
		process(request, response); //process(request, response) �޼ҵ� ȣ��
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		process(request, response);
	}
	//����� ���� �޼ҵ� ���� -> [�߿�]
	@Autowired
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//1. Ŭ���̾�Ʈ�� ��û path ������ �����Ѵ�.
		String uri = request.getRequestURI();
		String filepath = uri.substring(uri.lastIndexOf("/"));
		
		
		//2. Ŭ���̾�Ʈ�� ��û filePath�� ���� ������ �б� ó���Ѵ�.
		if(filepath.equals("/login.do")) {
			System.out.println("�α��� ó����!");
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			UserDO userDO = new UserDO();
			userDO.setId(id);
			userDO.setPassword(password);
			
			UserDAO userDAO = new UserDAO();
			UserDO user = userDAO.getUser(userDO);
			
			if(user != null) {
				HttpSession session = request.getSession();
				System.out.println("�α��� ����");
				response.sendRedirect("getBoardList.do");
			}else {
				System.out.println("�α��� ����");
				response.sendRedirect("login.jsp");
			}
			
		}else if(filepath.equals("/getBoardList.do")) {
			System.out.println("��ü �Խñ� ��� ���� ó����!");
			
			String searchField = "";
			String searchText = "";
			
			if(request.getParameter("searchCondition") != "" &&
					request.getParameter("searchKeyword") != "") {
				searchField = request.getParameter("searchCondition");
				searchText = request.getParameter("searchKeyword");
			}
			BoardDAO boardDAO = new BoardDAO();
			List<BoardDO> boardList = boardDAO.getBoardList(searchField, searchText);
			
			//�˻� ����� ���ǿ� ����
			HttpSession session = request.getSession();
			session.setAttribute("boardList", boardList);
			
			//������(����)
			response.sendRedirect("getBoardList.jsp");
		}else if(filepath.equals("/getBoard.do")){
			System.out.println("�Խñ� �󼼺��� ");
			
			String seq = request.getParameter("seq");
			BoardDO boardDO = new BoardDO();
			boardDO.setSeq(Integer.parseInt(seq));
			
			BoardDAO boardDAO = new BoardDAO();
			BoardDO board = boardDAO.getBoard(boardDO);
			
			HttpSession session = request.getSession();
			session.setAttribute("board", board);

			//������
			response.sendRedirect("getBoard.jsp");
		}else if(filepath.equals("/insertBoard.do")) {
			System.out.println("�Խñ� �Է� ó����!");
			
			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
						
			BoardDO boardDO = new BoardDO();
			boardDO.setTitle(title);
			boardDO.setWriter(writer);
			boardDO.setContent(content);
			
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.insertBoard(boardDO);
			
			//������
			response.sendRedirect("getBoardList.do");
			
		}else if(filepath.equals("/updateBoard.do")) {
			System.out.println("�Խñ� ���� ó����!");
			
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
			
			//������
			response.sendRedirect("getBoardList.do");
		}else if (filepath.equals("/deleteBoard.do")) {
			System.out.println("�Խñ� ���� ó����!");
			
			String seq = request.getParameter("seq");
			
			BoardDO boardDO = new BoardDO();
			boardDO.setSeq(Integer.parseInt(seq));
			
			
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.deleteBoard(boardDO);
			
			response.sendRedirect("getBoardList.do");
		}else if(filepath.equals("/logout.do")) {
			System.out.println("�α׾ƿ� ó����");
			
			HttpSession session = request.getSession();
			session.invalidate();
			
			response.sendRedirect("login.jsp");
		}
	}
}