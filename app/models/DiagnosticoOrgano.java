package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class DiagnosticoOrgano extends Model{

	@Required
	public String descripcion;
	
	@Required
	public int estado;
	
	@ManyToOne
	public DiagnosticoGeneral diagnosticoGeneral;
	
	@ManyToOne
	public Organo organo;
	
	public DiagnosticoOrgano(String descripcion, int estado, DiagnosticoGeneral diagnosticoGeneral, Organo organo){
		this.descripcion = descripcion;
		this.estado = estado;
		this.diagnosticoGeneral = diagnosticoGeneral;
		this.organo = organo;
	}
}
