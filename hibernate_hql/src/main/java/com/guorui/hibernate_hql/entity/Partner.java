package com.guorui.hibernate_hql.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Partner {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@Version
	private int version;

	//Getters and setters are omitted for brevity

}
