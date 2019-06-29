package Tagging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.utilities.DAOUtilities;

public class TagDAOImpl implements TagDAO {

	Connection connection = null;	// Our connection to the database
	PreparedStatement stmt = null;	// We use prepared statements to help protect against SQL injection
	
	@Override
	public List<String> getTagsByBook(Book book) {

		List<String> allTags = new ArrayList<String>();
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM book_tags WHERE isbn_13 = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, book.getIsbn13());
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				allTags.add(rs.getString("book_tag"));		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
	return allTags;
	}
	
	
	@Override
	public List<Book> getBooksByTag(String tag) {

		List<Book> taggedBooks = new ArrayList<Book>();
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM book_tags WHERE book_tag = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, tag);
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Book myBook = getBookByISBN(rs.getString("isbn_13"));
				String isbn = myBook.getIsbn13();
				taggedBooks.add(getBookByISBN(isbn));		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
	return taggedBooks;
	}
	
	
	
	@Override
	public Book getBookByISBN(String isbn) {
		Book book = null;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM Books WHERE isbn_13 = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, isbn);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				book = new Book();
				book.setIsbn13(rs.getString("isbn_13"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublishDate(rs.getDate("publish_date").toLocalDate());
				book.setPrice(rs.getDouble("price"));
				book.setContent(rs.getBytes("content"));			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return book;
	}


	
	
	public boolean addBookTag(Book book, String tag) {	
		
	boolean success = false;
	try {
		connection = DAOUtilities.getConnection();
		String sql = "INSERT INTO book_tags values (?, ?)";
		System.out.println("Attempting to execute sql statement: "+sql);
		stmt = connection.prepareStatement(sql);
		stmt.setString(1, book.getIsbn13());
		stmt.setString(2,  tag);
		stmt.executeUpdate();
		success = true;
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		closeResources();
	}
	return success;
	}
	
	//public boolean removeBookTag(Book book, String tag) {} 
	
	
	
	// Closing all resources is important, to prevent memory leaks. 
	// Ideally, you really want to close them in the reverse-order you open them
	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}

}
