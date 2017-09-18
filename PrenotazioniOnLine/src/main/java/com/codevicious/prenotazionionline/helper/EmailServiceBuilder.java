package com.codevicious.prenotazionionline.helper;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.codevicious.prenotazionionline.PrenotazioniOnLineConfiguration;

public class EmailServiceBuilder {
	private final PrenotazioniOnLineConfiguration cfg;

	public EmailServiceBuilder(PrenotazioniOnLineConfiguration cfg) {
		this.cfg = cfg;
	}
	
	
	public HtmlEmail build(){
		HtmlEmail he =  new HtmlEmail();
		he.setHostName(cfg.getSMTPHost().get());
		he.setSmtpPort(cfg.getSMTPPort().get());
		
		if(cfg.getSMTPUname().isPresent() && 
				cfg.getSMTPPw().isPresent()){
			he.setAuthenticator(new DefaultAuthenticator(cfg.getSMTPUname().get(), cfg.getSMTPPw().get()));
		}
		
		try {
			he.setFrom("ced@comune.san-miniato.pi.it");
			he.addTo("gcodevico@comune.san-miniato.pi.it");
			he.addTo("svolpi@comune.san-miniato.pi.it");
		} catch (EmailException e) {
			e.printStackTrace();
		}
		
		return he;
	}

}
