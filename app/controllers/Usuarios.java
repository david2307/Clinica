package controllers;

import java.util.Date;
import java.util.List;

import models.Municipio;
import models.Paciente;
import models.Pregunta;
import models.Usuario;

import play.data.validation.Required;
import play.libs.Crypto;
import play.mvc.Controller;

public class Usuarios extends Controller {
	
	//GET
	public static void mostrarCrearUsuario(@Required(message="El token es requerido") String token){
		if(validation.hasErrors()){
			render();
		}
		Crypto crypto = new Crypto();
		long idUsuario = Long.valueOf(crypto.decryptAES(token));
		Paciente paciente = Paciente.findById(idUsuario);
		if(paciente != null){
			render(paciente);
		}else{
			render("Pacientes/show.html");
		}
	}
	
	//POST
	public static void crearUsuario(@Required(message="El nickName es requerido")String nickName, 
			                        @Required(message="El password es requerido")String password,
			                        @Required(message="El paciente es requerido")Long paciente){
		
		if(validation.hasErrors()){
			render("Pacientes/crearUsuario",nickName,password,paciente);
		}
		//validando si el nickName existe
		Usuario usuario = Usuario.find("byNickName", nickName).first();
		if(usuario == null){
			Crypto crypto = new Crypto();
			String pass = crypto.encryptAES(String.valueOf(password));
			Usuario crearUsuario = new Usuario(nickName,pass,true,null).save();
			
			if(crearUsuario !=null){
				Paciente buscaPaciente = Paciente.findById(paciente);
				
				if(buscaPaciente != null){
					
					buscaPaciente.usuario = crearUsuario;
					buscaPaciente.validateAndSave();
					Security.authenticate(nickName, password);
					Security.onAuthenticated();
					
				}else{
					validation.equals(buscaPaciente,null).message("Error, no se encontro el paciente");
					render("Pacientes/crearUsuario",nickName,password,paciente);
				}
			}else{
				validation.equals(crearUsuario,null).message("Error, no se pudo crear el usuario");
				render("Pacientes/crearUsuario",nickName,password,paciente);
			}
		}else{
			validation.equals(usuario,null).message("Error, el nickName ya está registrado");
			render("Pacientes/crearUsuario",nickName,password,paciente);
		}
	}
}
