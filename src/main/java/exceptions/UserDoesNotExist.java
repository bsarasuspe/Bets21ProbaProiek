package exceptions;

public class UserDoesNotExist extends Exception {
	 private static final long serialVersionUID = 1L;
	 
	 public UserDoesNotExist () {
		 super();
	 }
	 
	 public UserDoesNotExist (String desk) {
		 super(desk);
	 }

}
