package com.guorui.hibernate_hql.entity.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static SessionFactory factory = null;
	private static Session session = null;
	
	public static SessionFactory getSessionFactory(){
		if(factory == null){
			factory = new Configuration().configure().buildSessionFactory();
		}
		return factory;
	}
	
	public static Session getSession(){
		if(session== null){
			session = getSessionFactory().openSession();
		}
		return session;
	}
	
/*	public static Session getCurrentSession(){
		return getSessionFactory().getCurrentSession();
	}*/
	
	public static void closeSessionFactory(){
		getSessionFactory().close();
	}
	
	public static void closeSession(Session session){
		session.close();
	}

}
