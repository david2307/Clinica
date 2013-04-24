package controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import models.Cita;
import models.DiagnosticoGeneral;

import play.data.validation.Required;
import play.mvc.Controller;

public class DiagnosticosGenerales extends Controller {
	
	//GET
	public static void mostrarCrearDiagnosticoGeneral(Cita cita){
		render(cita);
	}
	
	//POST
	public static void crearDiagnosticoGeneral(@Required(message="La descripción es requerida")String descripcion,
											   @Required(message="El tipo es requerido")int tipo,
											   @Required(message="La cita es requerida")long cita){
		if(validation.hasErrors()){
			render("DiagnosticosGenerales/crearDiagnosticoGeneral",descripcion,tipo,cita);
		}
		Cita buscaCita = Cita.findById(cita);
		if(buscaCita != null){
			DiagnosticoGeneral diagnosticoGeneral = new DiagnosticoGeneral(descripcion,tipo,buscaCita).save();
			if(diagnosticoGeneral !=null){
				flash.success("Diagnostico General creado exitosamente");
				Security.onAuthenticated();
			}
		}else{
			validation.equals(buscaCita,null).message("Error, no se encontro la cita");
			render("DiagnosticosGenerales/crearDiagnosticoGeneral",descripcion,tipo,cita);
		}
		
	}
}
