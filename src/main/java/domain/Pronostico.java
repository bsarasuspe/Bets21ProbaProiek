package domain;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Pronostico implements Serializable {
	private static final long serialVersionUID = 1L;
	
@Id 
@GeneratedValue
@XmlID
@XmlJavaTypeAdapter(IntegerAdapter.class)
private Integer id;
private String Erantzuna;
private double Cuota;
@XmlIDREF
private Question galdera;
//@XmlIDREF
private ArrayList<Apostua> apostuak;
private boolean estado;//Si es true se ha decidico ya la respuesta

public Pronostico (String Erantzuna, double Cuota,Question galdera) {
	this.Erantzuna=Erantzuna;
	this.Cuota=Cuota;
	this.galdera=galdera;
	this.apostuak=new ArrayList<Apostua>();
	this.estado=false;
}

public Pronostico() {}

@Override
public boolean equals (Object entrada) {
	if (entrada==null) {
		return false;
	}
	if (entrada instanceof Pronostico) {
		//return this.Erantzuna.equals(((Pronostico)entrada).Erantzuna);
		return this.id==((Pronostico)entrada).id;
	}
	return false;
}

@Override
public String toString () {
	return Erantzuna+" : "+Cuota;
}

/*public void apostuaEgin(double DiruKantitatea, RegisteredUser usuarioa) {
	Apostua tmp=new Apostua(DiruKantitatea, usuarioa, this);
	usuarioak.add(tmp);
	usuarioa.apustuaEgin(tmp);
}*/

public void apostuaEgin(Apostua apostua) {
	apostuak.add(apostua);
}

public ArrayList<Apostua> getApostuak(){
	return apostuak;
}

public double getCuota() {
	return Cuota;
}

public int getId() {
	return id;
}

public String getErantzuna() {
	return Erantzuna;
}

public Question getGaldera() {
	return galdera;
}

public boolean getEstado() {
	return estado;
}

public void setEstado(boolean estado) {
	this.estado = estado;
}

public void perder() {
	estado=true;
	for (Apostua i:this.apostuak) {
		i.nobalaida();
	}
}


}
