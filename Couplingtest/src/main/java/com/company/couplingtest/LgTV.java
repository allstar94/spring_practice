package com.company.couplingtest;
/*
 * samsungTV와 lgTV는 메소드 시그니쳐(signature)가 서로 다르게 구현되어 있다.
 */

public class LgTV implements TV {
	public LgTV() {
		System.out.println("--> lg 객체 생성");
	}
	@Override
	public void powerOn() {
		System.out.println("LG --> tv를 킨다");
	}

	@Override
	public void powerOff() {
		System.out.println("LG --> tv를 끈다");
	}

	@Override
	public void soundUp() {
		System.out.println("LG --> 볼륨을 올린다");
	}

	@Override
	public void soundDown() {
		System.out.println("LG --> 볼륨을 낮춘다");		
	}
}
