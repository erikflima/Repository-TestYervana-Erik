package com.erik.projeto.utils;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


public class EmailSender {

	
	
	@Autowired
	private JavaMailSender enviadorDeEmailDoJava;     //Objeto que eh capaz de realizar envio de emails.
	
	@Autowired
	private TemplateEngine templateEngineDoThymeleaf; //Objeto que processa html com thymeleaf da biblioteca do ThymeLeaf que estou usando.
	
	
	//Metodo que de fato faz um envio de email.
	public void enviarEmail( String       remetente, 
			                 List<String> destinatarios, 
			                 String       assunto, 
			                 String       mensagem      ){	
		try {
			
			//Objeto obrigatorio para usar mais pra frente no objeto "enviadorDeEmailDoJava".
			MimeMessage emailFormatadoASerEnviado = enviadorDeEmailDoJava.createMimeMessage();
			
			
			//Crio um objeto "MimeMessageHelper" que serve para ajudar a configurar o objeto "enviadorDeEmailDoJava".
			MimeMessageHelper formatadoDeEmail = new MimeMessageHelper( emailFormatadoASerEnviado, "UTF-8"); //Dizendo que o conteudo do email vai ser padrao UTF-8.
			
			formatadoDeEmail.setFrom   ( remetente);                                                   //Setando o remetente.
			formatadoDeEmail.setTo     ( destinatarios.toArray( new String[destinatarios.size()] )  ); //Setando o destinatario ou destinarios. Obs: Aqui eu pego o objeto do tipo "List<String>" e converto para "String[]".
			formatadoDeEmail.setSubject( assunto);                                                     //Setando o assunto.
			formatadoDeEmail.setText   ( mensagem, true);                                              //Setando a mensagem. Passo um segundo parametro como "true" ou "false" para indicar se a mensagem tem html ou nao. Pois pode ser uma string com tags html.
			
			
			//Por fim, faco finalmenet o envio do e-mail.
			enviadorDeEmailDoJava.send( emailFormatadoASerEnviado );
			
			 
		} catch ( MessagingException exceptionOcorrida ) {
			
			throw new RuntimeException( "Problems with sending email!", exceptionOcorrida ); 
		}
		
	}
	
	
	//Metodo que de fato faz um envio de email.
	public void enviarEmailComTemplateHtml( String              remetente, 
			                                List<String>        destinatarios, 
			                                String              assunto, 
			                                String              templateHtmlParaOEmail, 
			     		                    Map<String, Object> variaveisRecebidasPorParametro ){
		
		
		//Crio um objeto "Context" da biblioteca do Thymeleaf. Ele vai server para colocar objetos java que quero passar para a pagina html que esta usando thymeleaf.
		Context contextoDoTimeLeaf = new Context( new Locale("pt", "BR") );
		
		
		
		/*Expressao lambda maluca.
		  Aqui eu pego o Map, tiro todos os objetos que estao dentro dele, e coloco dento do contextoDoTimeLeaf. 
		  Seria o mesmo esquema de extrair as variaveis do map manualmente e tals.
		*/
		variaveisRecebidasPorParametro.entrySet().forEach(				
				                                           variavelExtraida -> contextoDoTimeLeaf.setVariable( variavelExtraida.getKey(), variavelExtraida.getValue() )
				                                         );
		
		
		
		//Aqui eu crio literalmente o codigo html que vai ser o corpo da email que vai ser enviado.
		String mensagemDoEmailEmFormatoHtmlComThymeleaf = templateEngineDoThymeleaf.process( templateHtmlParaOEmail, contextoDoTimeLeaf );
		
		System.out.println("Html with thymeleaf that was generated and will be the body of the email that will be sent: \n" +mensagemDoEmailEmFormatoHtmlComThymeleaf );
		
		
		
		// Chamo o metodo que por fim vai realizar o envio de email.
		enviarEmail( remetente, destinatarios, assunto, mensagemDoEmailEmFormatoHtmlComThymeleaf );
	}	

	
	//Realiza envio de email para todos os usarios que tem lancamentos com data de vencimento vencida
	public void realizarEnvioDeEmailsSobreLancamentosVencidos(	List<Lancamento> listaDeLancamentosVencidos, 
			                                                    List<Usuario>    listaDeDestinatarios        ){
		
		
		//Crio um mapa para colocar objetos java. O objetivo eh passar esses objetos para a pagina html que esta usando Thymeleaf.
		Map<String, Object> variaveisASeremPassadasPorParametro = new HashMap<>();
		
		//Coloco o objeto que quero dentro do map.
		//Obs: A ideia eh que o template html que vou usar, possa acessar esse objeto com o nome de "lancamentos". Ou seja, a pagina vai ter acesso a essa objeto ai.
		variaveisASeremPassadasPorParametro.put("lancamentos", listaDeLancamentosVencidos);		
		
		
		/*Expressao lambda maluca.
		  Aqui eu pego a lista de Usuarios, extraio o email de cada usuario e transformo em uma lista de String. 
		  Mas ao inves de usar essa expressao, eu poderia ter feio isso manualmente tambem. */
		List<String> listaDeEmailsDeDestinatarios = listaDeDestinatarios.stream()
				                                                        .map( u -> u.getEmail() )
				                                                        .collect( Collectors.toList() );
		
		
		
		//Aqui eu indico onde esta o template(template-para-envio-de-email.html) html que vou usar para colocar no email que vou enviar.
		String templateHtmlParaOEmail = "z-arquivos-do-Erik-templates-html-para-email/template-para-envio-de-email";
		
		
		enviarEmailComTemplateHtml( "erik.alves253@gmail.com",            //Remetente
				                    listaDeEmailsDeDestinatarios,         //Destinatarios
				                    "Lançamentos vencidos",               //Assunto do e-mail
				                    templateHtmlParaOEmail,               //Template html para ser usado no e-mail que sera enviado.
				                    variaveisASeremPassadasPorParametro   //Parametros para o o template html possa puxar e pegar dados
				                   );
	}

}