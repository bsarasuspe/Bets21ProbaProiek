package exceptions;

public class EvenAlreadyExists  extends Exception {
	 private static final long serialVersionUID = 1L;
	public EvenAlreadyExists()
	  {
	    super();
	  }
	
	public EvenAlreadyExists(String s)
	  {
	    super(s);
	  }
}
