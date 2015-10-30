package com.demo;

import org.apache.commons.lang.RandomStringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobleUtil {
	
	public static char [] INVITE_CODE_CHARS = "0123456789abcdefghigklmeopqrstuvwxyz".toCharArray();
	
	
	public static String createValidateCode(){
		return RandomStringUtils.randomNumeric(4);
	}
	
	public static String createInviteCode(){
		return RandomStringUtils.random(6, INVITE_CODE_CHARS);
	}
	
	/**
	 * 手机号验证
	 * 
	 * @param  str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false; 
		p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches(); 
		return b;
	}
	
	public static void main(String[] args) {
		System.out.println(createInviteCode());
		System.out.println(RandomStringUtils.randomAlphabetic(6));
	}
}
