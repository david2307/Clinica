package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

public class Usuario {

	@Required
	public String nickName;
	
	@Required
	public String password;
	
	@Required
	public boolean status;
	
	@ManyToOne
	public TipoUsuario tipoUsuario;
	
	public Usuario(String nickName, String password, boolean status, TipoUsuario tipoUsuario ){
		this.nickName = nickName;
		this.password = password;
		this.status = status;
		this.tipoUsuario = tipoUsuario;
	}
	
	
}
