package app;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.AuthorDao;
import dao.BookDao;
import dao.PublisherDao;
import entity.Author;
import entity.Book;
import entity.Publisher;

public class Main {
	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
		String rmiUrl = "rmi://localhost:3001/";
		Author a = new Author("butpro","0333444555","1st Saint Simon");
		Publisher p = new Publisher("kim dong","03344556677","2nd february");

		Set<Publisher> set = new HashSet<Publisher>();
		set.add(p);
		Book b = new Book( "Holy Bible", 100, 1860, a, set);
		
		AuthorDao authorDao = (AuthorDao) Naming.lookup(rmiUrl+"author");
		PublisherDao pubDao = (PublisherDao)Naming.lookup(rmiUrl+"publisher");
		BookDao bookDao = (BookDao) Naming.lookup(rmiUrl+"book");
		authorDao.add(a);
//		bookDao.add(b);
		
//		List<Book> books = bookDao.getAllBooks();
//		System.out.println(books.size());
//		bookDao.remove(3);
//		List<Book> books1 = bookDao.getAllBooks();
//		System.out.println(books1.size());

//		authorDao.remove(4);
		Author a1 = authorDao.findAuthor(5);
		System.out.println(a1.toString());
		
		
	}
}
