package Tagging;

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
		TagDAO myInterface = new TagDAOImpl();
		
		Book myBook = myInterface.getBookByISBN("1111111111111");
		System.out.println("Testing getBookByISBN");
		System.out.println(myBook.getAuthor() + "\n");
		
		// test getTagsByBook
		System.out.println("testing getTagsByBook");
		List<String> myTags = myInterface.getTagsByBook(myBook);
		for (int i=0; i<myTags.size(); i++) { 
			System.out.println(myTags.get(i) + "\n");
		}

		/* test addBookTag
		System.out.println("testing getAddBookTag");
		boolean addTagResult = myInterface.addBookTag(myBook, "fanfic");
		if (addTagResult) {
				System.out.println("addBookTag didn't fail \n");
			}
		*/
		
		// test getBooksByTag
		System.out.println("testing getBooksByTag");
		List<Book> dramaBooks = myInterface.getBooksByTag("drama");
		for (int i=0; i<myTags.size(); i++) { 
			System.out.println(dramaBooks.get(i).getTitle());
		}
		
			
	}
}
