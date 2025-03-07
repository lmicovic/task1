package task1.exception;

public class HttpResponseException extends RuntimeException {

	private static final long serialVersionUID = 3455879763600236728L;

	public HttpResponseException(String messsage) {
		super(messsage);
	}
	
	public HttpResponseException(String messsage, Throwable cause) {
		super(messsage, cause);
	}
	
}
