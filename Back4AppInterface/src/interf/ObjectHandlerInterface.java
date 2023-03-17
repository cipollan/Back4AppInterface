package interf;

import java.util.List;

import httputil.GenericResponse;
import httputil.UserResponse;
import model.User;

public interface ObjectHandlerInterface {

	 <T> GenericResponse<T> doMapResponseToObj (String responseBody) ;
	 <T> List<T> objectFind (Object o) ;
	 int objectStore 		(Object o) ;
	 int objectDelete 		(Object o) ;
	 
	 
	 
}
