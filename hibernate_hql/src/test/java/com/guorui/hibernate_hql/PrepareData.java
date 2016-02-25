package com.guorui.hibernate_hql;

import org.junit.Test;

import com.guorui.hibernate_hql.entity.Person;

public class PrepareData extends AbstractTest{
	
	@Test
	public void prepareDate(){
		Person person = new Person("Guo Rui");
		Person person2 = new Person("Zhi Jia");
		
		session.save(person);
		session.save(person2);
		
		
	}

}
