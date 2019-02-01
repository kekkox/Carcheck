package it.carcheck.utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {

	public static String Encrypt(String password){
		
		String encrypted = "";
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-512");
			digest.update(password.getBytes("UTF-8"), 0, password.length());
			encrypted = byteArrayToHexString(digest.digest());
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
		
		return encrypted;
	}
	
	/**
	 * Convert a byte array into String
	 * @param b The byte array to convert
	 * @return The string that matches to the array of byte passed
	 * 
	 * Each byte is converted into a positive byte if necessary adding 256 to it.
	 * After that it is added 256 to every byte and it is converted into hex base.
	 * Finally we store for each byte the last 2 chars.
	 */
	private static String byteArrayToHexString(byte[] b) {
		  String result = "";
		  for (int i=0; i < b.length; i++) {
			  /*  Uncomment this to understand how it work
			   System.out.println("Normal: " + b[i] + ", Op: " + (b[i] & 0xFF) + ", MOD: " + ((b[i] & 0xFF) + 0x100));
			   System.out.println(Integer.toString((b[i] & 0xff) + 0x100));
			   System.out.println(Integer.toString((b[i] & 0xff) + 0x100, 16) + " - " + Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1)); 
			   * */
		    result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		  }
		  return result;
		}
}
