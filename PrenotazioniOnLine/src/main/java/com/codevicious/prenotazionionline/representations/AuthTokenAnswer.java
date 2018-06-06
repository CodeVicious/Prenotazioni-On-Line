package com.codevicious.prenotazionionline.representations;

public class AuthTokenAnswer {


	String access_token;
	String token_type;
	int expires_in;
	
	public AuthTokenAnswer(String access_token, String token_type, int expires_in) {
		super();
		this.access_token = access_token;
		this.token_type = token_type;
		this.expires_in = expires_in;
	}

	public String getAccess_token() {
		return access_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	
	
	
}