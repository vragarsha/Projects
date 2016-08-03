package movies.rest.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 405802649322364762L;

	public UserAlreadyExistsException(String message) {
		super(message);
	}

	public UserAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
