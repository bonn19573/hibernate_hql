package com.guorui.hibernate_hql;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.guorui.hibernate_hql.entity.PCall;
import com.guorui.hibernate_hql.entity.Payment;
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
	
	@Test
	public void prepareCallHistory(){
		Phone phone = session.get(Phone.class, 13L);
		PCall pCall = new PCall(new Date(), 5);
		PCall pCall2 = new PCall(new Date(), 10);
		phone.addCall(pCall);
		phone.addCall(pCall2);
		session.save(phone);
		
		Phone phone2 = session.get(Phone.class, 14L);
		PCall pCall3 = new PCall(new Date(), 15);
		PCall pCall4 = new PCall(new Date(), 20);
		phone2.addCall(pCall3);
		phone2.addCall(pCall4);
		session.save(phone2);
		
	}

	
	@Test
	public void preparePayment(){
		Person person = session.get(Person.class, 1L);
		Payment payment = new Payment(new BigDecimal(100), true);
		Payment payment2 = new Payment(new BigDecimal(200), false);
		payment.setPerson(person);
		payment2.setPerson(person);
		
		session.save(payment);
		session.save(payment2);
		
	}
}
