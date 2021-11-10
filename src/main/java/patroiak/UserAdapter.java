package patroiak;

import java.util.ArrayList;
import java.util.Date;

import javax.imageio.metadata.IIOMetadataFormatImpl;

import dataAccess.DataAccess;
import domain.Apostua;
import domain.Event;
import domain.Question;
import domain.RegisteredUser;

public class UserAdapter {

	ArrayList<Event> event = new ArrayList<Event>();
	ArrayList<Question> question = new ArrayList<Question>();;
	ArrayList<Date> data = new ArrayList<Date>();;
	ArrayList<Double> betAmount = new ArrayList<Double>();;

	
	public UserAdapter(RegisteredUser rUser) {
		ArrayList<Apostua> apostuak=new ArrayList<Apostua>();
		DataAccess da = new DataAccess();
		apostuak=da.apostuaLortu(rUser);
		
		for(Apostua ap: apostuak) {
			//event.add(ap.getPronostikoa().get);
		}
	}
}
