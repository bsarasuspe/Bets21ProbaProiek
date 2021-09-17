package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;

import exceptions.JarraitzenZenuenException;
import exceptions.MutualFollowingException;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class RegisteredUser extends User implements Serializable {
	private static final long serialVersionUID = 1L;

	private long bankInformation;
	private Date birthDate;
	private double balance;
	@OneToMany(fetch=FetchType.EAGER)
	private Vector<RegisteredUser> jarraitzenditut;
	@OneToMany(fetch=FetchType.EAGER)
	private Vector<Double> jarraitzenditutPortzentagea;
	@OneToMany(fetch=FetchType.EAGER)
	private Vector<RegisteredUser> jarraitzendidate;
	private int comision;
	private boolean jarraitzeunutzi;
	@XmlIDREF
	private ArrayList<Apostua> ApostuLista;

	
	
	public RegisteredUser(String username, String password, String email, String id, long bankInformation, Date birthDate) {
		super(username, password, email, id);
		this.bankInformation = bankInformation;
		this.birthDate = birthDate;
		this.ApostuLista=new ArrayList<Apostua>();
		this.balance=0;
		jarraitzenditut=new Vector<RegisteredUser>();
		jarraitzenditutPortzentagea=new Vector<Double>();
		jarraitzendidate=new Vector<RegisteredUser>();
		comision=0;
		jarraitzeunutzi=false;
	}
	
	public RegisteredUser() {}
	
	public double getBalance () {
		return Math.round(balance * 100.0) / 100.0;
	}
	
	public void setBalance(double balance) {
		this.balance=balance;
	}
	
	public void apustuaEgin(Apostua apustua) {
		ApostuLista.add(apustua);
	}
	
	public void seguir (RegisteredUser PersonaAseguir,double porcentage) throws MutualFollowingException,JarraitzenZenuenException{
		if (PersonaAseguir.jarraitzenditut.contains(this)) {
			throw new MutualFollowingException();
		}
		if (this.jarraitzenditut.contains(PersonaAseguir)){
			throw new JarraitzenZenuenException();
		}
		PersonaAseguir.jarraitzendidate.add(this);
		this.jarraitzenditut.add(PersonaAseguir);
		this.jarraitzenditutPortzentagea.add(porcentage/100);//He hecho aqui el /100 para calcualr el porcentage
	}

	public void unfollow (RegisteredUser PersonaANoSeguir){
		if (PersonaANoSeguir.jarraitzendidate.remove(this)) {
			int tmp=this.jarraitzenditut.indexOf(PersonaANoSeguir);
			this.jarraitzenditut.remove(tmp);
			this.jarraitzenditutPortzentagea.remove(tmp);
		}
	}

	public Vector<RegisteredUser> getJarraitzenditut() {
		return jarraitzenditut;
	}

	public Vector<Double> getJarraitzenditutPortzentagea() {
		return jarraitzenditutPortzentagea;
	}

	public Vector<RegisteredUser> getJarraitzendidate() {
		return jarraitzendidate;
	}

	public int getComision() {
		return comision;
	}

	public boolean isJarraitzeunutzi() {
		return jarraitzeunutzi;
	}
	
	public double getPortzentaia (RegisteredUser perosnaquesigo){
		return jarraitzenditutPortzentagea.get(this.jarraitzenditut.indexOf(perosnaquesigo));///////
	}
	
	public ArrayList<RegisteredUser> apgarseguir () {
		jarraitzeunutzi=false;
		comision=0;
		ArrayList<RegisteredUser>buelta=new ArrayList<RegisteredUser>();
		for (RegisteredUser i:jarraitzendidate) {
			i.unfollow(this);
			buelta.add(i);
		}
		return buelta;
	}
	
	public void encenderseguir (int comision) {
		this.comision=comision;
		jarraitzeunutzi=true;
	}
	
	public Vector<String> infoseguidos(){
		Vector<String> buelta=new Vector<String>();
		for (RegisteredUser i:this.jarraitzenditut) {
			buelta.add(i.getUsername()+";"+i.comision+";"+this.getPortzentaia(i));
		}
		return buelta;
	}

}
