package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import dao.AuthorDao;
import dao.BookDao;
import dao.PublisherDao;
import dao.impl.*;

public class MyServer {
	public static void main(String[] args) throws RemoteException {
		Registry registry = LocateRegistry.createRegistry(3001);
		BookDao book = new  BookDaoImpl(); 
		PublisherDao publisherDao = new PublisherDaoImpl();
		AuthorDao authorDao = new AuthorDaoImpl();
		
		registry.rebind("book", book);
		registry.rebind("author", authorDao);
		registry.rebind("publisher", publisherDao);
		
		System.out.println("rebind ok");
	}

}
