package org.upiita.spring.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name="posts")
public class Post {
	
	@Id
	@SequenceGenerator(name="idPostSequencia",sequenceName="post_id_seq",allocationSize=1)
	@GeneratedValue(generator="idPostSequencia", strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private Integer id;
	
	@Column(name="titulo")
	private String titulo;
	
	@Column(name="contenido")
	private String contenido;
	
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@OneToMany(mappedBy="post", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SELECT)
	private List<Comentario> comentarios;

	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	@ManyToMany(mappedBy="posts")
	private List<Categoria> categorias;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

}
