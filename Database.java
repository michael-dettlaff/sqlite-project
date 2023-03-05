import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	
	private String url = "jdbc:sqlite:\\Users\\dettl\\Downloads\\Blockbuster 2.0.db";
	
	private Connection connection;
	
	// singleton pattern
	private static final Database INSTANCE = new Database();
	
	private Database() { }
	
	public static Database getInstance() {
		return INSTANCE;
	}
	
	public void connect() throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch(ClassNotFoundException e) {
			System.out.println("something isnt working");
		}
		
		connection = DriverManager.getConnection(url);
	}
	
	public void disconnect() throws SQLException {
		connection.close();
	}
	
	public ResultSet runQuery(String query) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet results = stmt.executeQuery();
		return results;	
	}
	
	// method to insert a director into a database
	public void insertDirector(String first, String last, String year) throws SQLException {
		String query = "INSERT INTO Director (DirectorID, FirstName, LastName, YearOfFirstFilm) VALUES(?, ?, ?, ?)";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, null);
		stmt.setString(2, first);
		stmt.setString(3, last);
		stmt.setString(4, year);
		stmt.executeUpdate();
	}
	
	// Update the database
	public void updateOscars(int id) throws SQLException {
		int count = oscarCount(id).getInt("ActingOscarNominations");
		count++;
		String query = "UPDATE Actor SET ActingOscarNominations = ? WHERE ActorID = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, count);
		stmt.setInt(2, id);
		stmt.executeUpdate();
	}
	
	// helper method to update the database
	public ResultSet oscarCount(int id) throws SQLException {
		String query = "SELECT ActingOscarNominations FROM Actor WHERE ActorID = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, id);
		return stmt.executeQuery();
	} 
	
	// Delete data from the database
	public void deleteRating(int cID, int mID) throws SQLException {
		String query = "DELETE FROM RATING WHERE CriticID = ? AND MovieID = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, cID);
		stmt.setInt(2, mID);
		stmt.executeUpdate();
	}
	
	public ResultSet getCritics() throws SQLException {
		String query = "SELECT CriticID, FirstName, LastName, Employer, StartYear FROM Critic";
		PreparedStatement stmt = connection.prepareStatement(query);
		return stmt.executeQuery();
	}
	
	public ResultSet searchCritic(String first, String last) throws SQLException {
		String query = "SELECT CriticID FROM Critic WHERE FirstName = ? AND LastName = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, first);
		stmt.setString(2,  last);
		return stmt.executeQuery();
	}
	
	public ResultSet searchMovie(String movie) throws SQLException {
		String query = "SELECT MovieID FROM Movie WHERE Title LIKE ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, movie);
		return stmt.executeQuery();
	}
	
	public int movie(int id) throws SQLException {
		String query = "SELECT MovieID FROM Movie WHERE MovieID = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet results = stmt.executeQuery();
		return results.getInt("MovieID");
	}
	
	public int critic(int id) throws SQLException {
		String query = "SELECT CriticID FROM Critic WHERE CriticID = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet results = stmt.executeQuery();
		return results.getInt("MovieID");
	}
	
	public String director(int id) throws SQLException {
		String query = "SELECT FirstName, LastName FROM Director WHERE DirectorID = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet results = stmt.executeQuery();
		return results.getString("FirstName") + " " + results.getString("LastName");	
	}
	
	public String actor(int id) throws SQLException {
		String query = "SELECT FirstName, LastName FROM Actor WHERE ActorID = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet results = stmt.executeQuery();
		return results.getString("FirstName") + " " + results.getString("LastName");
	}
	
	// Query 1 code
	public int q1(int id) throws SQLException {
		String query = "SELECT Actor.FirstName, Actor.LastName, round(avg(BoxOfficeDollars), 2) AS profits\r\n"
				+ "    FROM Actor NATURAL JOIN Performances NATURAL JOIN Movie\r\n"
				+ "    WHERE Actor.ActorID = ? ";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet results = stmt.executeQuery();
		return results.getInt("profits");
	}
	
	// Query 2 code
	public ResultSet q2(int id) throws SQLException {
		String query = "SELECT Director.FirstName, Director.LastName, Actor.FirstName, Actor.LastName, count(*) AS numberOfPerformances\r\n"
				+ "    FROM Director NATURAL JOIN Movie NATURAL JOIN Performances JOIN Actor\r\n"
				+ "        ON Performances.ActorID = Actor.ActorID\r\n"
				+ "    WHERE Director.DirectorID = ?\r\n"
				+ "    GROUP BY Director.DirectorID, Actor.ActorID, Director.FirstName, Director.LastName, Actor.FirstName, Actor.LastName\r\n"
				+ "\r\n"
				+ "    HAVING count(*) >= 2\r\n"
				+ "    ORDER BY count(*) DESC";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet q2Results = stmt.executeQuery();
		return q2Results;
	}
	
	// Query 3 code
	public ResultSet q3(int id) throws SQLException {
		String query = "SELECT Movie.MovieID, Movie.Title, Director.FirstName, Director.LastName, round(avg(Rating.Rating),1) AS avgRating\r\n"
				+ "    FROM Director Natural JOIN Movie Natural JOIN Rating NATURAL JOIN Rating\r\n"
				+ "    WHERE Director.DirectorID = ? \r\n"
				+ "    GROUP BY Movie.MovieID, Movie.Title, Director.FirstName, Director.LastName, Director.DirectorID\r\n"
				+ "    HAVING avgRating >= (  SELECT round(avg(avgMovieRating), 1) AS AvgRatingDirector\r\n"
				+ "                            FROM (  SELECT Director.DirectorID AS ID, Movie.MovieID, Movie.Title, round(avg(Rating.Rating),2) AS avgMovieRating\r\n"
				+ "                                        FROM Director Natural JOIN Movie Natural JOIN Rating NATURAL JOIN Rating\r\n"
				+ "                                        GROUP BY Movie.MovieID)\r\n"
				+ "                            WHERE ID = ?   \r\n"
				+ "                            GROUP BY ID  )\r\n"
				+ "    ORDER BY avgRating Desc  ";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, id);
		stmt.setInt(2, id);
		ResultSet q3Results = stmt.executeQuery();
		return q3Results;
	}
	
	public double avgRatingDirector(int id) throws SQLException {
		
		String query = "SELECT ID AS DirID, round(avg(avgRating), 1) AS AvgRatingDirector\r\n"
				+ "    FROM (    \r\n"
				+ "            SELECT Director.DirectorID AS ID, Movie.MovieID, Movie.Title, round(avg(Rating.Rating),2) AS avgRating\r\n"
				+ "                FROM Director Natural JOIN Movie Natural JOIN Rating NATURAL JOIN Rating\r\n"
				+ "            GROUP BY Movie.MovieID\r\n"
				+ "            ORDER BY avgRating Desc \r\n"
				+ "    )\r\n"
				+ "    WHERE ID = ?   \r\n"
				+ "    GROUP BY ID";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet q3Results = stmt.executeQuery();
		return q3Results.getDouble("AvgRatingDirector");
	}
	
	public ResultSet getDirector(String first, String last) throws SQLException {
		String query = "SELECT DirectorID, FirstName, LastName, YearOfFirstFilm FROM Director WHERE FirstName = ? AND LastName = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, first);
		stmt.setString(2, last);
		ResultSet results = stmt.executeQuery();
		return results;
	}
	
	
	// return all the directors in the database
	public ResultSet getAllDirectors() throws SQLException {
		String query = "SELECT DirectorID, FirstName, LastName, YearOfFirstFilm FROM Director";
		PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet results = stmt.executeQuery();
		return results;
	}
	
	// return all the movies in the database
	public ResultSet getAllMovies() throws SQLException {
		String query = "SELECT MovieID, Title, ReleaseDate, Genre, RuntimeMinutes, BoxOfficeDollars, Director.FirstName AS DirectorsFirstName, Director.LastName AS DirectorsLastName FROM Movie NATURAL JOIN Director";
		PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet results = stmt.executeQuery();
		return results;
	}
	
	// return all the actors in the database
	public ResultSet getAllActors() throws SQLException {
		String query = "SELECT ActorID, FirstName, LastName, YearOfFirstFilm, ActingOscarNominations FROM Actor";
		PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet results = stmt.executeQuery();
		return results;
	}
	
	// return all the critics in the database
	public ResultSet getAllCritics() throws SQLException {
		String query = "SELECT CriticID, FirstName, LastName, Employer, StartYear FROM Critic";
		PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet results = stmt.executeQuery();
		return results;
	}
	
	// return all the performances in the database
	public ResultSet getAllPerformances() throws SQLException {
		String query = "SELECT Movie.Title, Actor.FirstName, Actor.LastName, Performances.CharacterName \r\n"
				+ "    FROM Actor NATURAL JOIN Performances NATURAL JOIN Movie\r\n"
				+ "    ORDER BY Title";
		PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet results = stmt.executeQuery();
		return results;
	}
	
	// return all the ratings in the database
	public ResultSet getAllRatings() throws SQLException {
		String query = "SELECT Critic.FirstName, Critic.LastName, CriticID, Movie.Title, MovieID, Rating.Rating\r\n"
				+ "    FROM Movie NATURAL JOIN Rating NATURAL JOIN Critic";
		PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet results = stmt.executeQuery();
		return results;
	}
	
}
