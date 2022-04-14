/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Model.Books;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class SwingDao {
    public List<Books> getAllBook() {
        List<Books> books = new ArrayList<Books>();
        
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM books";
        
        try {
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);             
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                Books book = new Books();
                
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setPrice(rs.getInt("price"));
                
                books.add(book);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return books;  
    }
    
    public List<Integer> getAllId() throws SQLException {
        List<Integer> ids = new ArrayList<Integer>();
        
        Connection connection = JDBCConnection.getJDBCConnection();
        
        Statement stm = connection.createStatement();
        
        String sql = "SELECT id FROM books";
        
        ResultSet rs = stm.executeQuery(sql);
        
        while(rs.next()) {
            int id = rs.getInt("id");
            ids.add(id);
        }
        
        return ids;
    }
    
    public Books getBookById(int id) throws SQLException {
        Connection connection = JDBCConnection.getJDBCConnection();        
        
        String sql = "SELECT * FROM books WHERE id = ?";
        
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, id);
        
        ResultSet rs = pstm.executeQuery();
        
        rs.next();
        Books book = new Books(rs.getInt("id"), rs.getString("title"), rs.getDouble("price"));
        
        return book;
    }
    
    public List<Books> getBookByTitle(String title) throws SQLException {
        List<Books> books = new ArrayList<Books>();
        
        Connection connection = JDBCConnection.getJDBCConnection();
        Statement stm = connection.createStatement();
        String sql = "SELECT * FROM books WHERE title like '%" + title + "%'";
        
        ResultSet rs = stm.executeQuery(sql);
            
        while (rs.next()) {
                Books book = new Books();
                
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setPrice(rs.getDouble("price"));
                
               books.add(book);
            }
         return books;  
    }
    
    public int addBook(Books book) throws SQLException {
        Connection connection = JDBCConnection.getJDBCConnection();
        
        String sql = "INSERT INTO books(title, price) VALUES(?, ?)";
                
        
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, book.getTitle());
        preparedStatement.setDouble(2, book.getPrice());
        
        int rs = preparedStatement.executeUpdate();
        
        return rs;
    }
    
    public void deleteBook(int id) {
        Connection connection  = JDBCConnection.getJDBCConnection();
        
        String sql = "delete from books where id = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
       } catch (SQLException e) {
           e.printStackTrace();
       }
    }
    
    public int updateBook(Books book) throws SQLException {
        Connection connection = JDBCConnection.getJDBCConnection();
        
        String sql = "UPDATE book_inf SET title = ?, price = ?  WHERE id = ?";                               
        
        PreparedStatement preparedStatement = connection.prepareStatement(sql);        
        preparedStatement.setString(1, book.getTitle());
        preparedStatement.setDouble(2, book.getPrice());
        preparedStatement.setInt(3, book.getId());
        
        int rs = preparedStatement.executeUpdate();
        
        return rs;
    }
}
