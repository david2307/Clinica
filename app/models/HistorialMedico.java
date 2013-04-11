package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

public class HistorialMedico extends Model{
	
	@Required 
	public boolean respuesta;
	
	@OneToMany
	public Paciente paciente;
	
	@OneToMany
	public Pregunta pregunta;
	
	public HistorialMedico(boolean respuesta, Paciente paciente, Pregunta pregunta){
		this.respuesta = respuesta;
		this.pregunta = pregunta;
		this.paciente =paciente;
	}

}
