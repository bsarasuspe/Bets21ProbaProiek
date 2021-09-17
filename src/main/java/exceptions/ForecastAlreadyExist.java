package exceptions;

public class ForecastAlreadyExist extends Exception{
	 private static final long serialVersionUID = 1L;
	 
	 public ForecastAlreadyExist()
	  {
	    super();
	  }

	  public ForecastAlreadyExist(String s)
	  {
	    super(s);
	  }
}
