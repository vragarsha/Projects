package movies.rest.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Table
@Entity
@NamedQueries({ 
	@NamedQuery(name = "User.findAll", query = "SELECT e FROM User e ORDER BY e.lastName ASC"),
	@NamedQuery(name = "User.findByEmail", query = "SELECT e FROM User e WHERE e.email=:pEmail") 
})
public class User {
	
	@Id
	@GenericGenerator(name = "customUUID", strategy = "uuid2")
	@GeneratedValue(generator = "customUUID")
	private String uid;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String login;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Review> reviews;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
		

}
