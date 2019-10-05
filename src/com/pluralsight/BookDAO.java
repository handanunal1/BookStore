package com.pluralsight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class BookDAO {

	
	private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
    
    {
    	jdbcURL = "jdbc:mysql://localhost:3306/book_store";
    	jdbcUsername = "root";
    	jdbcPassword = "Sfr1234";
    }
     
    public Connection connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
        return jdbcConnection;
    }
     
    public void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
        	jdbcConnection.close();
        }
    }
    
    
    
    public ArrayList<Book> listAllBooks() throws SQLException{
    	connect();
    	ArrayList<Book> listBook = new ArrayList<>();
    	Statement statement = jdbcConnection.createStatement();
    	ResultSet resultSet = statement.executeQuery("SELECT * FROM book");
    	while(resultSet.next()) {
    		String title = resultSet.getString("title");
    		String author = resultSet.getString("author");
    		float price = resultSet.getFloat("price");
    		Book book = new Book(title,author,price);
    		listBook.add(book);
    	}
    	
        resultSet.close();
        statement.close();
    	return listBook;
    }
}
