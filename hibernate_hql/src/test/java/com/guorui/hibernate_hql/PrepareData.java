package com.guorui.hibernate_hql;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.guorui.hibernate_hql.entity.Person;
import com.guorui.hibernate_hql.entity.Phone;
import com.guorui.hibernate_hql.entity.PhoneType;

public class PrepareData extends AbstractTest{
	
	@Test
	public void prepareDate(){
		Phone phone = new Phone("111");
		Phone phone2 = new Phone("222");
		Phone phone3 = new Phone("333");
		Phone phone4 = new Phone("444");
		
		Person person = new Person("Jack");
		person.addPhone(phone);
		person.addPhone(phone2);
		Person person2 = new Person("Chris");
		person2.addPhone(phone3);
		person2.addPhone(phone4);
		
		session.save(person);
		session.save(person2);
		
		
	}
	
	@Test
	public void prepareData2(){
		Person person = new Person("zhang san");
		person.setCreatedOn(new Date());
		Person person2 = new Person("Li si");
		Calendar c = Calendar.getInstance();
		c.set(1983, 4, 2);
		Date birth = c.getTime();
		person2.setCreatedOn(birth);
		
		session.save(person);
		session.save(person2);
		
		
	}
	
	@Test
	public void prepareDate3(){
		Phone phone = new Phone("555");
		phone.setType(PhoneType.MOBILE);
		Phone phone2 = new Phone("666");
		phone2.setType(PhoneType.LAND_LINE);
		Phone phone3 = new Phone("777");
		phone3.setType(PhoneType.MOBILE);
		Phone phone4 = new Phone("888");
		phone4.setType(PhoneType.LAND_LINE);
		
		Person person = new Person("Li Ming");
		person.addPhone(phone);
		person.addPhone(phone2);
		Person person2 = new Person("Wang Li");
		person2.addPhone(phone3);
		person2.addPhone(phone4);
		
		session.save(person);
		session.save(person2);
		
		
	}

}
