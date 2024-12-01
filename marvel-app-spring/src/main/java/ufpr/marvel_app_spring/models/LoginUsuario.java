package ufpr.marvel_app_spring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginUsuario {
	private String email;
	private String senha;
	
	@Override
	public String toString() {
		return "LoginUsuario [email=" + email + ", senha=" + senha + "]";
	}
}
