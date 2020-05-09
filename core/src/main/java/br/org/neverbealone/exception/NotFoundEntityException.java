package br.org.neverbealone.exception;

public class NotFoundEntityException extends Exception {

	private static final long serialVersionUID = 9091593692325608108L;
	
	public NotFoundEntityException() {
		super();
	}
	
	public NotFoundEntityException(String message) {
		super(message);
	}

}
