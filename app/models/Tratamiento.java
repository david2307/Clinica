package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;


public class Tratamiento extends Model {
	
	@Required
	public String descripcion;
	
	@Required 
	public double valor;
	
	@Required
	public boolean estado;
	
	@ManyToOne
	public DiagnosticoGeneral diagnosticoGeneral;
	
	@ManyToOne
	public Procedimiento procedimiento;
	
	public Tratamiento(String descripcion, double valor, boolean estado, DiagnosticoGeneral diagnosticoGeneral, Procedimiento procedimiento){
		this.descripcion = descripcion;
		this.valor = valor;
		this.estado = estado;
		this.diagnosticoGeneral = diagnosticoGeneral;
		this.procedimiento = procedimiento;
		
	}

}
