package models;

import play.*;
import play.data.validation.Email;
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
	public int telefono;
	
	@Required
	public String dpi;
	
	@Required
	@Email
	public String email;
	
	@Required
	public String nombreEmergencia;
	
	@Required 
	public int telefonoEmergencia;
	
	public Date ultimaVisita;
	
	public String referido;
	
	public String observaciones;
	
	@ManyToOne
	public Usuario usuario;
	
	@ManyToOne
	public Municipio municipio;
	
	
	public Paciente(String nombre, String apellido, Date fechaNac, String sexo, int telefono, String dpi, String email, String nombreEmergencia, int telefonoEmergencia, Date ultimaVisita, String referido, String observaciones, Usuario usuario, Municipio municipio ){
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNac = fechaNac;
		this.sexo = sexo;
		this.telefono = telefono;
	    this.dpi = dpi;
	    this.email = email;
	    this.nombreEmergencia = nombreEmergencia;
	    this.telefonoEmergencia =telefonoEmergencia;
	    this.ultimaVisita = ultimaVisita;
	    this.referido = referido;
	    this.observaciones = observaciones;
	    this.usuario = usuario;
	    this.municipio = municipio;
	}
	
}

