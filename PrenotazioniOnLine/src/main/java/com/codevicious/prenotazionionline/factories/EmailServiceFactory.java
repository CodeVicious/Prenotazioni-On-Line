package com.codevicious.prenotazionionline.factories;

import java.util.Optional;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmailServiceFactory {

	@NotEmpty
	private String SMTPHost;
	
	@Min(1)
	@Max(65535)
	private int SMTPPort;

	private Optional<String> SMTPUsername;
	private Optional<String> SMTPPw;

	public HtmlEmail build() {
		HtmlEmail he = new HtmlEmail();
		he.setHostName(SMTPHost);
		he.setSmtpPort(SMTPPort);
		
		if(SMTPUsername.isPresent() && SMTPPw.isPresent()) {
			he.setAuthenticator(new DefaultAuthenticator(SMTPUsername.get(), SMTPPw.get()));
		}
		
        return he;
    }

	@JsonProperty("host")
	public String getSMTPHost() {
		return SMTPHost;
	}

	@JsonProperty("port")
	public int getSMTPPort() {
		return SMTPPort;
	}

	@JsonProperty("username")
	public String getSMTPUsername() {
		return SMTPUsername.get();
	}

	@JsonProperty("pw")
	public String getSMTPPw() {
		return SMTPPw.get();
	}

	@JsonProperty("host")
	public void setSMTPHost(String sMTPHost) {
		SMTPHost = sMTPHost;
	}

	@JsonProperty("port")
	public void setSMTPPort(int sMTPPort) {
		SMTPPort = sMTPPort;
	}

	@JsonProperty("username")
	public void setSMTPUsername(String sMTPUsername) {
		SMTPUsername = Optional.ofNullable(sMTPUsername);
	}

	@JsonProperty("pw")
	public void setSMTPPw(String sMTPPw) {
		SMTPPw = Optional.ofNullable(sMTPPw);
	}
		

}
