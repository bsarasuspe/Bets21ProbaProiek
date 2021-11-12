package patroiak;

import java.util.ArrayList;
import java.util.Date;

import javax.imageio.metadata.IIOMetadataFormatImpl;
import javax.swing.JTable;

import dataAccess.DataAccess;
import domain.Apostua;
import domain.Event;
import domain.Question;
import domain.RegisteredUser;

public class UserAdapter {


	private String[] columnNames = { "Event", "Question", "Event Date", "Bet(€)"};
	private Object[][] data = {};

	private JTable tableDatuak = new JTable(data, columnNames);

	public UserAdapter(RegisteredUser rUser) {
		ArrayList<String> datuak = new ArrayList<String>();
		DataAccess da = new DataAccess();
		datuak = da.datuakLortu(rUser);

		int i=0;
		
		while(i<datuak.size()) {
			tableDatuak.add(datuak.get(i), tableDatuak);
		}

	}

	/*
	 * public class DatuakAdaptatuta {
	 * 
	 * private String eventua; private String galdera; private String data; private
	 * String kopurua;
	 * 
	 * public DatuakAdaptatuta(String eventua, String galdera, String data, String
	 * kopurua) {
	 * 
	 * }
	 * 
	 * public String getGaldera() { return galdera; }
	 * 
	 * public void setGaldera(String galdera) { this.galdera = galdera; }
	 * 
	 * public String getData() { return data; }
	 * 
	 * public void setData(String data) { this.data = data; }
	 * 
	 * public String getKopurua() { return kopurua; }
	 * 
	 * public void setKopurua(String kopurua) { this.kopurua = kopurua; }
	 * 
	 * public String getEventua() { return eventua; } public void setEventua(String
	 * eventua) { this.eventua = eventua; } }
	 */
}
