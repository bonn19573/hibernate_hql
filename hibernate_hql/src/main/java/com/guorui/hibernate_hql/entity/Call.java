package com.guorui.hibernate_hql.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Call {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Phone phone;

    private Date timestamp;

    private int duration;
    
    

	public Call() {
	}

	public Call(Date timestamp, int duration) {
		this.timestamp = timestamp;
		this.duration = duration;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Call [id=" + id + ", phone=" + phone.getNumber() + ", timestamp=" + timestamp + ", duration=" + duration + "]";
	}


}