package cn.timebusker.model.one2one;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "t_address")
public class Address implements Serializable {

	private static final long serialVersionUID = -8011043775142297278L;

	public Address(String street, String city) {
		super();
		this.street = street;
		this.city = city;
	}

	@Id
	@GeneratedValue(generator = "userForeignGenerator")
	@GenericGenerator(name = "userForeignGenerator", strategy = "foreign", parameters = @Parameter(name = "property", value = "user"))
	@Column(name = "ADDRESS_ID")
	private Integer addressId;

	private String street;

	private String city;

	@OneToOne
	@JoinColumn(name = "userId")
	private User user;

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
