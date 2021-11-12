package patroiak;

import java.util.ArrayList;
import java.util.Date;

import javax.imageio.metadata.IIOMetadataFormatImpl;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dataAccess.DataAccess;
import domain.Apostua;
import domain.Event;
import domain.Question;
import domain.RegisteredUser;

public class UserAdapter {


	private String[] columnNames = { "Event", "Question", "Event Date", "Bet(€)"};

	private DefaultTableModel modelDatuak = new DefaultTableModel(columnNames, 0); 
	private JTable tableDatuak = new JTable(modelDatuak); 

	public UserAdapter(RegisteredUser rUser) {
		ArrayList<String> datuak = new ArrayList<String>();
		DataAccess da = new DataAccess();
		datuak = da.datuakLortu(rUser);

		int i=0;
		while(i<datuak.size()-3) {
			Object[] row = {datuak.get(i+2),datuak.get(i+1),datuak.get(i+3), datuak.get(i)};
            modelDatuak.addRow(row);
            i= i+4;
		}

	}

	public DefaultTableModel getModelDatuak() {
		return modelDatuak;
	}


	public JTable getTableDatuak() {
		return tableDatuak;
	}

}
