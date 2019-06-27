SELECT * FROM books;

SELECT isbn_13 FROM books;

CREATE TABLE book_tags(
   book_tag VARCHAR(100),
   isbn_13 VARCHAR(100),
   PRIMARY KEY( book_tag, isbn_13 )
);

SELECT * FROM book_tags;

INSERT INTO book_tags (book_tag, isbn_13) VALUES 
('drama', (SELECT isbn_13 FROM books WHERE title='The Adventures of Steve'));

SELECT * FROM book_tags;
