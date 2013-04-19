package controllers;

import java.util.Date;
import java.util.List;

import models.Municipio;
import models.Paciente;
import models.Pregunta;
import models.Usuario;

import play.data.validation.Required;
import play.mvc.Controller;

public class Pacientes extends Controller {
	
	//POST -- Metodo para crear un nuevo paciente y hace el llamado al Job para enviar el mail con el token de acceso
	public static void crearPaciente(
			@Required(message="El Nombre es requerido") String nombre,
			@Required(message="El Apellido es requerido") String apellido,
			@Required(message="Fecha de Nacimiento es requerida") Date fechaNac,
			String sexo, @Required(message="Telefono es requerdio") int telefono,
			@Required(message="El DPI es requerido") String dpi,
			@Required(message="El Nombre de el contacto de Emergencia es requerido") String nombreEmergencia,
			@Required(message="Telefono de el contacto de Emergencia es requerido") int telefonoEmergencia,
			Date ultimaVisita, String referido, String observaciones, Municipio municipio){
		
		if(validation.hasErrors()){
			render();
		}else {
			Paciente paciente = new Paciente(nombre, apellido, fechaNac, sexo, telefono, dpi, nombreEmergencia, telefonoEmergencia,
						ultimaVisita, referido, observaciones, null, municipio).save();
			flash.success("Paciente creado correctamente");
			mostrarFichaMedica(paciente);
		}
	}
	
	public static void mostrarFichaMedica(Paciente paciente){
		String nombre = paciente.nombre + " " + paciente.apellido;
		List<Pregunta> preguntas = Pregunta.findAll();
		render(nombre, preguntas);
	}
	
	public static void crearFichaMedica(){
		
	}
}
