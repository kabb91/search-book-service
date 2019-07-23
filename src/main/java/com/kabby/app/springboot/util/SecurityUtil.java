package com.kabby.app.springboot.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityUtil {
	Logger logger = LoggerFactory.getLogger(SecurityUtil.class);
	
	/**
	 * 패스워드 암호화(SHA-256)을 하기 위한 함수
	 * 
	 * @param str
	 * @return str (암호화된 String)
	 */
	public String encryptSHA256(String str) {
		String sha= "";
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes());
			byte byteData[] = md.digest();
			StringBuffer sb=new StringBuffer();
			for(int i=0; i<byteData.length; i++) {
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			sha=sb.toString();
		}catch (NoSuchAlgorithmException e) {
			logger.warn("Encrypt Error : NoSuchAlgorihmException");
			sha=null;
		}
		return sha;
	}
}
