package Tagging;

import java.util.List;
import examples.pubhub.model.Book;



/**
 * Interface for our Data Access Object to handle database queries related to Book tags.
 */


public interface TagDAO {

	
	/* functions desired:
	add tag to book given book identifier (name or isbn-13)
	remove tag to book given book identifier (name or isbn-13)
	return all books with a tag
	return all tags associated with a book
	*/
	public List<String> getTagsByBook(Book book);
	public List<Book> getBooksByTag(String tag);
	
	public Book getBookByISBN(String isbn);
	
	public boolean addBookTag(Book book, String tag);
	public boolean removeBookTag(Book book, String tag); 
		
}
