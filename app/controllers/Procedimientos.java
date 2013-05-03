package controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import models.Procedimiento;


import play.data.validation.Required;
import play.mvc.Controller;

public class Procedimientos extends Controller {
	
	
	
	//GET
	public static void mostrarCrearProcedimiento(){
		render();
	}
	
	//POST 
	public static void crearProcedimiento(@Required(message="La descripcion es requerida")String descripcion, 
										  @Required(message="El tiempo estimado es Requerido")String tiempoEstimado,
										  @Required(message="El valor es requerido")double valor){
		if(validation.hasErrors()){
			render("Procedimientos/mostrarCrearProcedimiento.html",descripcion, tiempoEstimado, valor);
		}
		
		Procedimiento procedimiento = new Procedimiento(descripcion,tiempoEstimado,valor);
		procedimiento.save();
		flash.success("El procedimiento se agrego exitosamente");
		Security.onAuthenticated();
	}

}
