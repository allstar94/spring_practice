package com.company.couplingtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 구현 클래스


@Component("tv")
public class SamsungTV implements TV {
	//sonySpeaker 타입의 맴버변수
	@Autowired
	private SonySpeaker speaker;
	private int price; 
	
	public SamsungTV(SonySpeaker speaker) {
		System.out.println("--> 삼성2 객체 생성");
		this.speaker = speaker;
	}
	
	/*
	 * setter 메소드는 스프링 컨테이너가 자동으로 호출하며,
	 * 호출하는 시점은 <bean> 객체 생성 직후이다. 따라서 setter 인젝션이
	 * 동작하려면 setter 메소드뿐만 아니라 기본 생성자도 반드시 필요하다
	 */
	public void setSpeaker(SonySpeaker speaker) {
		System.out.println("-->setspeaker() 호출");
		this.speaker = speaker;
	}
	public void setPrice(int price) {
		System.out.println("-->setsprice() 호출");
		this.price = price;
	}
	public SamsungTV(SonySpeaker speaker,int price) {
		System.out.println("--> 삼성3 객체 생성");
		this.speaker = speaker;
		this.price = price;
	}
	
	
	public SamsungTV() {
		System.out.println("--> 삼성 객체 생성");
	}

	@Override
	public void powerOn() {
		System.out.println("samsung --> tv를 킨다" );
	}
	@Override
	public void powerOff() {
		System.out.println("samsung --> tv를 끈다");
	}


	@Override
	public void soundUp() {
//		speaker = new Sonyspeaker();
		speaker.volumnUp();
//		System.out.println("samsung --> 볼륨을 올린다");
	}

	
	@Override
	public void soundDown() {
//		speaker = new Sonyspeaker();
		speaker.volumnDown();
//		System.out.println("samsung --> 볼륨을 낮춘다");		
	}

	
}
