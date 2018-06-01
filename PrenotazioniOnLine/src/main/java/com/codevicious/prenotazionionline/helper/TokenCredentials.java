package com.codevicious.prenotazionionline.helper;

import java.security.SecureRandom;

import com.google.common.io.BaseEncoding;

public class TokenCredentials {

	private static final SecureRandom PRNG = new SecureRandom();

	public static String generateRandomToken() {
		final byte[] tokenBytes = new byte[20];
		PRNG.nextBytes(tokenBytes);
		return BaseEncoding.base64().lowerCase().encode(tokenBytes);
	}

}
