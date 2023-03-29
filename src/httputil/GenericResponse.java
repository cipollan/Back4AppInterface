package httputil;

import java.io.Serializable;



public class GenericResponse<T> implements Serializable{

	public GenericResponse(T t) {
		super();
		this.t = t;
		System.out.println ( "GenericResponse Constructor  " + t.getClass()); 
	}

	

	public GenericResponse() {
		
	}



	private static final long serialVersionUID = 3929998046439360823L;
	
	public T t;

	public synchronized T getT() {
		return t;
	}



	public synchronized void setT(T t) {
		this.t = t;
	}

 
}
