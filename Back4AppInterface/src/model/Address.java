package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Address {
	
	@JsonIgnore
	private String objectId;
	
	public String via;
	public String civico;
	public String frazione;
	public String comune;
	public String provincia;
	public String stato;
	public String when;
	

	public synchronized String getWhen() {
		return when;
	}


	public synchronized void setWhen(String when) {
		this.when = when;
	}


	public Address() {
		objectId = "";
	}


	public synchronized String getObjectId() {
		return objectId;
	}


	public synchronized void setObjectId(String objectId) {
		this.objectId = objectId;
	}


	public synchronized String getVia() {
		return via;
	}


	public synchronized void setVia(String via) {
		this.via = via;
	}


	public synchronized String getCivico() {
		return civico;
	}


	public synchronized void setCivico(String civico) {
		this.civico = civico;
	}


	public synchronized String getFrazione() {
		return frazione;
	}


	public synchronized void setFrazione(String frazione) {
		this.frazione = frazione;
	}


	public synchronized String getComune() {
		return comune;
	}


	public synchronized void setComune(String comune) {
		this.comune = comune;
	}


	public synchronized String getProvincia() {
		return provincia;
	}


	public synchronized void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	public synchronized String getStato() {
		return stato;
	}


	public synchronized void setStato(String stato) {
		this.stato = stato;
	}

}
