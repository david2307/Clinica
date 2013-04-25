package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import models.Cita;
import models.DiagnosticoGeneral;
import models.Sintoma;
import models.DiagnosticoSintoma;

import play.data.validation.Required;
import play.mvc.Controller;

public class DiagnosticosGenerales extends Controller {
	
	//GET
	public static void mostrarCrearDiagnosticoGeneral(Cita cita){
		List<Sintoma> sintomas = Sintoma.findAll();
		render(cita,sintomas);
	}
	
	//POST
	public static void crearDiagnosticoGeneral(@Required(message="La descripción es requerida")String descripcion,
											   @Required(message="El tipo es requerido")int tipo,
											   @Required(message="La cita es requerida")long cita,
											   @Required(message="Sintoma es requerido")List<Sintoma> sintomas){
		if(validation.hasErrors()){
			render("DiagnosticosGenerales/mostrarCrearDiagnosticoGeneral",descripcion,tipo,cita,sintomas);
		}
		Cita buscaCita = Cita.findById(cita);
		
		if(buscaCita != null){
			DiagnosticoGeneral diagnosticoGeneral = new DiagnosticoGeneral(descripcion,tipo,buscaCita).save();
			
			if(diagnosticoGeneral !=null){
				Iterator it = sintomas.iterator();
				
				while(it.hasNext()){
					Sintoma sintoma = (Sintoma)it.next();
					DiagnosticoSintoma diagnosticoSintoma =new DiagnosticoSintoma(diagnosticoGeneral,sintoma);
					diagnosticoSintoma.save();					
				}
				
				flash.success("Diagnostico General creado exitosamente");
				Security.onAuthenticated();
			}
		}else{
			validation.equals(buscaCita,null).message("Error, no se encontro la cita");
			render("DiagnosticosGenerales/crearDiagnosticoGeneral",descripcion,tipo,cita);
		}
		
	}

	
	//GET
	public static void buscaDiagnosticoGeneral(@Required(message="La cita es requerida")Cita cita){
		DiagnosticoGeneral diagnostico = DiagnosticoGeneral.find("byCita", cita).first();
		if(diagnostico != null)
		{
			List<DiagnosticoSintoma> diagnosticoSintomas = DiagnosticoSintoma.find("byDiagnosticoGeneral",diagnostico).fetch(); 
			
			List<Sintoma> sintomas = new ArrayList<Sintoma>();
			Iterator it = diagnosticoSintomas.iterator();
			while(it.hasNext()){
				DiagnosticoSintoma diagnosticoSintoma = (DiagnosticoSintoma)it.next();
				Sintoma addSintoma = diagnosticoSintoma.sintoma;
				sintomas.add(addSintoma);
			}
			
			modificarDiagnosticoGeneral(diagnostico.id,diagnostico.descripcion,diagnostico.tipo,sintomas);
		}else{
			validation.equals(diagnostico,null).message("Error, no se encontro diagnostico para la cita");
			render(cita);
		}
	}
	
	//POST
	public static void modificarDiagnosticoGeneral(@Required(message="El diagnostico es requerido")long idDiagnosticoGeneral,
												   @Required(message="La descripcion es requerida")String descripcion,
												   @Required(message="El tipo es requirido")int tipo,
												   @Required(message="Sintoma es requerido")List<Sintoma> sintomas){
		if(validation.hasErrors()){
			render("DiagnosticosGenerales/modificarDiagnosticoGeneral",idDiagnosticoGeneral,descripcion,tipo);
		}
		
		DiagnosticoGeneral diagnostico = DiagnosticoGeneral.findById(idDiagnosticoGeneral);
		if(diagnostico != null){
			diagnostico.descripcion = descripcion;
			diagnostico.tipo = tipo;
			if(diagnostico.validateAndSave()){
				flash.success("Diagnostico General modificado exitosamente");
				Security.onAuthenticated();				
			}
		}else{
			validation.equals(diagnostico,null).message("Error, no se encontro diagnostico");
			render("DiagnosticosGenerales/crearDiagnosticoGeneral",idDiagnosticoGeneral,descripcion,tipo);
		}
	}
	
	//POST
	public static void eliminarDiagnosticoGeneral(@Required(message="El diagnostico general es requerido") long idDiagnosticoGeneral){
		
		if(validation.hasErrors()){
			render();
		}
		DiagnosticoGeneral diagnostico = DiagnosticoGeneral.findById(idDiagnosticoGeneral);
		
		if(diagnostico != null){
			diagnostico.delete();
			flash.success("Diagnostico General eliminado exitosamente");
			Security.onAuthenticated();
		}else{
			validation.equals(diagnostico,null).message("Error, no se encontro diagnostico");
			Security.onAuthenticated();
		}
	}
	
	
	//GET
	public static void mostrarAsociarSintomas(){
		
	}
}
