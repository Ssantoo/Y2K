package com.world.Y2K.service.login;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.world.Y2K.properties.NavermailProperties;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailAuthServiceImpl implements MailAuthService{

	public Map<String, Object> getEmailAuth(String email) {
		System.out.println("email : " + email);
		HashMap<String, Object> map =new HashMap<String, Object>();
		String authCode = makeRandomNumber();
		System.out.println("authCode : " + authCode);
		String contents = getContents(authCode);
		System.out.println("contents : " + contents);
		if(isValidEmail(email)) {
			sendMail("Your EmailAuthCode.",contents, email);
			map.put("authCode", authCode);
			return map;
		}
		map.put("authCode",  "It's not an appropriate email format");
		return map;
	}
	
	public void sendMail(String subejct, String body, Object obj) {
		try {
			InternetAddress[] receiverList = new InternetAddress[1];
			receiverList[0] = new InternetAddress((String)obj);
			// SMTP �߼� Properties ����
			Properties props = getProperties();
			
			// SMTP Session ����
			Session mailSession = Session.getInstance(props, new javax.mail.Authenticator(){
				
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new javax.mail.PasswordAuthentication(NavermailProperties.MAIL_ID, NavermailProperties.MAIL_PW);
				}
				
			});
			// Mail ����
			Message mimeMessage = new MimeMessage(mailSession);
			mimeMessage.setFrom(new InternetAddress(NavermailProperties.MAIL_ID));
			mimeMessage.setRecipients(Message.RecipientType.TO, receiverList);
			// ���� ����
			mimeMessage.setSubject(subejct);
            // ���� ���� (.setText�� ����ϸ� �ܼ� �ؽ�Ʈ ���� ����)
			mimeMessage.setContent(body, "text/html; charset=UTF-8");
			// Mail �߼�
			System.out.println(mimeMessage);
			Transport.send(mimeMessage);
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			log.error("Error SendMail");
		}
	}

	private Properties getProperties() {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", NavermailProperties.HOST);
		props.put("mail.smtp.port", NavermailProperties.PORT);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.enable","true");
		props.put("mail.smtp.ssl.trust", NavermailProperties.HOST);
		props.put("mail.smtp.auth", "true");
		return props;
	}
	
	public String makeRandomNumber() {
		String randomNumber = "";
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		for(int i = 0 ; i < 6 ; i ++) {
			randomNumber += random.nextInt(10);
		}
		return randomNumber;
	}
	
	public static boolean isValidEmail(String email) {
		String format = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
		Pattern pattern = Pattern.compile(format);
		Matcher matcher = pattern.matcher(email);
		if(matcher.matches()) {
			return true;
		}
		log.error("It's not an appropriate email format");
		return false;
	}
	
	public String getContents(String authCode) {
		 StringBuffer contents = new StringBuffer();
		   contents.append("<h1>Email AuthCode</h1><br><br>");
		   contents.append("<p> Your EmailAuthCode "+authCode+" <br>Please input authcode.</p><br>");
		return contents.toString();
	}
	
}
