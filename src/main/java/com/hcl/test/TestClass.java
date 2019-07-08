package com.hcl.test;

public class TestClass {

	public static void main(String[] args) {
		
		String email = "laxman.verma@gmail.com";
		System.out.println("old email : "+email);
		email = email.substring(0, email.indexOf('@')+1)+"jpmorgan.com";
		System.out.println("new email : "+email);

	}
}