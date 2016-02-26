package com.guorui.hibernate_hql;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.junit.Test;

import com.guorui.hibernate_hql.entity.PCall;
import com.guorui.hibernate_hql.entity.Payment;
import com.guorui.hibernate_hql.entity.Person;
import com.guorui.hibernate_hql.entity.Phone;
import com.guorui.hibernate_hql.entity.PhoneType;

public class JPQLTest extends AbstractJPATest {

	@Test
	public void testJPQL() {
		Query query = entityManager.createQuery("select p from Person p where name like :name")
				.setParameter("name", "%Rui");
		List<Person> resultList = query.getResultList();
		resultList.stream().forEach(item->{System.out.println(item);});
		
		TypedQuery<Person> typedQuery = entityManager.createQuery("select p from Person p where name like :name", Person.class);
		typedQuery.setParameter("name", "Zhi%");
		List<Person> resultList2 = typedQuery.getResultList();
		
		for (Person person : resultList2) {
			System.out.println(person);
		}
	}
	
	@Test
	public void testNamedQuery(){
		TypedQuery<Person> namedQuery = entityManager.createNamedQuery("get_person_by_name", Person.class);
		namedQuery.setParameter("name", "Guo Rui");
		List<Person> resultList = namedQuery.getResultList();
		resultList.stream().forEach(item->{System.out.println(item);});
	}
	
	@Test
	public void testQueryDate(){
		Calendar c = Calendar.getInstance();
		c.set(1982, 4, 2);
		Date birth = c.getTime();
		TypedQuery<Person> namedQuery = entityManager.createQuery(
				"select p "
				+ "from Person p "
				+ "where p.createdOn > :timestamp"
				+ "", Person.class);
		namedQuery.setParameter("timestamp", birth,TemporalType.TIMESTAMP);
		
		List<Person> resultList = namedQuery.getResultList();
		for (Person person : resultList) {
			System.out.println(person);
		}
				
	}
	
	@Test
	public void testPosQuery(){
		TypedQuery<Person> typedQuery = entityManager.createQuery(""
				+ "select p "
				+ "from Person p "
				+ "where p.name like ?1", 
				Person.class);
		typedQuery.setParameter(1, "Guo%");
		List<Person> resultList = typedQuery.getResultList();
		for (Person person : resultList) {
			System.out.println(person);
		}
	}
	
	@Test
	public void testSingleResult(){
		TypedQuery<Person> typedQuery = entityManager.createQuery(""
				+ "select p "
				+ "from Person p "
				+ "where id > :id"
				+ "", Person.class).setParameter("id", new Long(1));
		Person singleResult = typedQuery.getSingleResult();
		System.out.println(singleResult);
		//javax.persistence.NonUniqueResultException: result returns more than one elements
	}
	
	@Test
	public void testSelect(){
		List<Person> resultList = entityManager.createQuery("select p "
				+ "from com.guorui.hibernate_hql.entity.Person p", Person.class).getResultList();
		for (Person person : resultList) {
			System.out.println(person);
		}
	}
	
	@Test
	public void testUpdate(){
		int executeUpdate = entityManager.createQuery("update Person p "
				+ "set name = :newname "
				+ "where name = :oldname")
		.setParameter("newname", "haha")
		.setParameter("oldname", "zhang san").executeUpdate();
		
		System.out.println(executeUpdate);
	}
	
	@Test
	public void testMultiTable(){
		List<Object[]> resultList = entityManager.createQuery("select distinct pr, ph "
				+ "from Person pr, Phone ph "
				+ "where ph.person = pr "
				+ "and ph is not null").getResultList();
		
		for (Object[] objects : resultList) {
			for (Object object : objects) {
				System.out.print(object+",");
			}
			System.out.println();
		}
	}
	
	@Test
	public void testJoinSelfTable(){
		List<Object[]> resultList = entityManager.createQuery(" select distinct p1, p2 "
				+ "from Person p1, Person p2 "
				+ "where p1.id <> p2.id "
				+ "and p1.createdOn > p2.createdOn "
				+ "and p1.address = p2.address").getResultList();
		
		for (Object[] objects : resultList) {
			for (Object object : objects) {
				System.out.print(object+",");
			}
			System.out.println();
		}
		
		System.out.println(resultList.size());
	}
	
