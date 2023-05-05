package cn.arts.cyyarts.exception;

public class ModifyUserException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ModifyUserException() {
		super();
	}
	
	public ModifyUserException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ModifyUserException(String message) {
		super(message);
	}

	public ModifyUserException(Throwable cause) {
		super(cause);
	}
}