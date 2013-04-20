package jobs;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import play.jobs.Job;
import play.libs.Crypto;
import play.libs.Mail;

public class NuevoUsuario extends Job{
	
	public long id;
	public String emailCliente;
	
	public NuevoUsuario(long id, String emailCliente){
		this.id = id;
		this.emailCliente = emailCliente;
	}
	
	public void doJob() throws EmailException{
		Crypto crypto = new Crypto();
		String token = crypto.encryptAES(String.valueOf(id));
		System.out.println(token);
		String url = "http://localhost:9000/Usuarios/mostrarCrearUsuario?token=" + token;
		SimpleEmail email = new SimpleEmail();
		email.setFrom("mariog.david@gmail.com");
		email.addTo(emailCliente);
		email.setSubject("Activacion de Usuario");
		email.setMsg("URL: " + url);
		Mail.send(email);
	}

}