	@Test
	public void testExplicitJoin(){
		List<Person> resultList = entityManager.createQuery(" select pr "
				+ "from Person pr join pr.phones ph "   //inner join
				+ "where ph.type = :phoneType", Person.class)
		.setParameter("phoneType", PhoneType.MOBILE).getResultList();
		
		for (Person person : resultList) {
			System.out.println(person);
		}
	}
	
	@Test
	public void testLeftJoin(){
		List<Person> resultList = entityManager.createQuery("select pr "
				+ "from Person pr left outer join pr.phones ph "
				+ "where ph is null or ph.type = :phoneType",Person.class)
		.setParameter("phoneType", PhoneType.LAND_LINE).getResultList();
		
		for (Person person : resultList) {
			System.out.println(person);
		}
	}
	
	@Test
	public void testLeftJoinOn(){
		Query query = entityManager.createQuery(" select pr.name,ph.number "
				+ "from Person pr left join pr.phones ph on ph.type = :phoneType");
		List<Object[]> resultList = query.setParameter("phoneType", PhoneType.LAND_LINE).getResultList();
		
		for (Object[] objects : resultList) {
			for (Object object : objects) {
				System.out.print(object+", ");
			}
			System.out.println();
		}
	}
	
	@Test
	public void testLeftFetchJoin(){
		List<Person> resultList = entityManager.createQuery(" select pr "
				+ "from Person pr "
				+ "left join fetch pr.phones",Person.class).getResultList();
		
		for (Person person : resultList) {
			System.out.println(person);
		}
	}
	
	@Test
	public void testPathExpression(){
		TypedQuery<Phone> query = entityManager.createQuery(""
				+ "select ph "
				+ "from Phone ph "
				+ "where ph.person.address = :address",
				Phone.class).setParameter("address", "beijing");
		List<Phone> resultList = query.getResultList();
		for (Phone phone : resultList) {
			System.out.println(phone);
		}
		
		List<Phone> resultList2 = entityManager.createQuery(""
				+ "select ph "
				+ "from Phone ph "
				+ "inner join ph.person pr "
				+ "on pr.address=:address",Phone.class)
		.setParameter("address", "beijing").getResultList();
		
		for (Phone phone : resultList2) {
			System.out.println(phone);
		}
	}
	
	@Test
	public void testCollectionValue(){
		List<Phone> resultList = entityManager.createQuery(""
				+ "select ph "
				+ "from Person pr "
				+ "join pr.phones ph "
				+ "where pr.address=:address"
				+ "",Phone.class).setParameter("address", "beijing").getResultList();
		
		System.out.println(resultList);
		
		List<PCall> resultList2 = entityManager.createQuery(""
				+ "select value(ch) "
				+ "from Phone ph "
				+ "join ph.callHistory ch "
				+ "where ph.id=:id"
				+ "",PCall.class)
		.setParameter("id", 13L).getResultList();
		System.out.println(resultList2);
		
		List<Date> resultList3 = entityManager.createQuery(""
				+ "select key(ch) "
				+ "from Phone ph "
				+ "join ph.callHistory ch "
				+ "where ph.id=:id"
				+ "",Date.class)
		.setParameter("id", 13L).getResultList();
		System.out.println(resultList3);
		

	}
	
	@Test
	public void testMapEntry(){
		List<Map.Entry<Date, PCall>> callHistory = entityManager.createQuery(
			    "select entry(ch) " +
			    "from Phone ph " +
			    "join ph.callHistory ch " +
			    "where ph.id = :id " )
			.setParameter( "id", 13L )
			.getResultList();
		System.out.println(callHistory);
	}
	
	@Test
	public void testPoly(){
		List<Payment> resultList = entityManager.createQuery("select p from Payment p",Payment.class).getResultList();
		
		System.out.println(resultList);
	}
}
