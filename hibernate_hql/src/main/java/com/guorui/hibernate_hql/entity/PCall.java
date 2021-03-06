/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package com.guorui.hibernate_hql.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Vlad Mihalcea
 */
//tag::hql-examples-domain-model-example[]
@Entity
public class PCall {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Phone phone;

    private Date timestamp;

    private int duration;

    //Getters and setters are omitted for brevity

//end::hql-examples-domain-model-example[]
    public PCall() {}

    public PCall(Date timestamp, int duration) {
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
		return "PCall [id=" + id + ", phone=" + phone.getNumber() + ", timestamp=" + timestamp + ", duration=" + duration + "]";
	}
    
//tag::hql-examples-domain-model-example[]
}
//end::hql-examples-domain-model-example[]
