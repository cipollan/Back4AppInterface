package model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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
