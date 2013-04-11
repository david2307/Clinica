package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

public class Paciente extends Model {

	@Required
	public String nombre;
	
	@Required
	public String apellido;
	
	public Date fechaNac;
	
	public String sexo;
	
	@Required
	public String telefono;
	
	@Required
	public String dpi;
	
	@Required
	public String nombreEmergencia;
	
	@Required 
	public String telefonoEmergencia;
	
	public String ultimaVisita;
	
	public String referido;
	
	public String observaciones;
	
	@OneToMany
	public Usuario usuario;
	
	@OneToMany
	public Municipio municipio;
	
	
	public Paciente(String nombre, String apellido, Date fechaNac, String sexo, String telefono, String dpi, String nombreEmergencia, String telefonoEmergencia, String ultimaVisita, String referido, String observaciones, Usuario usuario, Municipio municipio ){
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNac = fechaNac;
		this.sexo = sexo;
		this.telefono = telefono;
	    this.dpi = dpi;
	    this.nombreEmergencia = nombreEmergencia;
	    this.telefonoEmergencia =telefonoEmergencia;
	    this.ultimaVisita = ultimaVisita;
	    this.referido = referido;
	    this.observaciones = observaciones;
	    this.usuario = usuario;
	    this.municipio = municipio;
	}
	
}

