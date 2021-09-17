package exceptions;

public class MismachingUseraAndPasword extends Exception {
	 private static final long serialVersionUID = 1L;
	 
	 public MismachingUseraAndPasword () {
		 super();
	 }
	 
	 public MismachingUseraAndPasword (String dat) {
		 super(dat);
	 }

}
