package telran.books.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import telran.books.dto.Book;

public class BooksRestoreAppl {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

		
		try(ObjectInputStream inputStream = new ObjectInputStream(
				new FileInputStream("books.data"))){
			
			List<Book> books = (List<Book>) inputStream.readObject();
			books.forEach(System.out::println);
		}

		
		
	}

}
