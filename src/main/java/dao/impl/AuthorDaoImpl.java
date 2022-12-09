package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.AuthorDao;
import entity.Author;
import entity.Book;
import jakarta.persistence.Query;
import utils.HibernateUtils;

public class AuthorDaoImpl extends UnicastRemoteObject implements AuthorDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SessionFactory factory;

	public AuthorDaoImpl() throws RemoteException {
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public boolean add(Author a) throws RemoteException {
		Session session = factory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.save(a);
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
		Session session = factory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Author a = session.get(Author.class, id);
			if(a != null) {
				session.delete(a);	
			}
			tr.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public boolean update(Author a) throws RemoteException {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			session.saveOrUpdate(a);
			tr.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Author> getAllAuthors() throws RemoteException {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		List<Author> authors = null;
		try {
			 authors =  session.createQuery("from Author",Author.class).list();
			tr.commit();
			return authors;
		}catch(Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public Author findAuthor(int id) throws RemoteException {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		Author author = null;
		try {
			 author  =  session.createQuery("from Author a where a.id = " + id,Author.class).getSingleResult();
			tr.commit();
			return author;
		}catch(Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

}
