import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.Length;

import com.dhev.constraints.LengthEL;
import com.dhev.constraints.SizeEL;

@Entity
@Table(name = "USER")
@SequenceGenerator(name = "USER_ID_SEQ", sequenceName = "USER_ID_SEQ", initialValue = 1)
public class User implements Serializable {

	private static final long serialVersionUID = -8166255867788169445L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_SEQ")
	private Long id;

	@Column
	@Length(min = 5)
	private String name;

	@Column
	@LengthEL(min = "4")
	private String surname;

	@Column
	@SizeEL(min = "2")
	private String[] test = new String[] { "xxx" };

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String[] getTest() {
		return test;
	}

	public void setTest(String[] test) {
		this.test = test;
	}

}
