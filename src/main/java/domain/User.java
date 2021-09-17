package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@XmlSeeAlso ({RegisteredUser.class, Admin.class,Worker.class})
public abstract class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlID
	@Id
	private String username;
	private String password;
	private String email;
	private String id;//TODO esta mierda no hace falta
	
	User(){
		/*this.username = "";
		this.password = "";
		this.email = "";
		this.id = "";*/
		super();
	}
	
	User(String username, String password, String email, String id){
		this.username = username;
		this.password = password;
		this.email = email;
		this.id = id;
	}
	
	public String getPassword() {
		return(this.password);
	}
	
	public String getUsername() {
		return username;
	}

}
