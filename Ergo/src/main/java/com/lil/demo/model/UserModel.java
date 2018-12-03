package com.lil.demo.model;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class UserModel {

	@Id
	@GeneratedValue
	private int id;
	
	@Size(min=2, max=35, message="First name {value.error}") 
	private String firstName;
	 
	@Size(min=2, max=35 , message="Last name {value.error}")
	private String lastName;
	
	@Size(min=3, max=35, message="Username {value.error}")
	@Pattern(message="{username.error}" , regexp="^(?=.{4,12}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$")
	private String username;
	
	@Email(message="Invalid Email format") @Size(max=45, message="Email max length is {max}")
	private String email;
	
	@NotBlank(message="{gender.notblank}")
	@Pattern(regexp="^male$|^female$", message="Invalid gender!")
	private String gender;
	 
	@Size(min=6,max=60, message="Password {value.error}") 
	private String password;
	
	private boolean enabled;
	
	@Size(min=6,max=60, message="Confirmation password {value.error}") 
	@Transient
	private String confirmPassword;
	
	

	public UserModel() {
		super(); 
		this.enabled=false;
	}
	
	public UserModel(int id, String firstName, String lastName, String username, String email, String gender,
			String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.gender = gender;
		this.password = password;
		this.enabled=false;
	}
	
	
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", email=" + email + ", gender=" + gender + ", password=" + password + ", enabled=" + enabled
				+ ", confirmPassword=" + confirmPassword + "]";
	}
 
	
}
