package controllers;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import models.DiagnosticoSintoma;
import models.Receta;
import models.Cita;
import models.Sintoma;
import models.Tratamiento;
import models.PlanTratamiento;
import models.DetalleReceta;


import play.data.validation.Required;
import play.mvc.Controller;

public class Recetas extends Controller{
	
	//GET 
	public static void mostrarCrearReceta(Cita cita,Tratamiento tratamiento, PlanTratamiento planTratamiento){
		render(cita, tratamiento, planTratamiento);
	}
	
	//POST
	public static void crearReceta(@Required(message="La descripcion es requerida")String descripcion,
								   @Required(message="La fecha es requirida")Date fecha,
								   @Required(message="El cita es requerido")Cita cita,
								   @Required(message="El detalle de la receta es requerido")List<DetalleReceta> detalles){
		if(validation.hasErrors()){
			render("Recetas/mostrarCrearReceta.html",descripcion,fecha,cita,detalles);
		}
		
		Receta receta = new Receta(descripcion,fecha,cita);
		receta.save();
		
		if(receta!=null){
			Iterator it = detalles.iterator();
			
			while(it.hasNext()){
				DetalleReceta detalle = (DetalleReceta)it.next();
				String desc = detalle.descripcion;
				String cantidad = detalle.cantidad;
				String tiempo = detalle.tiempo;
				Tratamiento tratamiento = detalle.tratamiento;
				PlanTratamiento plan = detalle.planTratamiento;
				DetalleReceta detalleReceta = new DetalleReceta(desc,cantidad,tiempo,receta,tratamiento,plan);
				detalleReceta.save();
				
			}
			
			flash.success("La receta ha sido agregada exitosamente");
			Security.onAuthenticated();
		}
	}

	
	//GET
	public static void mostrarModificarReceta(@Required(message="El id de la cita es requerido")Cita cita){
		if(validation.hasErrors()){
			Security.onAuthenticated();
		}
		Receta receta = Receta.find("byCita",cita).first();
		
			if(receta !=null){
				List<DetalleReceta> detalles = DetalleReceta.find("byReceta", receta).fetch();
			
					if(detalles != null){
						render(cita,receta,detalles);
					}else{
						render(cita,receta);
					}
			}else{
				validation.equals(receta,null).message("Error, no se encontro receta para esta cita");
				render("Recetas/mostrarModificarReceta.html",cita);
			}
	}
	
	
	//POST 
	public static void modificarReceta(@Required(message="El id de la recete es requerido")Long idReceta,
			   @Required(message="La descripcion es requerida")String descripcion,
			   @Required(message="La fecha es requirida")Date fecha,
			   @Required(message="El cita es requerido")Cita cita,
			   @Required(message="El detalle de la receta es requerido")List<DetalleReceta> detalles){
		
		if(validation.hasErrors()){
			render("Recetas/mostrarModificarReceta.html",cita);
		}
		Receta receta =Receta.findById(idReceta);
		
		if(receta !=null){
			receta.descripcion = descripcion;
			receta.fecha = fecha;
			receta.cita = cita;
			
			if(receta.validateAndSave()){
				Iterator it = detalles.iterator();
				
				while(it.hasNext()){
					DetalleReceta detalle = (DetalleReceta)it.next();
					DetalleReceta buscaDetalle = DetalleReceta.findById(detalle.id);
					
					if(buscaDetalle != null){
						buscaDetalle.descripcion = detalle.descripcion;
						buscaDetalle.cantidad = detalle.cantidad;
						buscaDetalle.tiempo = detalle.tiempo;
						buscaDetalle.tratamiento = detalle.tratamiento;
					    buscaDetalle.planTratamiento = detalle.planTratamiento;
					    
					    if(buscaDetalle.validateAndSave()){
					    	flash.success("La receta fue modificada exitosamente");
					    	Security.onAuthenticated();
					    }
					    
					}
				}
			}else{
				validation.equals(receta,null).message("Error, No se pudo modificar la receta");
				render("Recetas/mostrar/modificarReceta.html",cita);
			}
			
		}else{
			validation.equals(receta,null).message("Error, no se encontro receta");
			render("Recetas/mostrarModificarReceta.html", cita);
		}
	}
	
}
