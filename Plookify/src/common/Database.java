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
    
    public Database() {	
		
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

    
    public void createUser(String username, String firstName, String lastName, String password, String email, String address, int phoneNumber, String date) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.setQueryTimeout(10);
            String sql = "";
            if (date.equals(""))
                sql = "INSERT INTO account (username, fullName, password, email, address, phoneNumber) VALUES ('" + username + "', '" + firstName + " " + lastName + "', '" + password + "', '" + email + "', '" + address + "', " + phoneNumber + ")";
            else
                sql = "INSERT INTO account (username, fullName, password, email, address, phoneNumber, paymentDate) VALUES ('" + username + "', '" + firstName + " " + lastName + "', '" + password + "', '" + email + "', '" + address + "', " + phoneNumber + ", '" + date + "')";
            statement.executeUpdate(sql);
            statement.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
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
    
    public boolean checkUser(String username, String password) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.setQueryTimeout(10);
            ResultSet rs = statement.executeQuery("SELECT password FROM account WHERE username = '" + username + "'");
            if (!rs.isBeforeFirst()) {
                rs.close();
                statement.close();
                return false;
            }
            String pswd = "";
            while (rs.next())
                pswd = rs.getString("password");
            rs.close();
            statement.close();
            if (password.equals(pswd))
                return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
        
    public static Database getInstance() {
            return INSTANCE;
    }

    public Connection getConnection(){
            return this.connection;
    }
        
}
