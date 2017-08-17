package com.tower;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = new Md5Hash(new Md5Hash("123456")).toHex();
		System.out.println(str);
		//4280d89a5a03f812751f504cc10ee8a5
	}

}
