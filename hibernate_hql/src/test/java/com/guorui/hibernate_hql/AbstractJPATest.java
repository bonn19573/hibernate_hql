package com.guorui.hibernate_hql;


import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.guorui.hibernate_hql.entity.util.HibernateJPAUtil;


public class AbstractJPATest {

	protected static EntityManager entityManager;

	@BeforeClass
	public static void beforeClass() {
		entityManager = HibernateJPAUtil.getEntityManager();
	}

	@AfterClass
	public static void afterClass() {
		HibernateJPAUtil.closeEntityManager(entityManager);
		HibernateJPAUtil.closeEntityManagerFactory();
	}

	@Before
	public void before() throws InstantiationException, IllegalAccessException {
		entityManager.getTransaction().begin();;
	}

	@After
	public void after() {
		entityManager.getTransaction().commit();
	}
}
