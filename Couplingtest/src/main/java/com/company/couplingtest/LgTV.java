package com.company.couplingtest;
/*
 * samsungTV�� lgTV�� �޼ҵ� �ñ״���(signature)�� ���� �ٸ��� �����Ǿ� �ִ�.
 */

public class LgTV implements TV {
	public LgTV() {
		System.out.println("--> lg ��ü ����");
	}
	@Override
	public void powerOn() {
		System.out.println("LG --> tv�� Ų��");
	}

	@Override
	public void powerOff() {
		System.out.println("LG --> tv�� ����");
	}

	@Override
	public void soundUp() {
		System.out.println("LG --> ������ �ø���");
	}

	@Override
	public void soundDown() {
		System.out.println("LG --> ������ �����");		
	}
}
