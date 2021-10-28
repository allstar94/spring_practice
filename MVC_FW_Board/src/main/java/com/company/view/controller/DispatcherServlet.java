package com.company.view.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//��ü �������� ����
	private HandlerMapping handlerMapping;
	private ViewResolver viewResolver;
	
	@Override
	public void init() throws ServletException {
		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();
		viewResolver.setPrefix("./");
		viewResolver.setSuffix(".jsp");
	}
	
    public DispatcherServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		//1. Ŭ���̾�Ʈ�� ��û path ���� ����
		//(��) "http://localhost:8080/MVC_FW_Board/login.do" ��û�� �����Ͽ�
		String uri = request.getRequestURI();	//	"/MVC_FW_Board/login.do"�� ����!!
		String filePath = uri.substring(uri.lastIndexOf("/"));	//	"/login.do" ����!
		
		//2. HandlerMapping�� ���ؼ� filePath�� �ش��ϴ� Controller�� �˻��Ѵ�.
		Controller ctrl = handlerMapping.getController(filePath);
		
		//3. �˻��� Controller�� �����Ѵ�.
		String viewName = ctrl.handleRequest(request, response);
		
		//4. ViewResolver�� ���ؼ� viewName�� �ش��ϴ� ������(������)�� �˻��Ѵ�.
		String view = null;
		
		//�α��� ���� �ÿ��� "getBoardList.do" ���ڿ��� �Ѿ����,
		//���� �ÿ��� "login" ���ڿ��� �Ѿ�´�.
		if(viewName.contains(".do")) {
			view = viewName;
		}
		else {
			view = viewResolver.getView(viewName);
		}
		System.out.println("view = " + view);
		//5. ������ ������
		response.sendRedirect(view);
		
	}

}