package com.guorui.hibernate_hql.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@NamedQueries(@NamedQuery(name = "get_persion_by_name", query = "select p from Person p where name like :name"))
@Entity
public class Person {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private String nickName;

	private String address;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@OneToMany(mappedBy = "person")
	@OrderColumn(name = "order_id")
	private List<Phone> phones = new ArrayList<Phone>();

	@ElementCollection
	@MapKeyEnumerated(EnumType.STRING)
	private Map<AddressType, String> addresses = new HashMap<AddressType, String>();

	@Version
	private int version;
	

	public Person() {
	}

	public Person(String name, String nickName, String address) {
		this.name = name;
		this.nickName = nickName;
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
	
	public void addPhone(Phone phone){
		if(!this.phones.contains(phone)){
			this.phones.add(phone);
			phone.setPerson(this);
		}
	}
	
	public void removePhone(Phone phone){
		if(this.phones.contains(phone)){
			this.phones.remove(phone);
			phone.setPerson(null);
		}
	}

	public Map<AddressType, String> getAddresses() {
		return addresses;
	}

	public void setAddresses(Map<AddressType, String> addresses) {
		this.addresses = addresses;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", nickName=" + nickName + ", address=" + address + ", createdOn=" + createdOn + ", phones=" + phones + ", addresses=" + addresses + ", version="
				+ version + "]";
	}

	
	
}

enum AddressType {
	HOME, OFFICE;
}