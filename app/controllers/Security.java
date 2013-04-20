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
}
