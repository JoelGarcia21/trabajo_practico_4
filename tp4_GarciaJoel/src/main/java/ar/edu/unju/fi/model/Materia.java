package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

@Component
public class Materia {
	private int codigo;
	private String nombre;
	private String curso;
	private byte horas;
	private boolean modalidad;
	private Docente docente;
	private Carrera carrera;
	
	public Materia(int codigo, String nombre, String curso, byte horas, boolean modalidad, Docente docente,
			Carrera carrera) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.curso = curso;
		this.horas = horas;
		this.modalidad = modalidad;
		this.docente = docente;
		this.carrera = carrera;
	}
	public Materia() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public byte getHoras() {
		return horas;
	}
	public void setHoras(byte horas) {
		this.horas = horas;
	}
	public boolean isModalidad() {
		return modalidad;
	}
	public void setModalidad(boolean modalidad) {
		this.modalidad = modalidad;
	}
	public Docente getDocente() {
		return docente;
	}
	public void setDocente(Docente docente) {
		this.docente = docente;
	}
	public Carrera getCarrera() {
		return carrera;
	}
	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}
	
	@Override
	public String toString() {
		return "Materia [codigo=" + codigo + ", nombre=" + nombre + ", curso=" + curso + ", horas=" + horas
				+ ", modalidad=" + modalidad + ", docente=" + docente + ", carrera=" + carrera + "]";
	}
	
}
