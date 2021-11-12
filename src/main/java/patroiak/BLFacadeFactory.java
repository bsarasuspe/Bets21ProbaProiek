package patroiak;

import dataAccess.DataAccess;
import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import javax.xml.ws.Service;

public class BLFacadeFactory {

	
	
	public BLFacade BLFacadeLocal(DataAccess da) {
		return new BLFacadeImplementation(da);
	}
	
	public BLFacade BLFacadeInternet(Service service) {
		
		return service.getPort(BLFacade.class);
	}
}
