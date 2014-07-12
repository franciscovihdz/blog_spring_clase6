package org.upiita.spring.forma;


import org.hibernate.validator.constraints.NotEmpty;

public class UsuarioForma {

	private Integer id;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String password;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
