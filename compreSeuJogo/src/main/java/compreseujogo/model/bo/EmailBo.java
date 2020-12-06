package compreseujogo.model.bo;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailBo {
	private SimpleEmail email;
	private final String remetente = "java.compreseujogo@gmail.com";
	private final String senha = "geovann!1";

	public EmailBo() throws EmailException {
		this.email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setSSL(true);
		email.setTLS(true);
		email.setFrom(remetente);
		email.setAuthentication(remetente, senha);
	}

	public String novaVenda(String assunto, String mensagem, String loja, String cliente) throws Exception {
		try {
			email.addTo(cliente);
			email.setSubject(assunto);
			email.setMsg(mensagem);
			email.setDebug(false);
			return email.send();
		} catch (EmailException e) {
			throw new Exception("Falha ao enviar o e-mail");
		}
	}

}
