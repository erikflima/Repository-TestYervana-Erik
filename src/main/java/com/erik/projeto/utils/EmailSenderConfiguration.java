package com.erik.projeto.utils;
import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailSenderConfiguration {
	
	@Bean
	public JavaMailSender javaMailSender() {
		
		//Object that will save the necessary settings for sending an email.
		Properties propriedadesParaEnvioDeEmail = new Properties();
		propriedadesParaEnvioDeEmail.put("mail.transport.protocol",    "smtp"); //I say the email sending protocol that will be used.
		propriedadesParaEnvioDeEmail.put("mail.smtp.auth",              true ); //I say that sending an email will have authentication.
		propriedadesParaEnvioDeEmail.put("mail.smtp.starttls.enable",   true ); //I say this is a secure email delivery.
		propriedadesParaEnvioDeEmail.put("mail.smtp.connectiontimeout", 10000); //I say the maximum amount of time I will wait for an email send connection. I set the time in milesegunos.
		
		//Object that will execute the email.
		JavaMailSenderImpl enviadorDeEmail = new JavaMailSenderImpl();
		enviadorDeEmail.setJavaMailProperties( propriedadesParaEnvioDeEmail );
		enviadorDeEmail.setHost    ("smtp.gmail.com"          );  
		enviadorDeEmail.setPort    (587                       );
		enviadorDeEmail.setUsername("erik.alves253@gmail.com" );
		enviadorDeEmail.setPassword("xxxxxxxxx"               );

		//I send the email sender object for Spring to use.
		return enviadorDeEmail;	
	
	}

}