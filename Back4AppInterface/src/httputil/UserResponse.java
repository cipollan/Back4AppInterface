package httputil;

import java.util.ArrayList;
import java.util.List;
 

import model.User;

public class UserResponse {
   
	public UserResponse() {
		super();
		results = new ArrayList<>();
	}

	private List<User> results;

    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
    }
}
