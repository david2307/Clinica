package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.parser.*;

import models.Cita;
import models.Doctor;
import models.Paciente;
import models.Procedimiento;
import models.Usuario;

import play.data.validation.Required;
import play.mvc.Controller;

public class Citas extends Controller {
	
	public static void mostrar(){
		render();
	}
	
	public static void crearCita(
			@Required(message="La descripcion es requerida") String descripcion,
			@Required(message="La Fecha es requerida") Date fechaProgramada,
			@Required(message="El paciente es requerido") Long idPaciente,
			@Required(message="Los procedimientos son requeridos") String procedimientos) throws ParseException{
		if (validation.hasErrors()){
			render("Citas/crearCita.html", descripcion, fechaProgramada, idPaciente);
		}
		Paciente paciente = Paciente.findById(idPaciente);
		if (paciente != null){
			Doctor doctor = Doctor.find("byUsuario", Usuario.find("byNickName", Security.connected()).first()).first();
			if (doctor != null){
				Date fechaPeticion = new Date();
				Cita cita = new Cita(fechaPeticion, descripcion, fechaProgramada, null, 0, paciente, doctor).save();
				if (cita != null){
					JSONParser parser = new JSONParser();
					Object obj = parser.parse(procedimientos);
					JSONArray procedures = (JSONArray)obj;
					for (int i=0; i<procedures.size(); i++){
						ArrayList procedure = (ArrayList) procedures.get(i);
						Long idProcedimiento = (Long) procedure.get(0);
						Procedimiento procedimiento = Procedimiento.findById(idProcedimiento);
						cita.agregarCitaProcedimiento(procedimiento);
					}
				}else {
					
				}
			}else{
				
			}
		}else {
			
		}
	}
}
