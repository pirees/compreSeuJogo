package compreseujogo.teste;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.junit.jupiter.api.Test;

import compreseujogo.model.bo.EmailBo;

public class EmailTest {

	@Test
	public void novaVenda() throws Exception {
		EmailBo bo = new EmailBo();
		String mensagem = "Teste para o envio através da bo do email com Junit 5";
		System.out.println(bo.novaVenda("Teste do Junit", mensagem, "yasmine9836@uorak.com", "jaye9112@uorak.com"));
	}

	@Test
	public void email1() {
		final String emailP = "jaye9112@uorak.com";
		final String emailI = "java.compreseujogo@gmail.com";
		final String senha = "geovann!1";

		SimpleEmail email = new SimpleEmail();
		// Utilize o hostname do seu provedor de email
		System.out.println("alterando hostname...");
		email.setDebug(true);
		email.setHostName("smtp.gmail.com");

		// Quando a porta utilizada não é a padrão (gmail = 465)
		email.setSmtpPort(465);

		// Para autenticar no servidor é necessário chamar os dois métodos abaixo
		System.out.println("autenticando...");
		email.setSSL(true);
		email.setTLS(true);
		email.setAuthentication(emailI, senha);
		System.out.println("+ Informações");
		// Adicione os destinatários
		try {
			email.addTo(emailP, "faxodi4594@sekris.com");

			// Configure o seu email do qual enviará
			email.setFrom(emailI);

			// Adicione um assunto
			email.setSubject("O meu e-mail está funcionando");

			// Adicione a mensagem do email
			email.setMsg("Teste- Integrador");
			System.out.println("enviando...");
			email.send();
			System.out.println("Email enviado!");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void email2() {
		Properties props = new Properties();
		/** Parâmetros de conexão com servidor Gmail */
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("java.compreseujogo@gmail.com", "geovann!1");
			}
		});

		/** Ativa Debug para sessão */
		session.setDebug(true);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("java.compreseujogo@gmail.com"));
			// Remetente

			Address[] toUser = InternetAddress // Destinatário(s)
					.parse("ander.lemos.jr@gmail.com");

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("Enviando email com JavaMail");// Assunto
			message.setText("Enviei este email utilizando JavaMail com minha conta GMail!");
			/** Método para enviar a mensagem criada */
			Transport.send(message);

			System.out.println("Feito!!!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void email3() {
		final String username = "java.compreseujogo@gmail.com";
		final String password = "geovann!1";

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("geovannicorsino@gmail.com"));
			message.setSubject("Testing Gmail SSL");
			message.setText("Dear Mail Crawler," + "\n\n Please do not spam my email!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
