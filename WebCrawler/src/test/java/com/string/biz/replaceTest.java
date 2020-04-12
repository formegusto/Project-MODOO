package com.string.biz;

public class replaceTest {

	public static void main(String[] args) {
		String test = "test|입니다";
		
		if("|".equals("|")) {
			System.out.println("이거는 익식합니다");
		}
		System.out.println(test.replaceAll("[|]", ""));

	}

}
