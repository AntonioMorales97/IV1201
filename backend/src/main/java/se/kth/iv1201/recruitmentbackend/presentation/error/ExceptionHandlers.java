package se.kth.iv1201.recruitmentbackend.presentation.error;



import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import se.kth.iv1201.recruitmentbackend.application.exception.IllegalTransactionException;



@ControllerAdvice
@ResponseBody
public class ExceptionHandlers {
	private final String INVALID_METHOD_ARGUMENTS = "Invalid method arguments";
	private final String METHOD_ARGUMENT_TYPE_MISMATCH = "The type of the given arguments are wrong";
	
	/**
	 * Handles <code>IllegalTransactionException</code>s.
	 * 
	 * @param exc The exception with the message.
	 * @return the exception message.
	 */
	@ExceptionHandler(IllegalTransactionException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	ErrorResponse illegalTransactionExceptionHandler(IllegalTransactionException exc) {
		return new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(), exc.getMessage());
	}
	/**
	 * Handler for invalid method arguments. For example, missing fields in
	 * <code>Customer</code>s.
	 * 
	 * @param exc The <code>MethodArgumentNotValidException</code>.
	 * @return a list over all the violations.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ViolationResponse constraintViolationExceptionHandler(MethodArgumentNotValidException exc){
		ViolationResponse vr = new ViolationResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), INVALID_METHOD_ARGUMENTS);
		List<Violation> violations = vr.getViolations();
		exc.getBindingResult().getFieldErrors().forEach(fieldError -> {
			violations.add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
		});
		return vr;
	}
}
