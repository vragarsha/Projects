package movies.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class ReviewNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 405802649322364762L;

	public ReviewNotFoundException(String message) {
		super(message);
	}

	public ReviewNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
