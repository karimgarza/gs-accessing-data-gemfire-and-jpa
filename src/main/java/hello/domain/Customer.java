package hello.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.Region;

@Entity
@Region("Customer")
public class Customer implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Id
	@org.springframework.data.annotation.Id
    private Long id;
	
    private String firstName;
    private String lastName;

    protected Customer() {}

    @PersistenceConstructor
    public Customer(long id, String firstName, String lastName) {
        this.setId(id);        
    	this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}

