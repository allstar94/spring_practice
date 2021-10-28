package com.company.couplingtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// ���� Ŭ����


@Component("tv")
public class SamsungTV implements TV {
	//sonySpeaker Ÿ���� �ɹ�����
	@Autowired
	private SonySpeaker speaker;
	private int price; 
	
	public SamsungTV(SonySpeaker speaker) {
		System.out.println("--> �Ｚ2 ��ü ����");
		this.speaker = speaker;
	}
	
	/*
	 * setter �޼ҵ�� ������ �����̳ʰ� �ڵ����� ȣ���ϸ�,
	 * ȣ���ϴ� ������ <bean> ��ü ���� �����̴�. ���� setter ��������
	 * �����Ϸ��� setter �޼ҵ�Ӹ� �ƴ϶� �⺻ �����ڵ� �ݵ�� �ʿ��ϴ�
	 */
	public void setSpeaker(SonySpeaker speaker) {
		System.out.println("-->setspeaker() ȣ��");
		this.speaker = speaker;
	}
	public void setPrice(int price) {
		System.out.println("-->setsprice() ȣ��");
		this.price = price;
	}
	public SamsungTV(SonySpeaker speaker,int price) {
		System.out.println("--> �Ｚ3 ��ü ����");
		this.speaker = speaker;
		this.price = price;
	}
	
	
	public SamsungTV() {
		System.out.println("--> �Ｚ ��ü ����");
	}

	@Override
	public void powerOn() {
		System.out.println("samsung --> tv�� Ų��" );
	}
	@Override
	public void powerOff() {
		System.out.println("samsung --> tv�� ����");
	}


	@Override
	public void soundUp() {
//		speaker = new Sonyspeaker();
		speaker.volumnUp();
//		System.out.println("samsung --> ������ �ø���");
	}

	
	@Override
	public void soundDown() {
//		speaker = new Sonyspeaker();
		speaker.volumnDown();
//		System.out.println("samsung --> ������ �����");		
	}

	
}
