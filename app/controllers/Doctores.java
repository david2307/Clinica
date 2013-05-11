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
import models.TipoUsuario;
import models.Usuario;

import play.data.validation.Required;
import play.mvc.Controller;

public class Doctores extends Controller {
	
	   //GET 
		public static void perfilDoctor(long idDoctor){
			Doctor doctor = Doctor.findById(idDoctor);
				if(doctor!=null ){
					render(doctor);
				}else{
					Security.onAuthenticated();
				}
					
		}
	
	
	    //GET 
		public static void mostrarCrearDoctor(){
			render();
		}
	
	
	    //POST
		public static void crearDoctor(	@Required(message="El nombre es requerido") String nombre,
										@Required(message="El apellido es requerido") String apellido,
										@Required(message="La fecha de nacimiento es requerida") Date fechaNac,
										@Required(message="El sexo es requerido") String sexo,
										@Required(message="La direccion es requerido") String direccion,
										@Required(message="El telefono es requerido") String telefono,
										@Required(message="El usuario es requerido") String usuario,
										@Required(message="La contraseña es requerida") String password,
										Municipio municipio,
										String dpi){
			if (validation.hasErrors()){
				render("Doctores/mostrarCrearDoctor.html",nombre, apellido, fechaNac, sexo, direccion, telefono,usuario,password, dpi,municipio);
			}
				
				TipoUsuario tipoUsuario = TipoUsuario.findById((long)2);
			    Usuario usuarioNuevo = new Usuario(usuario,password,true,tipoUsuario).save();
				Doctor doctor = new Doctor(nombre, apellido, fechaNac, sexo, direccion, telefono, dpi,usuarioNuevo, null );	
				doctor.save();
				flash.success("Doctor creado correctamente");
				perfilDoctor(doctor.id);
		}
		
		
		//GET 
		public static void mostrarModificarDoctor(long idDoctor){
			Doctor doctor = Doctor.findById(idDoctor);
			render(doctor);
		}
		
		
		
		//POST 
		public static void modificarDoctor(@Required(message="El id es requerido") long idDoctor,
												  @Required(message="El nombre es requerido") String nombre,
												  @Required(message="El apellido es requerido") String apellido,
												  @Required(message="La fecha de nacimiento es requerida") Date fechaNac,
												  @Required(message="El sexo es requerido") String sexo,
												  @Required(message="La direccion es requerido") String direccion,
												  @Required(message="El telefono es requerido") String telefono,
												  Usuario usuario,
												  Municipio municipio,
												  String dpi){
					if (validation.hasErrors()){
						Doctor doctor =Doctor.findById(idDoctor);
						render("Doctores/mostrarModificarDoctor.html",doctor);
					}else{
						Doctor doctor = Doctor.findById(idDoctor);
						doctor.nombre = nombre;
						doctor.apellido = apellido;
						doctor.fechaNac = fechaNac;
						doctor.sexo = sexo;
						doctor.direccion = direccion;
						doctor.telefono = telefono;
						doctor.dpi = dpi;
						if(doctor.validateAndSave()){
							flash.success("Doctor modificado correctamente");
							perfilDoctor(doctor.id);
						}else{
							Security.onAuthenticated();
						}
						
						
					}
					
		}
		
		//GET 
		public static void listadoDoctores(){
			List<Doctor> doctores = Doctor.findAll();
			render(doctores);
		}
		
}
