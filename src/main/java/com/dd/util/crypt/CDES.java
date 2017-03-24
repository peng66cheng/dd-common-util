package com.dd.util.crypt;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class CDES {

	private static byte[] mykeys = { 0xF, 0xC, 0xB, 0xE, 0xB, 0xF, 0xA, 0xE, 0xD, 0xA, 0xA, 0xC, 0xB, 0xD, 0xF, 0xB };

	public static byte[] encrypt(String sSrc) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(mykeys, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// "算法/模式/补码方式"
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

		return encrypted;// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
	}

	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @return
	 */
	public static byte[] decrypt(byte[] content) {
		try {
			SecretKeySpec key = new SecretKeySpec(mykeys, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] AESecrypt(String content) {
		try {
			byte[] c = parseHexStr2Byte(content);
			SecretKeySpec key = new SecretKeySpec(mykeys, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(c);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将二进制转换成16进值制 ，防止byte[]数字转换成string类型时造成的数据损失
	 * 
	 * @param buf
	 * @return 返回16进制转换成的string
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();

	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 *            16进制的数组转换成String类型再传过来的参数
	 * @return 转换回来的二进制数组
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	public static String bytetoString(byte[] digest) {
		String str = "";
		String tempStr = "";

		for (int i = 1; i < digest.length; i++) {
			tempStr = (Integer.toHexString(digest[i] & 0xff));
			if (tempStr.length() == 1) {
				str = str + "0" + tempStr;
			} else {
				str = str + tempStr;
			}
		}
		return str.toLowerCase();
	}

	public static String SHA1(String inStr) {
		MessageDigest md = null;
		String outStr = null;
		try {
			md = MessageDigest.getInstance("SHA-1"); // 选择SHA-1，也可以选择MD5
			byte[] digest = md.digest(inStr.getBytes()); // 返回的是byet[]，要转化为String存储比较方便
			outStr = bytetoString(digest);
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		}
		return outStr;
	}

	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	public static String decrypt(String code) {
		byte[] codebyte = parseHexStr2Byte(code);
		return new String(decrypt(codebyte));
	}

	public static void main(String[] args) throws Exception {
		// 加密
		System.out.println(Crypt.decryptByDES("0nXzaxmwZG07XzLugghVTg=="));
	}

	public static String convertPassword(String password) {
		String pwd = CDES.decrypt(password);// 先AEC解密
		password = Crypt.encryptToDES(pwd);// 再DEC加密
		return password;
	}
}
