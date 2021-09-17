package exceptions;

public class IncorrectBetException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public IncorrectBetException() {
		super();
	}
	
	public IncorrectBetException(String in) {
		super(in);
	}

}
