package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;


public class Cita extends Model {
	
	@Required 
	public Date fechaPeticion;
	
	@Required
	public String descripcion;
	
	@Required
	public Date	fechaProgramada;
	
	@Required
	public Date fechaRelizacion;
	
	@Required
	public int estado;
	
	@ManyToOne
	public Paciente paciente;
	
	@ManyToOne
	public Doctor doctor;
	
	public Cita(Date fechaPeticion, String descripcion, Date fechaProgramada, Date fechaRealizacion, int estado, Paciente paciente, Doctor doctor){
		this.fechaPeticion = fechaPeticion;
		this.descripcion = descripcion;
		this.fechaProgramada = fechaProgramada;
		this.fechaRelizacion = fechaRealizacion;
		this.estado = estado;
		this.paciente =paciente;
		this.doctor = doctor;
		
	}
	
	
	

}
