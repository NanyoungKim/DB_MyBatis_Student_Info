

public class User {
	private Integer id;
	private String name;
	private String gender;
	private String email;
	private Classes cls = new Classes();

	public Integer getId() {  return id;  }

	public void setId(Integer id) { 	this.id = id;}

	public String getName() {	return name;  }
	
	public void setName(String name) {	this.name = name; }

	public String getGender() { 	return gender;   }

	public void setGender(String gender) { 	this.gender = gender;  }

	public String getEmail() {	return email;  }

	public void setEmail(String email) {  this.email = email;  }

	public Classes getCls() { 	return cls; }

	public void setCls(Classes cls) {	this.cls = cls; 	}
}
