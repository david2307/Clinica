package controllers;

import java.util.Date;
import java.util.List;

import models.Municipio;
import models.Paciente;
import models.Pregunta;
import models.Usuario;
import models.TipoUsuario;

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
			Security.onAuthenticated();
		}
	}
	
	//POST
	public static void crearUsuario(@Required(message="El nickName es requerido")String nickName, 
			                        @Required(message="El password es requerido")String password,
			                        @Required(message="El paciente es requerido")Long pacente,
			                        @Required(message="El tipo de usuario es requerido") int tipo){
		
		if(validation.hasErrors()){
			Paciente paciente = Paciente.findById(pacente);
			render("Usuarios/mostrarCrearUsuario.html",nickName,password,paciente);
		}
		//validando si el nickName existe
		Usuario usuario = Usuario.find("byNickName", nickName).first();
		Paciente paciente = Paciente.findById(pacente);
		if(usuario == null){
			System.out.println("el usuario es valido");
			Crypto crypto = new Crypto();
			String pass = crypto.encryptAES(String.valueOf(password));
			TipoUsuario tipoUsuario = TipoUsuario.findById((long)tipo);
			Usuario crearUsuario = new Usuario(nickName,pass,true,tipoUsuario).save();
						
			if(crearUsuario !=null){
				System.out.println("se creo el usuario");
				Paciente buscaPaciente = Paciente.findById(pacente);
				
				if(buscaPaciente != null){
					buscaPaciente.usuario = crearUsuario;
					buscaPaciente.save();
					System.out.println("se modifico el paciente");
					Security.onAuthenticated();
					
					//Security.authenticate(nickName, password);
				}else{
					validation.equals(buscaPaciente,null).message("Error, no se encontro el paciente");
					render("Usuarios/mostrarCrearUsuario.html",nickName,password,paciente);
				}
			}else{
				validation.equals(crearUsuario,null).message("Error, no se pudo crear el usuario");
				render("Usuarios/mostrarCrearUsuario.html",nickName,password,paciente);
			}
		}else{
			validation.equals(usuario,null).message("Error, el nickName ya está registrado");
			render("Usuarios/mostrarCrearUsuario.html",nickName,password,paciente);
		}
	}
	
	//GET 
	public static void mostrarModificarUsuario(){
		Usuario usuario = Usuario.find("byNickName", Security.connected()).first();
		if(usuario != null){
			render(usuario);
		}
	}
	
	//POST
	public static void modificarUsuario(@Required(message="El nickName es requerido") String nickName, 
										@Required(message="El password es requerido") String password,
										@Required(message="El estado es requerido")  boolean estado,
										@Required(message="El tipo de usuario es requerido") TipoUsuario tipoUsuario ){
		if(validation.hasErrors()){
			render("Usuarios/mostraModificarUsuario.html",nickName,password,estado);
		}
		Usuario usuario = Usuario.find("byNickName", nickName).first();
		
		if(usuario != null){
			usuario.password = password;
			usuario.status =estado;
			usuario.tipoUsuario = tipoUsuario;
			
			if(usuario.validateAndSave()){
				flash.success("Usuario actualizado correctamente");
				Security.onAuthenticated();
			}
		}else{
			validation.equals(usuario,null).message("Error, no se encontro el usuario");
			render("Usuarios/modificarUsuario.html",nickName,password,estado,tipoUsuario);
		}
		
	}
	
}
