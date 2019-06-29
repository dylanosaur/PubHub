package Tagging;

import examples.pubhub.dao.*;

import java.util.ArrayList;
import java.util.List;
import examples.pubhub.model.Book;

/* 
 * This class will test the TagDAO/TagDAOImpl interface class pair methods
 */
public class TagSimulator {

	public TagSimulator() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		//addMoreBooks();
		
		TagDAO myInterface = new TagDAOImpl();
		
		// test getTagsByBook
		
		Book myBook = myInterface.getBookByISBN("1112223334440");
		System.out.println("Testing getBookByISBN for 20k leagues under the sea");
		System.out.println(myBook.getTitle()+" "+myBook.getAuthor() + "\n");
		
		
		System.out.println("testing getTagsByBook for 20k leagues under the sea");
		List<String> myTags = myInterface.getTagsByBook(myBook);
		for (int i=0; i<myTags.size(); i++) { 
			System.out.println(myTags.get(i));
		}


		// test getBooksByTag
		System.out.println("testing getBooksByTag for sci-fi");
		List<Book> fanficBooks = myInterface.getBooksByTag("sci-fi");
		for (int i=0; i<myTags.size(); i++) { 
			System.out.println(fanficBooks.get(i).getTitle());
		}
		

		
	}
	
	public static void addMoreBooks() {
		List<Book> newBooks = new ArrayList<Book>();
		newBooks.add( new Book("1112223334440", "20k leagues under the sea", "Jules Verne", null));
		newBooks.add( new Book("1112223334441", "Foundation", "Isaac Asimov", null));
		newBooks.add( new Book("1112223334442", "Journey to the Center of the Earth", "Jules Verne", null));
		BookDAO myBookDAO = new BookDAOImpl();
		for (int i=0; i<newBooks.size(); i++) {
			myBookDAO.addBook(newBooks.get(i));
		}
		TagDAO myTagDAO = new TagDAOImpl();
		myTagDAO.addBookTag(newBooks.get(0), "sci-fi");
		myTagDAO.addBookTag(newBooks.get(0), "adventure");
		myTagDAO.addBookTag(newBooks.get(1), "sci-fi");
		myTagDAO.addBookTag(newBooks.get(1), "psychology");
		myTagDAO.addBookTag(newBooks.get(1), "robot");
		myTagDAO.addBookTag(newBooks.get(2), "adventure");
		myTagDAO.addBookTag(newBooks.get(2), "fantasy");
		
	}
	
}
