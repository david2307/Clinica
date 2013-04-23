package controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import models.Doctor;
import models.DoctorPaciente;
import models.HistorialMedico;
import models.Municipio;
import models.Paciente;
import models.Pregunta;
import models.Usuario;

import play.data.validation.Required;
import play.mvc.Controller;

public class Doctores extends Controller {
	
	   //GET 
		public static void perfilDoctor(){
			Usuario usuario = Usuario.find("byNickname", Security.connected()).first();
			if(usuario!= null){
				Doctor doctor = Doctor.find("byUsuario", usuario).first();
				if(doctor!=null ){
					render(doctor);
				}else{
					render("Doctores/show.html");
				}
			}else{
				render("Doctores/show.html");
			}
			
		}
	
	
	    //GET 
		public void mostrarCrearDoctor(){
			render();
		}
	
	
	    //POST
		public static void crearDoctor(	@Required(message="El nombre es requerido") String nombre,
										@Required(message="El apellido es requerido") String apellido,
										@Required(message="La fecha de nacimiento es requerida") Date fechanac,
										@Required(message="El sexo es requerido") String sexo,
										@Required(message="La direccion es requerido") String direccion,
										@Required(message="El telefono es requerido") String telefono,
										@Required(message="El usuario es requerido") Usuario usuario,
										@Required(message="El municipio es requerido") Municipio municipio,
										String dpi){
			if (validation.hasErrors()){
				render(nombre, apellido, fechanac, sexo, direccion, telefono, dpi,municipio);
			}
				Doctor doctor = new Doctor(nombre, apellido, fechanac, sexo, direccion, telefono, dpi,usuario, municipio );	
				doctor.save();
				flash.success("Doctor creado correctamente");
				perfilDoctor();
		}
		
		//POST 
		public static void modificaDoctor(@Required(message="El id es requerido") long idPaciente,
										  @Required(message="El nombre es requerido") String nombre,
										  @Required(message="El apellido es requerido") String apellido,
										  @Required(message="La fecha de nacimiento es requerida") Date fechanac,
										  @Required(message="El sexo es requerido") String sexo,
										  @Required(message="La direccion es requerido") String direccion,
										  @Required(message="El telefono es requerido") String telefono,
										  @Required(message="El usuario es requerido") Usuario usuario,
										  @Required(message="El municipio es requerido") Municipio municipio,
										  String dpi){
			if (validation.hasErrors()){
				render(idPaciente, nombre, apellido, fechanac, sexo, direccion, telefono, dpi,municipio);
			}else{
				Doctor doctor = new Doctor(nombre, apellido, fechanac, sexo, direccion, telefono, dpi,usuario, municipio );
				if(doctor.validateAndSave()){
					flash.success("Doctor modificado correctamente");
					perfilDoctor();
				}else{
					render("Doctores/show.html");
				}
				
				
			}
			
	}
		
		
		
	//GET
	public static void pacientesDoctor(){
		Usuario usuario = Usuario.find("byNickName", Security.connected()).first();
		if(usuario != null){
			Doctor doctor = Doctor.find("byUsuario", usuario).first();
		    if(doctor != null){
		    	List<Paciente> pacientes = Paciente.find("ByDoctor", doctor).fetch();
		    	if(pacientes != null){
		    		render(pacientes);
		    	}else{
		    		perfilDoctor();
		    	}
		    }else{
		    	render("Doctores/show.html");
		    }
		}else{
			render("Doctores/show.html");
		}
		
	}
}
