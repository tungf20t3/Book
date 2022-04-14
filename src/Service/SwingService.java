/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Books;
import dao.SwingDao;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ACER
 */
public class SwingService {
    private SwingDao swingDao;

    public SwingService() {
        swingDao = new SwingDao();
    }
    
    public List<Books> getAllBook() {
        return swingDao.getAllBook();
    }
    
    public Books getBookById(int id) throws SQLException {
        return swingDao.getBookById(id);
    }
    
    public List<Integer> getAllId() throws SQLException {
        return swingDao.getAllId();
    }
    
    public int addBook(Books book) throws SQLException {
        return swingDao.addBook(book);
    }
        
    public void deleteBook(int id) {
        swingDao.deleteBook(id);
    }
    
    public List<Books> getBookByTitle(String title) throws SQLException{
        return swingDao.getBookByTitle(title);
    }
    
    public int updateBook(Books book) throws SQLException {
        return swingDao.updateBook(book);
    }

}
