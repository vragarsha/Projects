package movies.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class MovieAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 405802649322364762L;

	public MovieAlreadyExistsException(String message) {
		super(message);
	}

	public MovieAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
