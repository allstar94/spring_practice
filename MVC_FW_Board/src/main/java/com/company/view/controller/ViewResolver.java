package com.company.view.controller;

public class ViewResolver {
	//�ʵ� ����
	public String prefix;		//���λ�
	public String suffix;		//���̻�
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	/*
	 * (��) �������� �� => ./getBoardList.jsp
	 * 				./ => ���λ� setPrefix("./");
	 * 				.jsp => ���̻� setSuffix(".jsp");
	 */
	
	public String getView(String viewName) {
		//	"./login.jsp"
		return prefix + viewName + suffix;
	}
}
