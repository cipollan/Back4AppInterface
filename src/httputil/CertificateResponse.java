
package httputil;

import java.util.ArrayList;
import java.util.List;
 

import model.Certificate;

public class CertificateResponse {
   
	public CertificateResponse() {
		super();
		results = new ArrayList<>();
	}

	private List<Certificate> results;

    public List<Certificate> getResults() {
        return results;
    }

    public void setResults(List<Certificate> results) {
        this.results = results;
    }
}