package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class Doctor extends Model {
	
	@Required
	public String nombre;
	
	@Required
	public String apellido;
	
	public Date fechaNac;
	
	public String sexo;
	
	@Required
	public String direccion;
	
	@Required
	public String telefono;
	
	@Required
	public String dpi;
	
	@ManyToOne
	public Usuario usuario;
	
	@ManyToOne
	public Municipio municipio;
	
	
	public Doctor(String nombre, String apellido, Date fechaNac, String sexo, String direccion, String telefono, String dpi, Usuario usuario, Municipio municipio ){
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNac = fechaNac;
		this.sexo = sexo;
		this.direccion = direccion;
		this.telefono = telefono;
	    this.dpi = dpi;
	    this.usuario = usuario;
	    this.municipio = municipio;
	}
	
}
