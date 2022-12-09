package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

import entity.Book;

public interface BookDao extends Remote {
	public boolean add(Book book)throws RemoteException;
	public boolean remove(long id)throws RemoteException;
	public boolean update(Book newBook)throws RemoteException;
	public List<Book> getAllBooks()throws RemoteException;
	public Book findBook(int id)throws RemoteException;
}
