package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

public class Procedimiento extends Model{

	@Required 
	public String descripcion;
	
	@Required
	public String tiempoEstimado;
	
	public double valor;
	
	public Procedimiento(String descripcion, String tiempoEstimado, double valor){
		this.descripcion = descripcion;
		this.tiempoEstimado = tiempoEstimado;
		this.valor = valor;
	}
}
