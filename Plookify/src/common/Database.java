package common;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author tunazzinaIshrat
 */

import java.sql.*;
import java.util.ArrayList;

public class Database {
    
    private static final Database INSTANCE = new Database();

    private Connection connection = null;
    
    Database() {	
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:plookifyDB.sqlite");
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Database connection failed!", ex);
		}
	}
	
	public void test_database(){
		
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(10);
			
		}
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		finally {
			if (connection != null){
				try{
					connection.close();
				}
				catch(SQLException ex){
					System.err.println(ex.getMessage());
				}
			}
		}
	}
        
        public ArrayList<Track> getTrack(String query) {
            ArrayList<Track> tracks = new ArrayList<Track>();
            Statement statement;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(10);
                        ResultSet rs = statement.executeQuery(query);
                        while (rs.next()) {
                            tracks.add(new Track(rs.getInt("trackID"), rs.getString("trackName"), rs.getInt("trackArtist"), rs.getInt("trackGenre"), rs.getInt("trackLength"), rs.getString("trackPath")));
                        }
                        rs.close();
                        statement.close();
		}
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		finally {
			if (connection != null){
				try{
					connection.close();
				}
				catch(SQLException ex){
					System.err.println(ex.getMessage());
				}
			}
		}
                return tracks;
        }
        
	public static Database getInstance() {
		return INSTANCE;
	}
	
	public Connection getConnection(){
		return this.connection;
	}
        
}