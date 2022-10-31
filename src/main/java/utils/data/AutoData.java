package utils.data;

public class AutoData {

	private String zip;
	private String dob;
	private String firstName;
	private String lastName;
	
	public AutoData(String zip, String dob, String firstName, String lastName) {
		this.zip = zip;
		this.dob = dob;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getZip() {
		return zip;
	}
	
	public String getDob() {
		return dob;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
