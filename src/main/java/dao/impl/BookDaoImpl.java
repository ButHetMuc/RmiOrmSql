package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.BookDao;
import entity.Book;
import utils.HibernateUtils;

public class BookDaoImpl extends UnicastRemoteObject implements BookDao  {

	SessionFactory factory ;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookDaoImpl() throws RemoteException {
		factory = HibernateUtils.getSessionFactory();
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean add(Book book) throws RemoteException {
		Session session = factory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.save(book);
			tr.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		
		return false;
	}

	@Override
	public boolean remove(long id) throws RemoteException {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Book b = session.get(Book.class, id);
			if(b!= null) {
				session.delete(b);
			}
			tr.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public boolean update(Book newBook) throws RemoteException {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			session.saveOrUpdate(newBook);
			tr.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getAllBooks() throws RemoteException {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		List<Book> books = null;
		try {
			 books =   session.createQuery("from Book",Book.class).list();
			 System.out.println(books.size());
			tr.commit();
			return books;
		}catch(Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}


	@Override
	public Book findBook(int id) throws RemoteException {
		// TODO Auto-generated method 
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			Book book = session.get(Book.class, id);
			tr.commit();
			return book;
		}catch(Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}


}
