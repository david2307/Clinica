package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class DiagnosticoSintoma extends Model {
	
	@ManyToOne
	public DiagnosticoGeneral diagnosticoGeneral;
	
	@ManyToOne
	public Sintoma sintoma;
	
	public DiagnosticoSintoma(DiagnosticoGeneral diagnosticoGeneral, Sintoma sintoma){
		this.diagnosticoGeneral = diagnosticoGeneral;
		this.sintoma = sintoma;
	}

}
