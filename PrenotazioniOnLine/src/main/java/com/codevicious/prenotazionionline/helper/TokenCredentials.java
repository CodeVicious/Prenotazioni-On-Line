package com.codevicious.prenotazionionline.helper;

import java.security.SecureRandom;
import java.sql.Timestamp;

import com.google.common.io.BaseEncoding;

public class TokenCredentials {

	private static final SecureRandom PRNG = new SecureRandom();

	public static String generateRandomToken(String username, Timestamp expires) {
		
		final byte[] tokenBytes = new byte[20];
		PRNG.nextBytes(tokenBytes);		
		String keysource = username+expires+tokenBytes;
		
		return BaseEncoding.base64().encode(keysource.getBytes());
	}

}
