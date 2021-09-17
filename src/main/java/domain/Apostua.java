package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Apostua implements Serializable {
	private static final long serialVersionUID = 1L;
@Id 
@GeneratedValue
@XmlID
@XmlJavaTypeAdapter(IntegerAdapter.class)
private Integer id;
private double kantitatea;//la cantidad apostada
private boolean balida;//Si es true significa que la apuesta es pagable
//@XmlIDREF
private RegisteredUser usuarioa;
private RegisteredUser copiado;
private int comision;
@XmlIDREF
private ArrayList<Pronostico> pronostikoa;

public Apostua (double kantitatea, RegisteredUser usuarioa, Pronostico pronostikoa) {
	this (kantitatea,usuarioa,new Vector<Pronostico> ());
	this.pronostikoa.add(pronostikoa);
}

public Apostua (double kantitatea, RegisteredUser usuarioa, Vector<Pronostico> pronostikoa) {
	this.copiado=null;
	this.balida=true;
	this.kantitatea=kantitatea;
	this.usuarioa=usuarioa;
	this.pronostikoa=new ArrayList<Pronostico>();
	this.pronostikoa.addAll(pronostikoa);
	usuarioa.apustuaEgin(this);
	for (Pronostico i:pronostikoa) {
		i.apostuaEgin(this);
	}
	
}
	
	public Apostua (double kantitatea, RegisteredUser usuarioa, Vector<Pronostico> pronostikoa,RegisteredUser copiado) {
		this (kantitatea,usuarioa,pronostikoa);
		this.copiado=copiado;
		this.comision=this.copiado.getComision();
}


public Apostua() {}


public RegisteredUser getUsuarioa() {
	return usuarioa;
}

public double getKantitatea() {
	return kantitatea;
}


public void nobalaida() {
	this.balida=false;
}

public boolean getBalida() {
	return this.balida;
}

public void SetBalida(boolean balida) {
	this.balida=balida;
}

public boolean pagar() {
	if (!balida) {
		return false;
	}
	for (Pronostico i:pronostikoa) {
		if (!i.getEstado()) {
			return false;
		}
	}
	return true;
}

public ArrayList<Pronostico> getPronostikoa() {
	return pronostikoa;
}

public RegisteredUser getCopiado() {
	return copiado;
}

public double getComision() {
	return (double)comision/100;
}
}
