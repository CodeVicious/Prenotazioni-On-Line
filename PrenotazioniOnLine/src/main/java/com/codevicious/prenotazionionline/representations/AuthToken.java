package com.codevicious.prenotazionionline.representations;

public class AuthToken {

	long id;
	String auth_token;
	long user_id;
	int expires;

	public AuthToken(long id, String auth_token, long user_id, int expires) {
		this.id = id;
		this.auth_token = auth_token;
		this.user_id = user_id;
		this.expires = expires;
	}

	public long getId() {
		return id;
	}

	public String getAuth_token() {
		return auth_token;
	}

	public long getUser_id() {
		return user_id;
	}

	public int getExpires() {
		return expires;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setAuth_token(String auth_token) {
		this.auth_token = auth_token;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public void setExpires(int expires) {
		this.expires = expires;
	}

}
