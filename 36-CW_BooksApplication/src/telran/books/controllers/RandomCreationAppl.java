package telran.books.controllers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import telran.books.dto.Book;
import telran.books.dto.CoverType;
import telran.books.dto.Genre;
import telran.books.dto.LiteratureBook;
import telran.books.dto.TechnicalBook;

public class RandomCreationAppl {

	private static final long N_BOOKS = 100;
	private static final int PROB_LITERATURE_BOOK = 80;
	private static final int MAX_SUBJECTS = 7;
	private static final int N_AUTHORS = 15;
	private static final int MIN_PAGES = 50;
	private static final int MAX_PAGES = 9_999;
	private static final int N_PUBLISHERS = 1000;
	private static final int MIN_YEAR = 1295;
	private static final int MAX_YEAR = 2020;

	public static void main(String[] args) throws FileNotFoundException, IOException {

		try (ObjectOutputStream output = 
				new ObjectOutputStream(new FileOutputStream("books.data"))) {
			
			List<Book> books = getRandomBooks();
			output.writeObject(books);
			
		}
		
		
		
	}

	private static List<Book> getRandomBooks() {
		return Stream.generate(RandomCreationAppl::getRandomBook).
				distinct().
				limit(N_BOOKS).
				collect(Collectors.toList());
	}

	private static Book getRandomBook() {

		return getChance() <= PROB_LITERATURE_BOOK ? getLiteratureBook() : getTechnikalBook();
	}

	private static int getChance() {
		return getRandomNumber(0,100);
	}

	private static int getRandomNumber(int min, int max) {
		return (int) (min + Math.random() * (max - min));
	}

	private static Book getTechnikalBook() {
		Book book = getBook();
		String subject = "subject" + getRandomNumber(1, MAX_SUBJECTS);
		return getTechnikalBook(book, subject);
	}

	private static Book getTechnikalBook(Book book, String subject) {
		return new TechnicalBook(
				book.getIsbn(),
				book.getTitle(),
				book.getAuthor(),
				book.getCover(),
				book.getPages(),
				book.getPublisher(),
				book.getPublishDate(),
				subject);
	}

	private static Book getBook() {
		long isbn = getRandomNumber(1_000_00, 1_000_000);
		String title = "title" + isbn;
		String author = "author" + getRandomNumber(1, N_AUTHORS);
		CoverType cover = getCover();
		int pages = getRandomNumber(MIN_PAGES, MAX_PAGES);
		String publisher = "publisher" + getRandomNumber(1, N_PUBLISHERS);
		LocalDate publishDate = getPublishDate();
		return new Book(isbn, title, author, cover, pages, publisher, publishDate);
	}

	private static CoverType getCover() {
		CoverType[] allCovers  = CoverType.values();
		int index = getRandomNumber(0, allCovers.length);
		return allCovers[index];
	}

	private static LocalDate getPublishDate() {
		int year = getRandomNumber(MIN_YEAR, MAX_YEAR);
		int dayOfYear = getRandomNumber(1, 365);
		return LocalDate.ofYearDay(year, dayOfYear);
	}

	private static Book getLiteratureBook() {
		Genre  genre = getGenre();
		return getLiteratureBook(getBook(), genre);
	}

	private static Book getLiteratureBook(Book book, Genre genre) {
		
		return new LiteratureBook(
				book.getIsbn(),
				book.getTitle(),
				book.getAuthor(),
				book.getCover(),
				book.getPages(),
				book.getPublisher(),
				book.getPublishDate(),
				genre);
		}

	private static Genre getGenre() {
		Genre[] allGenres = Genre.values();
		int index = getRandomNumber(0, allGenres.length);
		return allGenres[index];
	}


	
	
}
