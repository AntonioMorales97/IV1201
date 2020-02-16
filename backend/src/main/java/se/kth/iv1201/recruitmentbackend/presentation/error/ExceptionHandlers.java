package se.kth.iv1201.recruitmentbackend.presentation.error;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import org.springframework.web.bind.annotation.ExceptionHandler;

import se.kth.iv1201.recruitmentbackend.application.exception.ApplicationNotFoundException;
import se.kth.iv1201.recruitmentbackend.application.exception.IllegalTransactionException;
import se.kth.iv1201.recruitmentbackend.application.exception.OutdatedApplicationException;
import se.kth.iv1201.recruitmentbackend.application.exception.StatusNotFoundException;
import se.kth.iv1201.recruitmentbackend.presentation.exception.InvalidCredentials;

/**
 * Global Exception handler, that handles all the exceptions in this application.
 *
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandlers {
	private final String INVALID_METHOD_ARGUMENTS = "Invalid method arguments";
	private final String METHOD_ARGUMENT_TYPE_MISMATCH = "The type of the given arguments are wrong";
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlers.class);
	
	@ExceptionHandler(InvalidCredentials.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	ErrorResponse invalidCredentials(InvalidCredentials exc) {
		logger.error(exc.getMessage());
		return new ErrorResponse(HttpStatus.UNAUTHORIZED.getReasonPhrase(), exc.getMessage(), exc.getCode());
	}
	
	
	@ExceptionHandler(OutdatedApplicationException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	ErrorResponse outdatedApplicationException(OutdatedApplicationException exc) {
		logger.error(exc.getMessage());
		return new ErrorResponse(HttpStatus.CONFLICT.getReasonPhrase(), exc.getMessage(), exc.getCode());
	}
	/**
	 * Handles <code>IllegalTransactionException</code>s.
	 * 
	 * @param exc The exception with the message.
	 * @return the exception message.
	 */
	@ExceptionHandler(IllegalTransactionException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	ErrorResponse illegalTransactionExceptionHandler(IllegalTransactionException exc) {
		logger.error(exc.getMessage());
		return new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(), exc.getMessage(),exc.getCode());
	}
	/**
	 * Handles <code>ApplicationNotFoundException</code>s.
	 * 
	 * @param exc The exception with the message.
	 * @return the exception message.
	 */
	@ExceptionHandler(ApplicationNotFoundException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	ErrorResponse ApplicationNotFoundExceptionHandler(ApplicationNotFoundException exc) {
		logger.error(exc.getMessage());
		return new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(), exc.getMessage(),exc.getCode());
	}
	/**
	 * Handles <code>StatusNotFoundException</code>s.
	 * 
	 * @param exc The exception with the message.
	 * @return the exception message.
	 */
	@ExceptionHandler(StatusNotFoundException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	ErrorResponse statusNotFoundExceptionHandler(StatusNotFoundException exc) {
		logger.error(exc.getMessage());
		return new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(), exc.getMessage(),exc.getCode());
	}
	/**
	 * Handler for invalid method arguments. For example, missing fields in
	 * <code>Application</code>s.
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
			logger.error(fieldError.getDefaultMessage());
			violations.add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
		});
		return vr;
	}
	
	/**
	 * Handles <code>NoHandlerException</code>s.
	 * 
	 * @param exc The exception.
	 * @return the exception message.
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	ErrorResponse noHandlerFoundExceptionHandler(NoHandlerFoundException exc) {
		logger.error(exc.getMessage());
		return new ErrorResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), exc.getMessage());
	}
	/**
	 * Handles <code>MissingServletRequestParameterException</code>s.
	 * 
	 * @param exc The exception.
	 * @return the exception message.
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ErrorResponse missingServletRequestParameterExceptionHandler( MissingServletRequestParameterException exc){
		logger.error(exc.getMessage());
		return new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), exc.getMessage());
	}
	
	/**
	 * Handler for not supported methods.
	 * 
	 * @param exc The <code>HttpRequestMethodNotSupportedException</code>.
	 * @return the exception message.
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	ErrorResponse methodNotAllowedHandler(HttpRequestMethodNotSupportedException exc) {
		logger.error(exc.getMessage());
		return new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(), exc.getMessage());
	}
	
	/**
	 * Handler for method argument type mismatches.
	 * 
	 * @param exc The <code>MethodArgumentTypeMismatchException</code>.
	 * @return the exception message with the expected type.
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ErrorResponse methodArgumentTypeMismatchHandler(MethodArgumentTypeMismatchException exc) {
		logger.error(exc.getMessage());
		return new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), METHOD_ARGUMENT_TYPE_MISMATCH
				+ ", expected: " + exc.getRequiredType().getSimpleName());
	}
	
	
	/**
	 * Handler for bad http messages received.
	 * 
	 * @param exc The <code>HttpMessageNotReadableException</code>.
	 * @return a sad face.
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ErrorResponse messageNotReadableExceptionHandler(HttpMessageNotReadableException exc) {
		logger.error(exc.getMessage());
		return new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), "Could not read the message!:(");
	}

	/**
	 * Handles the exceptions that are not handled by any other exception handler.
	 * 
	 * @param exc the exception to be handled.
	 * @return the exception message.
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	ErrorResponse generalExceptionHandler(Exception exc) {
		System.out.println(exc.getClass());
		exc.printStackTrace();
		logger.error("Error: "+exc.getClass() + "With message: "+exc.getMessage());
		return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), exc.getMessage());
	}
	
	
}
