package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.PublisherDao;
import entity.Author;
import entity.Book;
import entity.Publisher;
import utils.HibernateUtils;

public class PublisherDaoImpl extends UnicastRemoteObject implements PublisherDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SessionFactory factory;
	public PublisherDaoImpl() throws RemoteException {
		 factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public boolean add(Publisher p) throws RemoteException {
		Session session = factory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.save(p);
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
			Publisher a = session.get(Publisher.class, id);
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
	public boolean update(Publisher newp) throws RemoteException {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			session.saveOrUpdate(newp);
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
	public List<Publisher> getAllPublishers() throws RemoteException {
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		List<Publisher> pubs = null;
		try {
			pubs =  session.createQuery("from Publisher",Publisher.class).list();
			tr.commit();
			return pubs;
		}catch(Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public Publisher findPublisher(long id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
