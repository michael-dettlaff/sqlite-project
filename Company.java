import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;



public class Company {
	
	
	private static JFrame mainWindow, modifyWindow, testWindow, directorsWindow, movieWindow, actorWindow, criticWindow, q3Window,
	performancesWindow, ratingsWindow;
	public JTextField insertField, insertField2, insertField3, updateField, updateField2, 
	deleteField, deleteField2, deleteField3, q1Field, q1Field2, q1ResultName, q1ResultMoney,
	q2Field, q2Field2, q3Field, test;
	private static Database db;
	public JTable q2Table, dirTable, movieTable, actorTable, criticTable, q3Table, performancesTable, ratingsTable;
	public JScrollPane sp, spDirector, spMovie, spActor, spCritic, spQ3, spPerformances, spRatings;

	public static void main(String[] args) {
		
		db = Database.getInstance();
		Company type = new Company();
		type.makeMainWindow();
				
	}
	
	private void makeMainWindow() {
		
		// connect the database when the first window is created
		try {
			db.connect();
		} catch (SQLException e) {
			System.out.println("Database connection failed");
			e.printStackTrace();
		}
		
		//makes the main window
		mainWindow = new JFrame();
		mainWindow.setTitle("Welcome!");
		mainWindow.setLocation(300, 25);
		mainWindow.setVisible(true);
		mainWindow.setLayout(null);
		mainWindow.setSize(1000, 700);
		mainWindow.setResizable(false);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.getContentPane().setBackground(Color.cyan);
		
		// welcome heading
		JLabel mainHeading = new JLabel("Welcome to Blockbuster 2.0!");
		mainHeading.setFont(new Font("Serif", Font.PLAIN, 30));
		mainHeading.setBounds(320, 40, 400, 25);
		mainWindow.add(mainHeading);
		
		JLabel subHeading = new JLabel("If you need to know a particular ID, find it in one of the first six tables!");
		subHeading.setFont(new Font("Serif", Font.PLAIN, 20));
		subHeading.setBounds(200, 80, 800, 25);
		mainWindow.add(subHeading);
		
		// modify button
		JButton modifyButton = new JButton("Want to modify the database?");
		modifyButton.setLocation(340, 565);
		modifyButton.setSize(300,37);
		mainWindow.add(modifyButton);
		
		// when modify button is pressed, changed the screen
		ActionListener listenerModify =  new buttonPressedModify();
		modifyButton.addActionListener(listenerModify);
		
		// query 1 label
		JLabel q1Heading = new JLabel("Query 1: Enter an actor's ID to find how much money their movies make on average");
		q1Heading.setFont(new Font("Serif", Font.PLAIN, 20));
		q1Heading.setBounds(150, 100, 800, 250);
		mainWindow.add(q1Heading);
		
		// q1 firstName label
		JLabel q1First = new JLabel("ID of Actor:");
		q1First.setFont(new Font("Serif", Font.PLAIN, 15));
		q1First.setBounds(250, 260, 350, 25);
		mainWindow.add(q1First);
		
		// q1 first field
		q1Field = new JTextField();
		q1Field.setBounds(350, 260, 100, 25);
		mainWindow.add(q1Field);

		// q1 button
		JButton q1Button = new JButton("Execute!");
		q1Button.setLocation(500, 260);
		q1Button.setSize(200,25);
		mainWindow.add(q1Button);
		
		// call methods for q1
		ActionListener listenerq1 =  new buttonPressedQ1();
		q1Button.addActionListener(listenerq1);
		
		JLabel q1Result = new JLabel("The average money made of a movie starring:");
		q1Result.setFont(new Font("Serif", Font.PLAIN, 18));
		q1Result.setBounds(150, 300, 600, 25);
		mainWindow.add(q1Result);
		
		q1ResultName = new JTextField();
		q1ResultName.setBounds(500, 300, 150, 25);
		mainWindow.add(q1ResultName);
		
		JLabel q1Result2 = new JLabel("is");
		q1Result2.setFont(new Font("Serif", Font.PLAIN, 18));
		q1Result2.setBounds(660, 300, 50, 25);
		mainWindow.add(q1Result2);
		
		q1ResultMoney = new JTextField();
		q1ResultMoney.setBounds(685, 300, 150, 25);
		mainWindow.add(q1ResultMoney);
		
		JLabel q2heading = new JLabel("Query 2: Enter the ID of a director to see which actors they have worked with at least twice!");
		q2heading.setFont(new Font("Serif", Font.PLAIN, 20));
		q2heading.setBounds(110, 230, 800, 250);
		mainWindow.add(q2heading);
		
		JLabel q2First = new JLabel("ID of Director:");
		q2First.setFont(new Font("Serif", Font.PLAIN, 15));
		q2First.setBounds(250, 380, 350, 25);
		mainWindow.add(q2First);
		
		// q1 first field
		q2Field = new JTextField();
		q2Field.setBounds(350, 380, 100, 25);
		mainWindow.add(q2Field);
		
		JButton q2Button = new JButton("Execute!");
		q2Button.setLocation(500, 380);
		q2Button.setSize(200,25);
		mainWindow.add(q2Button);
		
		ActionListener listenerq2 =  new buttonPressedQ2();
		q2Button.addActionListener(listenerq2);
		//mainWindow.add(sp);
		
		JLabel q3heading = new JLabel("Query 3: Enter the ID of a director to find out what the average rating is for one of their movies,");
		JLabel q3heading2 = new JLabel("and which movies are as good or better than their average!");
		q3heading.setFont(new Font("Serif", Font.PLAIN, 20));
		q3heading2.setFont(new Font("Serif", Font.PLAIN, 20));
		q3heading.setBounds(100, 310, 800, 250);
		q3heading2.setBounds(250, 340, 800, 250);
		mainWindow.add(q3heading);
		mainWindow.add(q3heading2);
		
		JLabel q3Label = new JLabel("ID of Director:");
		q3Label.setFont(new Font("Serif", Font.PLAIN, 15));
		q3Label.setBounds(250, 490, 350, 25);
		mainWindow.add(q3Label);
		
		q3Field = new JTextField();
		q3Field.setBounds(350, 490, 100, 25);
		mainWindow.add(q3Field);
		
		JButton q3Button = new JButton("Execute!");
		q3Button.setLocation(500, 490);
		q3Button.setSize(200,25);
		mainWindow.add(q3Button);
		
		ActionListener listenerq3 =  new buttonPressedQ3();
		q3Button.addActionListener(listenerq3);
		
		JButton testBtn = new JButton("View all Directors");
		testBtn.setLocation(200, 125);
		testBtn.setSize(180,25);
		mainWindow.add(testBtn);
		
		ActionListener listenerViewDir = new buttonPressedViewDirectors();
		testBtn.addActionListener(listenerViewDir);
		
		JButton movieBtn = new JButton("View all Movies");
		movieBtn.setLocation(400, 125);
		movieBtn.setSize(180,25);
		mainWindow.add(movieBtn);
		
		ActionListener listenerViewMovie = new buttonPressedViewMovies();
		movieBtn.addActionListener(listenerViewMovie);
		
		JButton actorBtn = new JButton("View all Actors");
		actorBtn.setLocation(600, 125);
		actorBtn.setSize(180,25);
		mainWindow.add(actorBtn);
		
		ActionListener listenerViewActors = new buttonPressedViewActors();
		actorBtn.addActionListener(listenerViewActors);
		
		JButton criticBtn = new JButton("View all Critics");
		criticBtn.setLocation(200, 165);
		criticBtn.setSize(180,25);
		mainWindow.add(criticBtn);
		
		ActionListener listenerViewCritics = new buttonPressedViewCritcs();
		criticBtn.addActionListener(listenerViewCritics);
		
		JButton performancesButton = new JButton("View all Performances");
		performancesButton.setLocation(400, 165);
		performancesButton.setSize(180,25);
		mainWindow.add(performancesButton);
		
		ActionListener listenerViewPerformances = new buttonPressedPerformances();
		performancesButton.addActionListener(listenerViewPerformances);
		
		JButton ratingsButton = new JButton("View all Ratings");
		ratingsButton.setLocation(600, 165);
		ratingsButton.setSize(180,25);
		mainWindow.add(ratingsButton);
		
		ActionListener listenerViewRatings = new buttonPressedViewRatings();
		ratingsButton.addActionListener(listenerViewRatings);
		
		mainWindow.repaint();
	}
	
		
	public class buttonPressedModify implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			Company test = new Company();
			test.makeModifyWindow();
		}
	}
	
	public class buttonPressedViewRatings implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			ratingsWindow = new JFrame();
			
			try {
				ResultSet results = db.getAllRatings();
				ResultSet copy = db.getAllRatings();
				int len = getLength(copy);
				String[][] data = new String[len][5];
				int count = 0;
				
				while (results.next()) {
					data[count][0] = results.getString(1)+" "+results.getString(2);
					data[count][1] = results.getString(3)+"";
					data[count][2] = results.getString(4);
					data[count][3] = results.getString(5)+"";
					data[count][4] = results.getString(6)+"";
					count++;
				}
				String[] colNames = { "Critic", "Critic ID", "Title", "Movie ID", "Rating" };
				
				ratingsTable = new JTable(data, colNames);
				ratingsTable.setBounds(200,300,200,300);
				ratingsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				spRatings = new JScrollPane(ratingsTable);
				ratingsWindow.add(spRatings);
				ratingsWindow.setVisible(true);
				ratingsWindow.setSize(1000, 800);
				ratingsWindow.repaint();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}
	
	public class buttonPressedPerformances implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			performancesWindow = new JFrame();
			
			try {
				ResultSet results = db.getAllPerformances();
				ResultSet copy = db.getAllPerformances();
				
				int len = getLength(copy);
				String[][] data = new String[len][3];
				int count = 0;
				
				while (results.next()) {
					data[count][0] = results.getString(1);
					data[count][1] = results.getString(2)+" "+results.getString(3);
					data[count][2] = results.getString(4);
					count++;
				}
				
				String[] colNames = { "Movie Title", "Actor", "Character Name" };
				
				performancesTable = new JTable(data, colNames);
				performancesTable.setBounds(200,300,200,300);
				performancesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				spPerformances = new JScrollPane(performancesTable);
				performancesWindow.add(spPerformances);
				performancesWindow.setVisible(true);
				performancesWindow.setSize(1000, 800);
				performancesWindow.repaint();

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println("something went wrong with performances");
			}
		}
	}
	
	public class buttonPressedViewCritcs implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			criticWindow = new JFrame();

			try {
				ResultSet results = db.getAllCritics();
				ResultSet copy = db.getAllCritics();
				int len = getLength(copy);
				
				String[][] data = new String[len][4];
				int count = 0;
				
				while (results.next()) {
					data[count][0]=results.getString(1)+"";
					data[count][1]=results.getString(2)+" "+results.getString(3);
					data[count][2]=results.getString(4);
					data[count][3]=results.getString(5)+"";
					count++;
				}
				
				String[] colNames = { "Critic ID", "Name", "Employer", "Start Year" };
				
				criticTable = new JTable(data, colNames);
				criticTable.setBounds(200,300,200,300);
				criticTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				spCritic = new JScrollPane(criticTable);
				criticWindow.add(spCritic);
				criticWindow.setVisible(true);
				criticWindow.setSize(1000, 800);
				criticWindow.repaint();
			} catch (SQLException e1) {
				System.out.println("something went wrong with displaying the critics");
			}		
		}
	}
	
	public class buttonPressedViewActors implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			actorWindow = new JFrame();
			
			try {
				ResultSet results = db.getAllActors();
				ResultSet copy = db.getAllActors();
				int len = getLength(copy);
				
				String[][] data = new String[len][4];
				int count = 0;
				
				while (results.next()) {
					data[count][0]=results.getString(1)+"";
					data[count][1]=results.getString(2)+" "+results.getString(3);
					data[count][2]=results.getString(4)+"";
					data[count][3]=results.getString(5)+"";
					count++;
				}
				
				String[] colNames = { "Actor ID", "Name", "Year of First Film", "Oscar Acting Nominations" };
				
				actorTable = new JTable(data, colNames);
				actorTable.setBounds(200,300,200,300);
				actorTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				spActor = new JScrollPane(actorTable);
				actorWindow.add(spActor);
				actorWindow.setVisible(true);
				actorWindow.setSize(1000, 800);
				actorWindow.repaint();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println("something went wrong with displaying actors");
			}
		}
	}
	
	public class buttonPressedViewMovies implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			movieWindow = new JFrame();
			try {
				ResultSet results = db.getAllMovies();
				ResultSet copy = db.getAllMovies();
				int len = getLength(copy);
				
				String[][] data = new String[len][7];
				int count = 0;
				
				while (results.next()) {
					data[count][0]=results.getString(1)+"";
					data[count][1]=results.getString(2);
					data[count][2]=results.getString(3);
					data[count][3]=results.getString(4);
					data[count][4]=results.getString(5)+"";
					data[count][5]=results.getString(6)+"";
					data[count][6]=results.getString(7)+" "+results.getString(8);
					count++;
				}
				
				String[] colNames = { "Movie ID", "Title", "Release Date", "Genre", "Runtime (Minutes)", "Box Office Dollars", "Director" };
				
				movieTable = new JTable(data, colNames);
				movieTable.setBounds(200,300,200,300);
				movieTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				spMovie = new JScrollPane(movieTable);
				movieWindow.add(spMovie);
				movieWindow.setVisible(true);
				movieWindow.setSize(1000, 800);
				movieWindow.repaint();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println("something went wrong with display movies");
			}
			
			
		}
	}
	
	
	
	public class buttonPressedViewDirectors implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			directorsWindow = new JFrame();
			try {
				ResultSet results = db.getAllDirectors();
				ResultSet copy = db.getAllDirectors();
				int len = getLength(copy);
				
				String[][] data = new String[len][3];
				int count = 0;
				
				while (results.next()) {
					data[count][0] = results.getString(1)+"";
					data[count][1] = results.getString(2)+" "+results.getString(3);
					data[count][2] = results.getInt(4)+"";
					count++;
				}
				
				String[] colNames = { "Director ID", "Name", "Year Of First Film" };
				
				dirTable = new JTable(data, colNames);
				dirTable.setBounds(200,300,200,300);
				dirTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				spDirector = new JScrollPane(dirTable);
				directorsWindow.add(spDirector);
				directorsWindow.setVisible(true);
				directorsWindow.setSize(1000, 800);
				directorsWindow.repaint();
				
			} catch (SQLException e1) {
				System.out.println("something went wrong");
			}
		}
	}
	
	
	public class buttonPressedQ1 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{

			String x = q1Field.getText();
			int id = Integer.parseInt(x);
			
			try {
				String name = db.actor(id);
				int avg = db.q1(id);

				q1ResultName.setText(name);
				q1ResultMoney.setText(avg + " dollars ");		
			} catch (SQLException e1) {
				System.out.println("Something wrong with q1");
			}
		}
	}
	
	public class buttonPressedQ2 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{			
			String id = q2Field.getText();
			int directorID = Integer.parseInt(id);
			
			int count=0;
			
			try {
				testWindow = new JFrame();
				ResultSet results = db.q2(directorID);
				ResultSet copy = db.q2(directorID);
				int len = getLength(copy);
				if (len==0) {
					
					JLabel no = new JLabel("This director has not collaborated with any actor more than once");
					no.setFont(new Font("Serif", Font.PLAIN, 20));
					no.setBounds(400, 330, 100, 25);
					testWindow.add(no);
					testWindow.setVisible(true);
					testWindow.setSize(650, 100);
					testWindow.repaint();
					
					return;
				}
				
				String[][] data = new String[len][3];
				
				while (results.next()) {
					data[count][0] = results.getString(1)+" "+results.getString(2);
					data[count][1] = results.getString(3)+" "+results.getString(4);
					data[count][2] = results.getInt(5)+"";
					count++;
				}
				String[] colNames = { "Director", "Actor", "Number of Collaborations" };
				
				q2Table = new JTable(data, colNames);
				q2Table.setBounds(200,300,200,300);
				q2Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				sp = new JScrollPane(q2Table);
				testWindow.add(sp);
				testWindow.setVisible(true);
				testWindow.setSize(1000, 800);
				testWindow.repaint();
				
			} catch (SQLException e1) {
				System.out.println("something went wrong with q2");
			}
		}
	}
	
	public class buttonPressedQ3 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			String x = q3Field.getText();
			int id = Integer.parseInt(x);
			
			q3Window = new JFrame();
			double avgRating = 0.0;
			
			try {
				avgRating = db.avgRatingDirector(id);
				ResultSet results = db.q3(id);
				ResultSet copy = db.q3(id);
				int len = getLength(copy);
				
				if (len==0) {	
					//System.out.println("this director has no movies");
					return;
				}
				
				String[][] data = new String[len][3];
				int count = 0;

				while (results.next()) {
					data[count][0] = results.getString(1)+"";
					data[count][1] = results.getString(2);
					data[count][2] = results.getString(5);
					count++;
				}
				
				String[] colNames = { "Movie ID", "Title", "Average Rating" };
				
				String directorName = db.director(id);
				
				JLabel displayRating = new JLabel("The average rating for a movie directed by " + directorName + " is " + avgRating);
				displayRating.setFont(new Font("Serif", Font.PLAIN, 20));
				displayRating.setBounds(250, 225, 600, 25);
				q3Window.setVisible(true);
				q3Window.setSize(650, 100);
				q3Window.add(displayRating);
				
				q3Table = new JTable(data, colNames);
				q3Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				spQ3 = new JScrollPane(q3Table);
				q3Window.add(spQ3);
				q3Window.setVisible(true);
				q3Window.setSize(1000, 500);
				q3Window.repaint();
				
			} catch (SQLException e1) {
				System.out.println("problem");
			}
		}
	}
	
	private int getLength(ResultSet results) throws SQLException {
		int num = 0;
		while (results.next()) {
			num++;
		}
		return num;
	}
	
	private void makeModifyWindow() {
		// makes the modify Window
		modifyWindow = new JFrame();
		modifyWindow.setTitle("Modify!");
		modifyWindow.setLocation(300, 175);
		modifyWindow.setVisible(true);
		modifyWindow.setLayout(null);
		modifyWindow.setSize(1000, 400);
		modifyWindow.setResizable(false);
		modifyWindow.getContentPane().setBackground(Color.orange);
		
		// insert heading
		JLabel insertHeading = new JLabel("Add a director to the director table!");
		insertHeading.setFont(new Font("Serif", Font.PLAIN, 20));
		insertHeading.setBounds(320, 40, 400, 25);
		modifyWindow.add(insertHeading);
		
		// firstName label
		JLabel insertFirst = new JLabel("First Name:");
		insertFirst.setFont(new Font("Serif", Font.PLAIN, 15));
		insertFirst.setBounds(45, 80, 350, 25);
		modifyWindow.add(insertFirst);
		
		// firstName text field
		insertField = new JTextField();
		insertField.setBounds(125, 80, 100, 25);
		modifyWindow.add(insertField);
		
		// lastName label
		JLabel insertLast = new JLabel("Last Name:");
		insertLast.setFont(new Font("Serif", Font.PLAIN, 15));
		insertLast.setBounds(250, 80, 350, 25);
		modifyWindow.add(insertLast);
		
		// lastName text field
		insertField2 = new JTextField();
		insertField2.setBounds(325, 80, 100, 25);
		modifyWindow.add(insertField2);
		
		// year label
		JLabel insertStartYear = new JLabel("Year of First Film:");
		insertStartYear.setFont(new Font("Serif", Font.PLAIN, 15));
		insertStartYear.setBounds(450, 80, 350, 25);
		modifyWindow.add(insertStartYear);
		
		// label text field
		insertField3 = new JTextField();
		insertField3.setBounds(575, 80, 100, 25);
		modifyWindow.add(insertField3);
		
		// Button to insert into database
		JButton insertButton = new JButton("Insert Director into Database!");
		insertButton.setLocation(725, 80);
		insertButton.setSize(200,25);
		modifyWindow.add(insertButton);
		
		// when insert button is pressed, call the method to insert
		ActionListener listenerModify =  new buttonPressedInsert();
		insertButton.addActionListener(listenerModify);
		
		// the update heading
		JLabel updateHeading = new JLabel("Oscar season! Update the oscar nominations count for the nominated actors!");
		updateHeading.setFont(new Font("Serif", Font.PLAIN, 20));
		updateHeading.setBounds(200, 140, 700, 25);
		modifyWindow.add(updateHeading);
		
		// firstName label
		JLabel updateFirst = new JLabel("ID of Actor:");
		updateFirst.setFont(new Font("Serif", Font.PLAIN, 15));
		updateFirst.setBounds(200, 180, 350, 25);
		modifyWindow.add(updateFirst);
				
		// firstName text field
		updateField = new JTextField();
		updateField.setBounds(300, 180, 100, 25);
		modifyWindow.add(updateField);
				
		// update button
		JButton updateButton = new JButton("Increment the oscar nomination count by one!");
		updateButton.setLocation(475, 180);
		updateButton.setSize(400,25);
		modifyWindow.add(updateButton);
		
		// when insert button is pressed, call the method to insert
		ActionListener listenerUpdate =  new buttonPressedUpdate();
		updateButton.addActionListener(listenerUpdate);
		
		// delete heading 
		JLabel deleteHeading = new JLabel("Did a critic get it wrong? Delete a specific rating!");
		deleteHeading.setFont(new Font("Serif", Font.PLAIN, 20));
		deleteHeading.setBounds(300, 240, 400, 25);
		modifyWindow.add(deleteHeading);
		
		// firstName label
		JLabel deleteFirst = new JLabel("ID of Critic:");
		deleteFirst.setFont(new Font("Serif", Font.PLAIN, 15));
		deleteFirst.setBounds(135, 280, 350, 25);
		modifyWindow.add(deleteFirst);
				
		// firstName text field
		deleteField = new JTextField();
		deleteField.setBounds(225, 280, 100, 25);
		modifyWindow.add(deleteField);
					
		// year label
		JLabel deleteMovie = new JLabel("ID of Movie:");
		deleteMovie.setFont(new Font("Serif", Font.PLAIN, 15));
		deleteMovie.setBounds(370, 280, 350, 25);
		modifyWindow.add(deleteMovie);
				
		// label text field
		deleteField3 = new JTextField();
		deleteField3.setBounds(470, 280, 100, 25);
		modifyWindow.add(deleteField3);
		
		JButton deleteButton = new JButton("Delete the critic's review!");
		deleteButton.setLocation(630, 280);
		deleteButton.setSize(200,25);
		modifyWindow.add(deleteButton);
		
		ActionListener listenerDelete =  new buttonPressedDelete();
		deleteButton.addActionListener(listenerDelete);

		modifyWindow.repaint();
	}
	
	public class buttonPressedInsert implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			String firstName = insertField.getText();
			String lastName = insertField2.getText();
			String firstYear = insertField3.getText();
			
			// insert the director into the database
			try {
				db.insertDirector(firstName, lastName, firstYear);
			} catch (SQLException e1) {
				System.out.println("insert did not work correctly");
			}
		}
	}
	
	public class buttonPressedUpdate implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			String x = updateField.getText();
			int id = Integer.parseInt(x);
			
			try {
				db.updateOscars(id);
			} catch (SQLException e1) {
				System.out.println("update oscars didn't work out correctly");
			}
		}
	}
	
	public class buttonPressedDelete implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			String critic = deleteField.getText();
			String movie = deleteField3.getText();
			int Crit = Integer.parseInt(critic);
			int Mov = Integer.parseInt(movie);

			try {
				db.deleteRating(Crit, Mov);
			} catch (SQLException e1) {
				System.out.println("Delete rating didn't work correctly");
			}
		}
	}

}
