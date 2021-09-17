package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Worker extends User implements Serializable {
	private static final long serialVersionUID = 1L;

	public Worker(String username, String password, String email, String id) {
		super(username, password, email, id);
	}
	
	public Worker() {}

}
