/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package com.guorui.hibernate_hql.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

/**
 * @author Vlad Mihalcea
 */
//tag::hql-examples-domain-model-example[]
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Payment {

    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal amount;

    private boolean completed;

    @ManyToOne
    private Person person;

    //Getters and setters are omitted for brevity

//end::hql-examples-domain-model-example[]

    public Long getId() {
        return id;
    }

    public Payment() {
	}

	public Payment(BigDecimal amount, boolean completed) {
		this.amount = amount;
		this.completed = completed;
	}

	public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

	@Override
	public String toString() {
		return "Payment [id=" + id + ", amount=" + amount + ", completed=" + completed + ", person=" + person.getName() + "]";
	}
    
//tag::hql-examples-domain-model-example[]
}
//end::hql-examples-domain-model-example[]
