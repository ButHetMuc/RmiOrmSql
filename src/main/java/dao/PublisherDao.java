package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

import entity.Publisher;

public interface PublisherDao extends Remote {
	public boolean add(Publisher p) throws RemoteException;
	public boolean remove(long id) throws RemoteException;
	public boolean update(Publisher newp) throws RemoteException;
	public List<Publisher> getAllPublishers() throws RemoteException;
	public Publisher findPublisher(long id) throws RemoteException;
}
