package com.csxiaoyao.springmvc.util.encryptor;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// 采用SHA-1加密算法
public class SHAEncryptor implements Encryptor {

	@Override
	public String encrypt(String text) {
		if (text == null || "".equals(text.trim())) {
			return null;
		}
		String result = null;
		try {
			MessageDigest m = MessageDigest.getInstance("sha-1");
			m.update(text.getBytes("UTF8"));
			byte s[] = m.digest();
			return hex(s);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	private String hex(byte[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; ++i) {
			sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString();
	}

}
