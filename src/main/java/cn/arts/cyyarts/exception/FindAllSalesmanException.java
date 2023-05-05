package cn.arts.cyyarts.exception;

public class FindAllSalesmanException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public FindAllSalesmanException() {
		super();
	}
	
	public FindAllSalesmanException(String message,Throwable cause) {
		super(message, cause);
	}
	
	public FindAllSalesmanException(String message) {
		super(message);
	}
	
	public FindAllSalesmanException(Throwable cause) {
		super(cause);
	}
}