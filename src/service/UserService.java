package service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import httpConnector.MyHttpHub;
import httputil.AddressResponse;
import httputil.GenericResponse;
import httputil.UserResponse;
import interf.ObjectHandlerInterface;
import model.User;

public class UserService implements ObjectHandlerInterface {
	
	static Logger logger = Logger.getLogger(UserService.class.getName());
	
	public UserService(User u) {
		super();
		this.u = u;
	}

	private User u;

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}
	
    
    public List<User> objectFind(User u)
    {
    	List<User> userList = new ArrayList<>();
    
    	return userList;
    }
    
    public <T> GenericResponse<T> doMapResponseToObj (String responseBody) 
	{
		Gson gsonL = new Gson();
		logger.debug ( " BEGIN UserService.doMapResponseToObj              <"  );
		
	    UserResponse   userResponse = gsonL.fromJson(responseBody, UserResponse.class);
	    GenericResponse<UserResponse>  gres = new GenericResponse<>(userResponse);
	   
	    logger.debug ( " END UserService.doMapResponseToObj              <"  );
	    
		return (GenericResponse<T>) gres;
	}

	@Override
	public <T> List<T> objectFind(Object o) {
		List<User> userList = new ArrayList<>();
		User uin = (User)o;
		
		logger.debug ( " BEGIN UserService.objectFind <" + uin.getCognome());
		
		logger.debug ( " END   UserService.objectFind <"  );
		
    	return (List<T>) userList;
	}

	@Override
	public int objectStore(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int objectDelete(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
