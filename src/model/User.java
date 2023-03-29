package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
	@JsonIgnore
	private  String objectId;
	
	private String createdAt;
	private String updatedAt;
	private String cognome;
	private int  tel;
	
	public synchronized String getUpdatedAt() {
		return updatedAt;
	}
	public synchronized void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public synchronized String getCreatedAt() {
		return createdAt;
	}
	public synchronized void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public synchronized String getObjectId() {
		return objectId;
	}
	public synchronized void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	 
	
	String nome;
	public synchronized String getNome() {
		return nome;
	}
	public synchronized void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public synchronized int getTel() {
		return tel;
	}
	public synchronized void setTel(int i) {
		this.tel = i;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	 

}
