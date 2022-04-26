package Task;

import java.io.File;
import java.io.PrintWriter;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.io.FileOutputStream;

public class Task3 {
	private static Connection conn = null;
	private static Statement s = null;
	private String sqlp = "";
	private static String[] tables = { "Pilot", "Airline", "SwitchBoard_Employment", "Workplace" };
	private static String[] commands = { "Delete tables", "Create tables", "Populate Tables", "Change Pilot Age",
			"Postint To TXT", "Delete Pilot", "User Data Entry", "Data Retrieval", "Data Appendix", "Decrease Payment",
			"Filter Number Of Employees", "Change Workplace", "Avarage Payment", "Dynamic QueryFor Two Fields",
			"	Women Pilots", "MetaDatas", "	New base", "Disconnect" };

	public Task3() {
	}

	public static int Exec(String sqlp) {
		int out = 0;
		try {
			s = conn.createStatement();
			out = s.executeUpdate(sqlp);
			System.out.println("Executed command:" + sqlp);
		} catch (Exception e) {
			out = -1;
			System.out.println("Problem" + e.getMessage());
		}
		return out;
	}

	public static void Connection(String user, String password) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@193.6.5.58:1521:XE";
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connected succesfully!");
		} catch (Exception ex) {
			System.out.println("Connection error: " + ex.getMessage());
		}
	}

	public static void Disconnection() {
		if (conn != null) {
			try {
				conn.close();
				System.out.println("Disconnected successfully!");
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	public void CreateTables() {
		String sqlp_Pilot = "create table Pilot(IDNumber number(3) primary key," + "Sex varchar(10), Age number(2))";
		String sqlp_Airline = "create table Airline(NumberOfHoursFlown number(3) primary key,"
				+ "Payment number(10), Base varchar(40), IDNumber number(3),foreign key (IDNumber)references Pilot(IDNumber))";
		String sqlp_Workplace = "create table Workplace(TaxNumber number(4) primary key,"
				+ "Country varchar(25), ControlNumber number(5), City varchar(25),	Street varchar(45),	HouseNumber number(4),	NumberOfEmployees number(6),OrganizationalStructure varchar(25))";
		String sqlp_Employment = "create table Employment(IDNumber number(3) not null,"
				+ "TaxNumber number(4) not null, foreign key (IDNumber) references Pilot(IDNumber),	foreign key (TaxNumber)	references Workplace(TaxNumber))";
		if (conn != null) {
			try {
				s = conn.createStatement();
				s.executeUpdate(sqlp_Pilot);
				System.out.println("Pilot table was created!\n");
				s.executeUpdate(sqlp_Airline);
				System.out.println("Airline table was created!\n");
				s.executeUpdate(sqlp_Workplace);
				System.out.println("Workplace table was created!\n");
				s.executeUpdate(sqlp_Employment);
				System.out.println("Employment table was created!\n");
				s.close();
			} catch (Exception ex) {
				System.err.println("Tables could not be created" + ex.getMessage());
			}
		}
	}

	public void ChangePilotAge() {
		if (conn != null) {
			try {
				String sqlp = "update Pilot set Age='45' where IDNumber= 3 ";
				s = conn.createStatement();
				s.executeUpdate(sqlp);
				System.out.println("Pilot table modified!\n");
				s.close();
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	public void PopulateTables() {
		int out = 0;
		if (conn != null) {
			String[] sqlp_Pilot = { "insert into Pilot values (1, 'Man', 18)",
					"insert into Pilot values (2, 'Man', 27)", "insert into Pilot values (3, 'Woman', 33)",
					"insert into Pilot values (4, 'Woman', 49)", "insert into Pilot values (5, 'Man', 57)", };
			String[] sqlp_Airline = { "insert into Airline values (150,5000,'Frankfurt',1)",
					"insert into Airline values (220,7000,'Berlin',2)",
					"insert into Airline values (180,6000,'Wien',3)",
					"insert into Airline values (130,4300,'Budapest',4)",
					"insert into Airline values (170,5600,'Madrid',5)", };
			String[] sqlp_Workplace = {
					"insert into Workplace values (10, 'Germany', 8000,'Frankfurt','Main Street',12,400,'Company')",
					"insert into Workplace values (9, 'Germany', 8500,'Berlin','Fruhstucke Street',23,500,'Company')",
					"insert into Workplace values (8, 'Austria',0878,'Wien','Franz Joseph Street',54,250,'Firm')",
					"insert into Workplace values (7, 'Hungary', 1001,'Budapest','Kossuth Street',1,360,'Firm')",
					"insert into Workplace values (6, 'Spain', 5555,'Madrid','Churros Street',363,420,'Company')", };
			String[] sqlp_Employment = { "insert into Employment values(1,10)", "insert into Employment values(2,9)",
					"insert into Employment values(3,8)", "insert into Employment values(4,7)",
					"insert into Employment values(5,6)", };
			for (int i = 0; i < sqlp_Pilot.length; i++) {
				try {
					out++;
					s = conn.createStatement();
					s.executeUpdate(sqlp_Pilot[i]);
					System.out.println(out + ". Data series: Data recorded in Pilot table!\n");
					s.close();
				} catch (Exception ex) {
					System.err.println(out + ". Data series: Error in Pilot table " + ex.getMessage());
				}
			}
			for (int i = 0; i < sqlp_Airline.length; i++) {
				try {
					out++;
					s = conn.createStatement();
					s.executeUpdate(sqlp_Airline[i]);
					System.out.println(out + ". Data series recorded in Airline table!\n");
					s.close();
				} catch (Exception ex) {
					System.err.println(out + ". Data series: Error in Airline table " + ex.getMessage());
				}
			}
			for (int i = 0; i < sqlp_Workplace.length; i++) {
				try {
					out++;
					s = conn.createStatement();
					s.executeUpdate(sqlp_Workplace[i]);
					System.out.println(out + ". Data series: Data recorded in Workplace table!\n");
					s.close();
				} catch (Exception ex) {
					System.err.println(out + ". Data series: Error in Workplace table " + ex.getMessage());
				}
			}
			for (int i = 0; i < sqlp_Employment.length; i++) {
				try {
					out++;
					s = conn.createStatement();
					s.executeUpdate(sqlp_Employment[i]);
					System.out.println(out + ". Data series recorded in Employment table!\n");
					s.close();
				} catch (Exception ex) {
					System.err.println(out + ". Data series: Error in Employment table " + ex.getMessage());
				}
			}
		}
	}

	public void DeletePilot() {
		System.out.println("Pilot ID 2 deleted");
		String sqlpe = "Delete from Airline where IDNumber = 2";
		String sqlpm = "Delete from Employment where IDNumber = 2";
		String sqlph = "Delete from Pilot where IDNumber = 2";
		if (conn != null) {
			try {
				s = conn.createStatement();
				s.executeUpdate(sqlph);
				s.executeUpdate(sqlpe);
				s.executeUpdate(sqlpm);
				s.close();
				System.out.println("Pilot with id 2 is deleted\n");
			} catch (Exception ex) {
				System.err.println("Pilot with id 2 could not be deleted " + ex.getMessage());
			}
		}
	}

	public void PostingToTxt() {
		ResultSet rs = null;
		int out = 0;
		if (conn != null) {
			String sqlp = "select * from Workplace";
			try {
				PrintWriter outputStream = new PrintWriter(new FileOutputStream(new File("out.txt"), true));
				outputStream.write("Workplace table:");
				outputStream.write("\n");
				outputStream.write(
						"Taxnumber \t Country \t Control number \t City \t Street \t Housenumber \t Number of employees \t OrganizationalStructure");
				outputStream.write("\n");
				outputStream.write(
						"--------------------------------------------------------------------------------------------------------------");
				outputStream.write("\n");
				s = conn.createStatement();
				s.executeQuery(sqlp);
				rs = s.getResultSet();
				while (rs.next()) {
					out++;
					int TaxNumber = rs.getInt("TaxNumber");
					String Country = rs.getString("Country");
					int ControlNumber = rs.getInt("ControlNumber");
					String City = rs.getString("City");
					String Street = rs.getString("Street");
					int HouseNumber = rs.getInt("HouseNumber");
					String NumberOfEmployees = rs.getString("NumberOfEmployees");
					String OrganizationalStructure = rs.getString("OrganizationalStructure");
					outputStream.write(TaxNumber + "\t" + Country + "\t" + ControlNumber + "\t" + City + "\t" + Street
							+ "\t" + HouseNumber + "\t" + NumberOfEmployees + "\t" + OrganizationalStructure + "\n");
				}
				rs.close();
				outputStream.close();
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	public void PilotDataRetrieval() {
		ResultSet rs = null;
		int out = 0;
		if (conn != null) {
			String sqlp = "select * from Pilot";
			System.out.println("IDNumber \t Sex \t Age \t");
			System.out.println("----------------------------------------------");
			try {
				s = conn.createStatement();
				s.executeQuery(sqlp);
				rs = s.getResultSet();
				while (rs.next()) {
					out++;
					int IDNumber = rs.getInt("IDNumber");
					String Sex = rs.getString("Sex");
					int Age = rs.getInt("Age");
					System.out.println(IDNumber + "\t" + Sex + "\t" + Age + "\t");
				}
				rs.close();
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	public void AirlineDataRetrieval() {
		ResultSet rs = null;
		int out = 0;
		if (conn != null) {
			String sqlp = "select * from Airline";
			System.out.println("NumberOfHoursFlown \t Payment \t Base \t IDNumber");
			System.out.println("--------------------------------------------------");
			try {
				s = conn.createStatement();
				s.executeQuery(sqlp);
				rs = s.getResultSet();
				while (rs.next()) {
					out++;
					int NumberOfHoursFlown = rs.getInt("NumberOfHoursFlown");
					int Payment = rs.getInt("Payment");
					String Base = rs.getString("Base");
					int IDNumber = rs.getInt("IDNumber");
					System.out.println(NumberOfHoursFlown + "\t" + Payment + "\t" + Base + "\t" + IDNumber);
				}
				rs.close();
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	public void WorkplaceDataRetrieval() {
		ResultSet rs = null;
		int out = 0;
		if (conn != null) {
			String sqlp = "select * from Workplace ";
			System.out.println(
					"TaxNumber \t Country \t ControlNumber \t City \t Street \t HouseNumber \t NumberOfEmployees \t OrganizationalStructure");
			System.out.println("----------------------------------------------");
			try {
				s = conn.createStatement();
				s.executeQuery(sqlp);
				rs = s.getResultSet();
				while (rs.next()) {
					out++;
					int TaxNumber = rs.getInt("TaxNumber");
					String Country = rs.getString("Country");
					int ControlNumber = rs.getInt("ControlNumber");
					String City = rs.getString("City");
					String Street = rs.getString("Street");
					int HouseNumber = rs.getInt("HouseNumber");
					int NumberOfEmployees = rs.getInt("NumberOfEmployees");
					String OrganizationalStructure = rs.getString("OrganizationalStructure");
					System.out.println(TaxNumber + "\t" + Country + "\t" + ControlNumber + "\t" + City + "\t" + Street
							+ "\t" + HouseNumber + "\t" + NumberOfEmployees + "\t" + OrganizationalStructure);
				}
				rs.close();
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	public void UserDataEntry() {
		Scanner sc = new Scanner(System.in);
		PreparedStatement ps = null;
		if (conn != null) {
			String sqlp = "insert into Pilot (IDNumber, Sex , Age)" + "values (?, ?, ?)";
			System.out.println("Please give the informations: ");
			System.out.println("The new pilot's id number: ");
			int IDNumber = sc.nextInt();
			System.out.println("Pilot's sex: ");
			String Sex = sc.next();
			System.out.println("Age: ");
			int Age = sc.nextInt();
			try {
				ps = conn.prepareStatement(sqlp);
				ps.setInt(1, IDNumber);
				ps.setString(2, Sex);
				ps.setInt(3, Age);
				ps.executeUpdate();
				ps.close();
				System.out.println("New pilot is recorded!\n");
			} catch (Exception ex) {
				System.err.println("Error: " + ex.getMessage());
			}
		}
	}

	public void DataAppendix() {
		ResultSet rs = null;
		int out = 0;
		if (conn != null) {
			String sqlp = "select * from Airline";
			try {
				PrintWriter outputStream = new PrintWriter(
						new FileOutputStream(new File("out.txt"), true /* append = true */));
				outputStream.write("\n");
				outputStream.write("Airline table:");
				outputStream.write("\n");
				outputStream.write("line \t NumberOfHoursFlown \t Payment \t Base \t IDNumber");
				outputStream.write("\n");
				outputStream.write("------------------------------------------------------");
				outputStream.write("\n");
				s = conn.createStatement();
				s.executeQuery(sqlp);
				rs = s.getResultSet();
				while (rs.next()) {
					out++;
					int NumberOfHoursFlown = rs.getInt("NumberOfHoursFlown");
					int Payment = rs.getInt("Payment");
					String Base = rs.getString("Base");
					int IDNumber = rs.getInt("IDNumber");
					outputStream.write(
							out + ". Line: " + NumberOfHoursFlown + "\t\t" + Payment + "\t" + Base + "\t" + IDNumber);
					outputStream.write("\n");
				}
				System.out.println("Done.");
				rs.close();
				outputStream.close();
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	public void DecreasePayment() {
		Scanner sc = new Scanner(System.in);
		PreparedStatement ps = null;
		if (conn != null) {
			String sqlp = "update Airline set payment= payment-?";
			System.out.println("How much do you want to reduce the payment? ");
			int paycheck = sc.nextInt();
			try {
				conn.setAutoCommit(false);
				try {
					ps = conn.prepareStatement(sqlp);
					ps.setInt(1, paycheck);
					ps.executeUpdate();
					conn.commit();
					System.out.println("Modified! \n");
				} catch (Exception e) {
					System.err.println("Error: " + e.getMessage());
					conn.rollback();
					System.out.println("Modification take back!\n");
				}
				conn.setAutoCommit(true);
			} catch (Exception ex) {
				System.err.println("Error: " + ex.getMessage());
			}
		}
	}

	public void FilterNumberOfEmployees() {
		Scanner sc = new Scanner(System.in);
		Statement s = null;
		ResultSet rs = null;
		System.out.println("Number of employees capacity: ");
		int numb = sc.nextInt();
		String sqlp = "select NumberOfEmployees from Workplace where ;NumberOfEmployees <= '" + numb + "'";
		if (conn != null) {
			try {
				s = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
				rs = s.executeQuery(sqlp);
				int countnumb = 0;
				while (rs.next()) {
					int OldNumbers = rs.getInt("NumberOfEmployees");
					countnumb++;
					rs.updateInt("NumberOfEmployees", (OldNumbers * 2));
					rs.updateRow();
					int Numbers = rs.getInt("NumberOfEmployees");
					System.out.println(countnumb + " courses-capacity less than " + numb);
					System.out.println(Numbers);
				}
				rs.close();
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	public void ChangeWorkplace() {
		if (conn != null) {
			try {
				String sqlp = "update Workplace set City='Budapest' where City='Wien' ";
				s = conn.createStatement();
				s.executeUpdate(sqlp);
				System.out.println("Modified! \n");
				s.close();
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	public void AvaragePayment() {
		Scanner sc = new Scanner(System.in);
		if (conn != null) {
			try {
				String sqlp = "select avg(payment),Sex from Airline inner join Pilot on Airline.IDNumber= Pilot.IDNumber where pilot.sex = 'Man' group by pilot.sex";
				s = conn.createStatement();
				ResultSet rs = s.executeQuery(sqlp);
				rs.next();
				String str = rs.getString(1);
				str = str.substring(0, 5);
				System.out.println("Average payment of a man Payment " + str + "\n");
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	public int DynamicQueryForTwoFields() {
		ResultSet rs = null;
		int out = 0;
		Scanner sc = new Scanner(System.in);
		if (conn != null) {
			sqlp = "select Age,Base,Payment,NumberOfHoursFlown from Pilot inner join Airline on Pilot.IDNumber = Airline.IDNumber where Base like ? and Payment <= ? ";
			System.out.println(" Pilot's ID \t Base \t Payment \t NumberOfHoursFlown ");
			System.out.println("-------------------------------------------------------");
			try {
				PreparedStatement ps = conn.prepareStatement(sqlp);
				System.out.println("Enter the Base:");
				String Basee = sc.next();
				ps.setString(1, Basee);
				System.out.println("Enter the Payment:");
				String PayMeent = sc.next();
				ps.setString(2, PayMeent);
				rs = ps.executeQuery();
				while (rs.next()) {
					out++;
					int Age = rs.getInt("Age");
					String Base = rs.getString("Base");
					int Payment = rs.getInt("Payment");
					int NumberOfHoursFlown = rs.getInt("NumberOfHoursFlown");
					System.out.println(out + ". adatsor, " + Age + "\t" + Base + "\t" + Payment + "\t"
							+ NumberOfHoursFlown + "\t");
				}
				rs.close();
				ps.close();
			} catch (Exception e) {
				out = -1;
				System.out.println("Problem: " + e.getMessage());
			}
		}
		return out;
	}

	public void NewBase() {
		if (conn != null) {
			Scanner sc = new Scanner(System.in);
			String sqlp = "update Airline set base=? where payment=7000";
			System.out.println("Budapest pay 7000. What should be the next base?");
			String NewBase = sc.nextLine();
			try {
				conn.setAutoCommit(false);
				try {
					PreparedStatement ps = conn.prepareStatement(sqlp);
					ps.setString(1, NewBase);
					ps.executeUpdate();
					conn.commit();
					System.out.println("Modified! \n");
				} catch (Exception e) {
					System.err.println("Error: " + e.getMessage());
					conn.rollback();
					System.out.println("Modification take back!\n");
				}
				conn.setAutoCommit(true);
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	public void WomenPilots() {
		ResultSet rs = null;
		if (conn != null) {
			String sqlp = "select IDNumber from Pilot where Sex='Woman'";
			System.out.println("Women pilots");
			System.out.println("------------");
			try {
				s = conn.createStatement();
				s.executeQuery(sqlp);
				rs = s.getResultSet();
				while (rs.next()) {
					int Women = rs.getInt("IDNumber");
					System.out.println("ID numbers of women: \t" + Women);
				}
				rs.close();
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	public void MetaDatas() {
		ResultSet rs = null;
		try {
			for (int y = 0; y < tables.length; y++) {
				s = conn.createStatement();
				String sqlp = "select * from " + tables[y];
				rs = s.executeQuery(sqlp);
				ResultSetMetaData rsmd = rs.getMetaData();
				if (rsmd != null) {
					int cols = rsmd.getColumnCount();
					System.out.println("Number of Columns: \t\t Table Name:");
					String tableName = rsmd.getTableName(1);
					System.out.println(".............................................");
					System.out.println(cols + "\t\t\t\t" + tables[y]);
					for (int i = 1; i <= cols; i++) {
						System.out.println("Class Name: " + rsmd.getColumnClassName(i));
						System.out.println("Column Name: " + rsmd.getColumnName(i));
						System.out.println("Column Type Name: " + rsmd.getColumnTypeName(i));
						System.out.println("----------------------------------------");
						System.out.println("----------------------------------------" + "\n");
					}
				} else {
					System.out.println("ResultSetMetadata not supported");
				}
			}
		} catch (SQLException ex1) {
			System.err.println(ex1);
		} finally {
			try {
				s.close();
				rs.close();
				conn.close();
			} catch (SQLException ex2) {
			}
		}
	}

	public void DeleteTables() {
		if (conn != null) {
			try {
				for (int i = 0; i < tables.length; i++) {
					String sqlp = "drop table " + tables[i];
					Exec(sqlp);
				}
			} catch (Exception e) {
				System.out.println("Error : " + e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		Task3 Task = new Task3();
		Task.Connection("H22_VDK7MU", "VDK7MU");
		Scanner sc = new Scanner(System.in);
		int executeCommand;
		do {
			System.out.println();
			for (int i = 0; i < commands.length; i++) {
				System.out.println(i + "." + commands[i]);
				System.out.println();
			}
			while (true) {
				System.out.println("-------------------------------------------------------");
				System.out.println("Enter the sequence number of the operation to be performed:");
				System.out.flush();
				if (!sc.hasNextInt()) {
					System.out.println("Please enter a number!");
					sc.next();
					continue;
				}
				break;
			}
			executeCommand = sc.nextInt();
			switch (executeCommand) {
			case 0:
				Task.DeleteTables();
				break;
			case 1:
				Task.CreateTables();
				break;
			case 2:
				Task.PopulateTables();
				break;
			case 3:
				Task.ChangePilotAge();
				break;
			case 4:
				Task.PostingToTxt();
				break;
			case 5:
				Task.DeletePilot();
				break;
			case 6:
				Task.UserDataEntry();
				break;
			case 7:
				System.out.println("1-Pilot table details: \n 2-Airline table details: \n 3-Workplace table details:");
				Scanner sc1 = new Scanner(System.in);
				int par = sc1.nextInt();
				if (par == 1) {
					Task.PilotDataRetrieval();
				} else if (par == 2) {
					Task.AirlineDataRetrieval();
				} else if (par == 3) {
					Task.WorkplaceDataRetrieval();
				} else {
					System.out.println("Please enter number 1 to 3!");
					par = sc1.nextInt();
				}
				break;
			case 8:
				Task.DataAppendix();
				break;
			case 9:
				Task.DecreasePayment();
				break;
			case 10:
				Task.FilterNumberOfEmployees();
				break;
			case 11:
				Task.ChangeWorkplace();
				break;
			case 12:
				Task.AvaragePayment();
				break;
			case 13:
				Task.DynamicQueryForTwoFields();
				break;
			case 14:
				Task.WomenPilots();
			case 15:
				Task.MetaDatas();
			case 16:
				Task.NewBase();
			case 17:
				Disconnection();
			default:
				break;
			}
		} while (executeCommand != 18);
	}
}
