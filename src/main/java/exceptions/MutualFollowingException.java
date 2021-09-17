package exceptions;

public class MutualFollowingException extends Exception {
	 private static final long serialVersionUID = 1L;
	public MutualFollowingException()
	  {
	    super();
	  }
	
	public MutualFollowingException(String s)
	  {
	    super(s);
	  }
}