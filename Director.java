
public class Director {
	
	private int ID;
	private String firstName;
	private String lastName;
	private int firstFilm;
	
	public Director(int iD, String firstName, String lastName, int firstFilm) {
		super();
		ID = iD;
		this.firstName = firstName;
		this.lastName = lastName;
		this.firstFilm = firstFilm;
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

	public int getFirstFilm() {
		return firstFilm;
	}

	public void setFirstFilm(int firstFilm) {
		this.firstFilm = firstFilm;
	}

	public int getID() {
		return ID;
	}
	
	public String toString() {
		return "Director [ID=" + ID + ", firstName=" + firstName + ", lastName=" + lastName + ", firstFilm=" + firstFilm
				+ "]";
	}

}
