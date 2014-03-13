package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Performance;

public class BDD {
	private Connection conn;
	
	public BDD(){
		conn = connect();
	}

	public Connection connect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			
			String url = "jdbc:mysql://localhost:3307/athlétisme";
			String user = "root";
			String passwd = "esgi";
			
			Connection conn = DriverManager.getConnection(url, user, passwd);
			
			return conn;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	
	}
	
	public ArrayList<String> getParours(){
		ArrayList<String> parcours = new ArrayList<String>();
	
		
			try {

				String query = "SELECT * FROM parcours";
				Statement stat = conn.createStatement();
				ResultSet rs = stat.executeQuery(query);
				while(rs.next()){
					parcours.add(rs.getString(2));
				}
				stat.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return parcours;
	}


	public int addPerformance(String date, String temps, int parcours){
		try {
			String query = "INSERT INTO performance(id_parcour, date, temps) VALUE ("+parcours+",'"+date+"', '"+temps+"')";
			Statement stat = conn.createStatement();
			int nb = stat.executeUpdate(query);
			stat.close();
			return nb;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	
	public ArrayList<Performance> getPerformance(){
		ArrayList<Performance> performances = new ArrayList<Performance>();
		
		String query = "Select * from performance";
		try {
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(query);
			while(rs.next()){
				performances.add(new Performance(rs.getInt(2), rs.getString(4), rs.getString(3)));
			}
			stat.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return performances;
	}
}
