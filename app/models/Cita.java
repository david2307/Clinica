package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class Cita extends Model {
	
	@Required 
	public Date fechaPeticion;
	
	@Required
	public String descripcion;
	
	@Required
	public Date	fechaProgramada;
	
	public Date fechaRelizacion;
	
	@Required
	public int estado;
	
	@Required
	@ManyToOne
	public Paciente paciente;
	
	@Required
	@ManyToOne
	public Doctor doctor;
	
	@OneToMany(mappedBy="cita", cascade=CascadeType.ALL)
	public List<CitaProcedimiento> citaProcedimiento;
	
	public Cita(Date fechaPeticion, String descripcion, Date fechaProgramada, Date fechaRealizacion, int estado, Paciente paciente, Doctor doctor){
		this.citaProcedimiento = new ArrayList<CitaProcedimiento>();
		this.fechaPeticion = fechaPeticion;
		this.descripcion = descripcion;
		this.fechaProgramada = fechaProgramada;
		this.fechaRelizacion = fechaRealizacion;
		this.estado = estado;
		this.paciente = paciente;
		this.doctor = doctor;
	}
	
	public Cita agregarCitaProcedimiento(Procedimiento procedimiento){
		CitaProcedimiento citaProcedimiento = new CitaProcedimiento(procedimiento, this);
		this.citaProcedimiento.add(citaProcedimiento);
		this.save();
		return this;
	}
}
