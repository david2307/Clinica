package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

public class DiagnosticoOrgano {

	@Required
	public String descripcion;
	
	@Required
	public int estado;
	
	@OneToMany
	public DiagnosticoGeneral diagnosticoGeneral;
	
	@OneToMany
	public Organo organo;
	
	public DiagnosticoOrgano(String descripcion, int estado, DiagnosticoGeneral diagnosticoGeneral, Organo organo){
		this.descripcion = descripcion;
		this.estado = estado;
		this.diagnosticoGeneral = diagnosticoGeneral;
		this.organo = organo;
	}
}
