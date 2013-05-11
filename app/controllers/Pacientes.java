package controllers;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import jobs.NuevoUsuario;

import models.Doctor;
import models.DoctorPaciente;
import models.HistorialMedico;
import models.Municipio;
import models.Paciente;
import models.Pregunta;
import models.Usuario;

import play.data.validation.Email;
import play.data.validation.Required;
import play.mvc.Controller;

public class Pacientes extends Controller {
	
	public static void listadoPacientes(){
		Doctor doctor = Doctor.find("byUsuario", Usuario.find("byNickName", Security.connected()).first()).first();
		List<DoctorPaciente> doctorPaciente = DoctorPaciente.find("byDoctor", doctor).fetch();
		
		render(doctorPaciente);
	}
	
	public static void mostrarPaciente(@Required(message="El ID del Paciente es Requerido") Long idPaciente){
		if (validation.hasErrors()){
			render("Pacientes/listadoPacientes");
		}
		Paciente paciente = Paciente.findById(idPaciente);
		if (paciente != null){
			render(paciente);
		}else{
			validation.equals(paciente, null).message("El Paciente no Existe");
			render("Pacientes/listadoPacientes");
		}
	}
	
	public static void mostrarCrearPaciente(){
		render();
	}
	
	//POST -- Metodo para crear un nuevo paciente y hace el llamado al Job para enviar el mail con el token de acceso
	public static void crearPaciente(
			@Required(message="El Nombre es requerido") String nombre,
			@Required(message="El Apellido es requerido") String apellido,
			@Required(message="Fecha de Nacimiento es requerida") Date fechaNac,
			String sexo, @Required(message="La direccion es requerdia")String direccion,
			@Required(message="Telefono es requerdio") int telefono,
			@Required(message="El DPI es requerido") String dpi,
			@Required(message="El email es requerido")@Email(message="El email es incorrecto") String email,
			@Required(message="El Nombre de el contacto de Emergencia es requerido") String nombreEmergencia,
			@Required(message="Telefono de el contacto de Emergencia es requerido") int telefonoEmergencia,
			Date ultimaVisita, String referido, String observaciones, Municipio municipio){
		
		if(validation.hasErrors()){
			render("Pacientes/mostrarCrearPaciente.html", nombre, apellido, fechaNac, sexo, telefono, dpi, email, nombreEmergencia, telefonoEmergencia,
					ultimaVisita, referido, observaciones, municipio);
		}
		Paciente paciente = new Paciente(nombre, apellido, fechaNac, sexo, direccion, telefono, dpi, email, nombreEmergencia, telefonoEmergencia,
										ultimaVisita, referido, observaciones, null, null, null).save();
		Doctor doctor = Doctor.find("byUsuario", Usuario.find("byNickName", Security.connected()).first()).first();
		new DoctorPaciente(doctor, paciente).save();
		new NuevoUsuario(paciente.id, paciente.email).now();
		flash.success("Paciente creado correctamente");
		mostrarFichaMedica(paciente.id);
	}
	
	//GET
	public static void mostrarFichaMedica(Long idPaciente){
		List<Pregunta> preguntas = Pregunta.findAll();
		render(idPaciente, preguntas);
	}
	
	//POST
	public static void crearFichaMedica(@Required Long idPaciente, @Required Map<String, String> respuestas){
		if (validation.hasErrors()){
			System.out.println("" + idPaciente);
			render("Pacientes/mostrarFichaMedica", idPaciente, respuestas);
		}
		
		System.out.println("" + idPaciente);
		System.out.println("" + respuestas.size());
		Iterator it = respuestas.entrySet().iterator();
		Paciente paciente = Paciente.findById(idPaciente);
		
		while(it.hasNext()){
			Map.Entry e = (Map.Entry)it.next();
			long id = Long.valueOf(e.getKey().toString());
			boolean resultado = Boolean.valueOf(e.getValue().toString());
			
			Pregunta pregunta = Pregunta.findById(id);
			HistorialMedico historial = new HistorialMedico(resultado, paciente, pregunta);
			historial.save();
		}
		mostrarPaciente(paciente.id);
	}
	
	//GET
	public static void mostrarBuscaPaciente(){
		render();
	}
		
	//POST 
	public static void buscarPaciente(@Required(message="El dato para la busqueda es requerido") String busqueda){
		if (validation.hasErrors()){
			render("Pacientes/showBuscaPaciente",busqueda);
		}
		long idPaciente = Long.parseLong(busqueda);
			
		Paciente paciente = Paciente.find("Select p From Paciente p where p.nombre like ? or p.apellido like ? or p.id ==?", busqueda, busqueda, idPaciente).first();
			
		if(paciente !=null){
			mostrarPaciente(idPaciente);
		}else{
			validation.equals(paciente,null).message("Error, no se encontro el paciente");
			render("Paciente/showBuscPaciente.html",busqueda);
		}
	}
	
	public static void mostrarModificarPaciente(Long idPaciente){
		Paciente paciente = Paciente.findById(idPaciente);
		render(paciente);
	}
	
	//POST
	public static void modificarPaciente(
			@Required(message="El ID del paciente es requerido") long idPaciente,
			@Required(message="El Nombre es requerido") String nombre,
			@Required(message="El Apellido es requerido") String apellido,
			@Required(message="Fecha de Nacimiento es requerida") Date fechaNac,
			String sexo, @Required(message="La direccion es requerida") String direccion,
			@Required(message="Telefono es requerdio") int telefono,
			@Required(message="El DPI es requerido") String dpi,
			@Required(message="El email es requerido")@Email(message="El email es incorrecto") String email,
			@Required(message="El Nombre de el contacto de Emergencia es requerido") String nombreEmergencia,
			@Required(message="Telefono de el contacto de Emergencia es requerido") int telefonoEmergencia,
			Date ultimaVisita, String referido, String observaciones, Municipio municipio){
		if (validation.hasErrors()){
			Paciente paciente = Paciente.findById(idPaciente);
			render("Pacientes/mostrarModificarPaciente.html", paciente);
		}
			
		Paciente paciente = Paciente.findById(idPaciente);
		if(paciente != null){
			paciente.nombre = nombre;
			paciente.apellido = apellido;
			paciente.fechaNac = fechaNac;
			paciente.sexo = sexo;
			paciente.telefono = telefono;
			paciente.dpi = dpi;
			paciente.nombreEmergencia =nombreEmergencia;
			paciente.telefonoEmergencia = telefonoEmergencia;
			paciente.ultimaVisita = ultimaVisita;
			paciente.referido = referido;
			paciente.observaciones = observaciones;
		
			if(paciente.validateAndSave()){
				flash.success("El paciente se modificado exitosamente");
				mostrarPaciente(paciente.id);					
			}else{
				validation.equals(paciente,!paciente.validateAndSave()).message("Error, no se pudo modificar el paciente");
				mostrarModificarPaciente(paciente.id);
			}
		}else{
			validation.equals(paciente,null).message("Error, no se encontro el paciente");
			listadoPacientes();
		}		
	}
}