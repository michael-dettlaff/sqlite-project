
public class Critic {
	
	private int ID;
	private String firstName;
	private String lastName;
	private String Employer;
	private int startYear;
	
	public Critic(int iD, String firstName, String lastName, String employer, int startYear) {
		super();
		ID = iD;
		this.firstName = firstName;
		this.lastName = lastName;
		Employer = employer;
		this.startYear = startYear;
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

	public String getEmployer() {
		return Employer;
	}

	public void setEmployer(String employer) {
		Employer = employer;
	}

	public int getID() {
		return ID;
	}

	public int getStartYear() {
		return startYear;
	}

	@Override
	public String toString() {
		return "Critic [ID=" + ID + ", firstName=" + firstName + ", lastName=" + lastName + ", Employer=" + Employer
				+ ", startYear=" + startYear + "]";
	}

	
	
}
