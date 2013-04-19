package controllers;

import java.util.Date;
import java.util.List;

import jobs.NewUser;

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
			new NewUser(paciente.id).now();
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
	
	//GET
	public static void showBuscaPaciente(){
		render();
	}
		
	//POST 
	public static void buscaPaciente(@Required(message="El dato para la busqueda es requerido") String busqueda){
		if (validation.hasErrors()){
			render("Pacientes/showBuscaPaciente",busqueda);
		}
		long idPaciente = Long.parseLong(busqueda);
			
		Paciente paciente = Paciente.find("Select p From Paciente p where p.nombre like ? or p.apellido like ? or p.id ==?", busqueda, busqueda, idPaciente).first();
			
		if(paciente !=null){
			render("Paciente/modificaPaciente.html",paciente);
		}else{
			validation.equals(paciente,null).message("Error, no se encontro el paciente");
			render("Paciente/showBuscPaciente.html",busqueda);
		}
	}
	
	//POST
	public static void modificaPaciente(@Required(message="El paciente es requerido") long idPaciente,
										@Required(message="El nombre es requerido") String nombre,
										@Required(message="El apellido es requerido") String apellido,
										@Required(message="La fecha de nacimiento es requerida") Date fechanac,
										@Required(message="El sexo es requerido") String sexo,
										@Required(message="La direccion es requerido") String direccion,
										@Required(message="El telefono es requerido") int telefono,
										String dpi, String nombreEmergencia, int telefonoEmergencia,
										@Required(message="El municipio es requerido") Municipio municipio,
										Usuario usuario, Date ultimaVisita, String requerido, String observaciones){
		if (validation.hasErrors()){
			render(idPaciente, nombre, apellido, fechanac, sexo, direccion, telefono, dpi, nombreEmergencia, telefono, dpi, nombreEmergencia,
					telefonoEmergencia, municipio, usuario, ultimaVisita, requerido, observaciones);
		}
			
		Paciente paciente = Paciente.findById(idPaciente);
		if(paciente != null){
			paciente.nombre = nombre;
			paciente.apellido = apellido;
			paciente.fechaNac = fechanac;
			paciente.sexo = sexo;
			paciente.telefono = telefono;
			paciente.dpi = dpi;
			paciente.nombreEmergencia =nombreEmergencia;
			paciente.telefonoEmergencia = telefonoEmergencia;
			paciente.municipio = municipio;
			paciente.usuario = usuario; 
			paciente.ultimaVisita = ultimaVisita;
			paciente.referido = requerido;
			paciente.observaciones = observaciones;
			
			if(paciente.validateAndSave()){
				if(paciente.validateAndSave()){
					flash.success("El paciente se modificado exitosamente");
					render("Paciente/show.hml");					
				}else{
					validation.equals(paciente,!paciente.validateAndSave()).message("Error, no se pudo modificar el paciente");
					render("Paciente/show.html");
				}
			}
		}else{
			validation.equals(paciente,null).message("Error, no se encontro el paciente");
			render("Paciente/showBuscPaciente.html");
		}		
	}
}