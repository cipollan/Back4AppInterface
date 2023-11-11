package model;

import java.sql.Timestamp;

public class Persona {

	
	public Persona( String nome, String cognome, int age,Timestamp timestamp) {
		super();
		this.when = (Timestamp) timestamp;
		this.nome = nome;
		this.cognome = cognome;
		this.age = age;
	}
	public String nome;
	public String cognome;
	public int age;
	public Timestamp when;
	
	public Persona(String nome, String cognome, int age) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.age = age;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
