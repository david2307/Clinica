package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

public class DiagnosticoSintoma extends Model {
	
	@OneToMany
	public DiagnosticoGeneral diagnosticoGeneral;
	
	@OneToMany
	public Sintoma sintoma;
	
	public DiagnosticoSintoma(DiagnosticoGeneral diagnosticoGeneral, Sintoma sintoma){
		this.diagnosticoGeneral = diagnosticoGeneral;
		this.sintoma = sintoma;
	}

}
