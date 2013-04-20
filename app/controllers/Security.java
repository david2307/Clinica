package controllers;

import models.Usuario;

public class Security extends Secure.Security{
	
	static boolean authenticate(String nickName, String password){
		return Usuario.connect(nickName, password) != null;
	}
	
	static boolean check(String profile) {
		Usuario us = Usuario.find("byNickName", Security.connected()).first();
		if (profile.equals(us.tipoUsuario.descripcion)){
			return true;
		}
		return false;
	}
	
	static void onAuthenticated(){
		String usuario = connected();
    	Usuario us = Usuario.find("byNickName", usuario).first();
    	if (us.tipoUsuario.descripcion.equals("Administrador")){
    		Dashboard.administrador();
    	}else if (us.tipoUsuario.descripcion.equals("Doctor")){
    		Dashboard.doctores();
    	}else{
    		Dashboard.pacientes();
    	}
	}
}
