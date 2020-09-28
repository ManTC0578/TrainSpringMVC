package com.banvien.myplatform.core.utils;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public final class DesEncrypterUtils {
	private transient final Log log = LogFactory.getLog(DesEncrypterUtils.class);
	private Cipher ecipher;
	private Cipher dcipher;
	private static DesEncrypterUtils instance = new DesEncrypterUtils();
	private static final String SECURITY_PHASE = "mYPLAtfoRM23";
	public static DesEncrypterUtils getInstance() {
		return instance;
	}
	private DesEncrypterUtils() {
		try {
			 // 8-bytes Salt
	        byte[] salt = {
	            (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
	            (byte)0x56, (byte)0x34, (byte)0xE3, (byte)0x03
	        };

			int iterationCount = 19;
			KeySpec keySpec = new PBEKeySpec(SECURITY_PHASE.toCharArray(), salt, iterationCount);
			
			SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
			ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());
			
		    AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
			ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

		} catch (java.security.NoSuchAlgorithmException e) {
			log.error(e.getMessage());
		} catch (java.security.InvalidKeyException e) {
			log.error(e.getMessage());
		} catch (InvalidKeySpecException e) {
			log.error(e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			log.error(e.getMessage());
		} catch (NoSuchPaddingException e) {
			log.error(e.getMessage());
		}
	}

	public String encrypt(String str) {
		try {
			byte[] utf8 = str.getBytes("UTF8");
			byte[] enc = ecipher.doFinal(utf8);
			return new sun.misc.BASE64Encoder().encode(enc);
		} catch (javax.crypto.BadPaddingException e) {
			log.error(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			log.error(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
		} catch (java.io.IOException e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public String decrypt(String str) {
		try {
			byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
			byte[] utf8 = dcipher.doFinal(dec);
			return new String(utf8, "UTF8");
		} catch (javax.crypto.BadPaddingException e) {
			log.error(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			log.error(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
		} catch (java.io.IOException e) {
			log.error(e.getMessage());
		}
		return null;
	}
	public static void main(String[] args) {
		System.out.print(DesEncrypterUtils.getInstance().encrypt("123456"));
	}
}
