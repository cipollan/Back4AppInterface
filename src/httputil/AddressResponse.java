package httputil;
import java.util.List;

import model.Address;


public class AddressResponse {
	
	private List<Address> results;
	
	public List<Address> getResults() {
        return results;
    }

    public void setResults(List<Address> results) {
        this.results = results;
    }

	public AddressResponse() {
		// TODO Auto-generated constructor stub
	}

}

