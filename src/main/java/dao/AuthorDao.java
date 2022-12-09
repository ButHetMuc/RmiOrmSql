package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

import entity.Author;


public interface AuthorDao extends Remote {
	public boolean add(Author a	) throws RemoteException;
	public boolean remove(long id)throws RemoteException;
	public boolean update(Author a)throws RemoteException;
	public List<Author> getAllAuthors()throws RemoteException;
	public Author findAuthor(int id)throws RemoteException;
}
