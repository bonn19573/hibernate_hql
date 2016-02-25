package com.guorui.hibernate_hql;


import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.guorui.hibernate_hql.entity.util.HibernateUtil;


public class AbstractTest {

	protected static Session session;

	@BeforeClass
	public static void beforeClass() {
		session = HibernateUtil.getSession();
	}

	@AfterClass
	public static void afterClass() {
		HibernateUtil.closeSession(session);
		HibernateUtil.closeSessionFactory();
	}

	@Before
	public void before() throws InstantiationException, IllegalAccessException {
		session.beginTransaction();
	}

	@After
	public void after() {
		session.getTransaction().commit();
	}
}
