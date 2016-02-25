package com.guorui.hibernate_hql.entity.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateJPAUtil {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static EntityManagerFactory getEntityManagerFactory() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
		}
		return entityManagerFactory;
	}

	public static EntityManager getEntityManager() {
		if (entityManager == null) {
			entityManager = getEntityManagerFactory().createEntityManager();
		}
		return entityManager;
	}

	
	public static void closeEntityManager(EntityManager entityManager){
		if(entityManager!=null){
			entityManager.close();
		}
		
	}
	
	public static void closeEntityManagerFactory(){
		if(entityManagerFactory != null){
			entityManagerFactory.close();
		}
	}
}
