package com.neu.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class DAO {

	private static final Logger log = Logger.getAnonymousLogger();

	private static final ThreadLocal sessionThread = new ThreadLocal();

	private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	protected DAO() {
	}

	public static Session getSession() {
		Session session = (Session) DAO.sessionThread.get();

		if (session == null) {
			session = sessionFactory.openSession();
			DAO.sessionThread.set(session);
		}
		return session;
	}

	protected void begin() {
		getSession().beginTransaction();
	}

	protected void commit() {
		getSession().getTransaction().commit();
	}

	protected void rollback() {
		try {
			getSession().getTransaction().rollback();
		} catch (HibernateException e) {
			log.log(Level.WARNING, "Couldn't rollback", e);
		}
		try {
			getSession().close();
		} catch (HibernateException e) {
			log.log(Level.WARNING, "Couldn't close", e);
		}
		DAO.sessionThread.set(null);
	}

	public static void close() {
		getSession().close();
		DAO.sessionThread.set(null);
	}

}