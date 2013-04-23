package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class Sintoma extends Model{
	
	@Required
	public String descripcion;
	
	@ManyToOne
	public TipoSintoma tipoSintoma;
	
	public Sintoma(String descripcion, TipoSintoma tipoSintoma){
		this.descripcion = descripcion;
		this.tipoSintoma = tipoSintoma;
	}
}
