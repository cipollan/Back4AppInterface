package model;

import java.util.Date;

public class Certificate {
	
	public Certificate(String certiType, int certifNum, String dateOfEmission) {
		super();
		CertiType = certiType;
		CertifNum = certifNum;
		this.dateOfEmission = dateOfEmission;
	}
	private String CertiType;
	private int CertifNum;
	private String dateOfEmission;
	public synchronized String getCertiType() {
		return CertiType;
	}
	public synchronized void setCertiType(String certiType) {
		CertiType = certiType;
	}
	public synchronized int getCertifNum() {
		return CertifNum;
	}
	public synchronized void setCertifNum(int certifNum) {
		CertifNum = certifNum;
	}
	public synchronized String getDateOfEmission() {
		return dateOfEmission;
	}
	public synchronized void setDateOfEmission(String dateOfEmission) {
		this.dateOfEmission = dateOfEmission;
	}

}
