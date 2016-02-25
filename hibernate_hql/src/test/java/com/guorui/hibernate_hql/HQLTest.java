package com.guorui.hibernate_hql;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.junit.Test;

import com.guorui.hibernate_hql.entity.Person;
import com.guorui.hibernate_hql.entity.PhoneType;

public class HQLTest extends AbstractTest {
	
	@Test
	public void testNamedQuery(){
		Calendar c = Calendar.getInstance();
		c.set(1984, 4, 2);
		Date birth = c.getTime();
		Query createQuery = session.createQuery(""
				+ "select p "
				+ "from Person p "
				+ "where name = :name "
				+ "and createdOn > :timeStamp").setString("name", "zhang san")
				.setTimestamp("timeStamp", birth);
		
		List<Person> list = createQuery.list();
		for (Person person : list) {
			System.out.println(person);
		}
		
		Query query = session.createQuery("select p "
				+ "from Person p "
				+ "where createdOn > ?").setTimestamp(0, birth);
		Object uniqueResult = query.uniqueResult();
		System.out.println(uniqueResult);
	}
	
	@Test
	public void testSelect(){
		List<Person> list = session.createQuery("from Person").list();
		for (Person person : list) {
			System.out.println(person);
		}
	}
	
	@Test
	public void testUpdate(){
		int executeUpdate = session.createQuery("update Person "
				+ "set name = :newname "
				+ "where name = :oldname")
		.setParameter("newname", "zhang san")
		.setParameter("oldname", "haha")
		.executeUpdate();
		
		System.out.println(executeUpdate);
		
		int executeUpdate2 = session.createQuery("update versioned Person "
				+ "set name = :newname "
				+ "where name = :oldname")
		.setParameter("newname", "haha")
		.setParameter("oldname", "zhang san")
		.executeUpdate();
		
		System.out.println(executeUpdate2);
	}
	
	@Test
	public void testDelete(){
		int executeUpdate = session.createQuery("delete Person "
				+ "where name = :name")
		.setParameter("name", "haha")
		.executeUpdate();
		
		System.out.println(executeUpdate);
	}

	@Test
	public void testInsert(){
		int executeUpdate = session.createQuery("insert into Partner (id, name) "
				+ "select id, name from Person ").executeUpdate();
		
		System.out.println(executeUpdate);
	}
	
	@Test
	public void testLeftJoinWith(){
		List<Object[]> list = session.createQuery(" select pr.name, ph.number "
				+ "from Person pr left join pr.phones ph "
				+ "with ph.type = :phoneType")
		.setParameter("phoneType", PhoneType.LAND_LINE).list();
		
		for (Object[] objects : list) {
			for (Object object : objects) {
				System.out.print(object+",");
			}
			System.out.println();
		}
		
		
	}
}
