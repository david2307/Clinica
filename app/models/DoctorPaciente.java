package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

public class DoctorPaciente {
	
	@OneToMany
	public Doctor doctor;
	
	@OneToMany
	public Paciente paciente;
	
	public DoctorPaciente(Doctor doctor, Paciente paciente){
		this.doctor = doctor;
		this.paciente = paciente;
	}

}
