package patroiak;

import domain.Event;

import java.sql.Date;
import java.util.Calendar;

import businessLogic.*;
import configuration.UtilDate;
import dataAccess.*;

public class Main {

	public static void main(String[] args) {
		boolean isLocal = true;
		BLFacadeFactory factory = new BLFacadeFactory();
		DataAccess da = new DataAccess(true);
		BLFacade facadeInterface = factory.BLFacadeLocal(da);

		Calendar today = Calendar.getInstance();
		int month = today.get(Calendar.MONTH);
		month += 1;
		int year = today.get(Calendar.YEAR);

		ExtendedIterator i = facadeInterface.getEvents(UtilDate.newDate(year, month, 17));
		Event ev;
		i.goLast();
		while (i.hasPrevious()) {
			ev = (Event) i.previous();
			System.out.println(ev.getDescription());
		}

		System.out.println(" ");
		// Nahiz eta suposatu hasierara ailegatu garela, eragiketa egiten dugu.
		i.goFirst();
		while (i.hasNext()) {
			ev = (Event) i.next();
			System.out.println(ev.getDescription());
		}

	}
}
