package com.company.couplingtest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {
	public static void main(String[] args) {
//		BeanFactory factory = new BeanFactory();
//		TV tv = (TV)factory.getBean(args[0]);
		//1. ������ �����̳ʸ� �����Ѵ�.
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
		//2. ������ �����̳ʷκ��� �ʿ��� ��ü�� ��û(Lookup)�Ѵ�.
		TV tv = (TV)factory.getBean("tv");
		tv.powerOn();
		tv.soundUp(); 
		tv.soundDown();
		tv.powerOff();

		//3. ������ �����̳ʸ� �����Ѵ�.
		factory.close();
	}
}
