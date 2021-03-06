/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package com.guorui.hibernate_hql.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MapKeyTemporal;
import javax.persistence.OneToMany;
import javax.persistence.TemporalType;

/**
 * @author Vlad Mihalcea
 */
//tag::hql-examples-domain-model-example[]
@Entity
public class Phone {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="person_id")
    private Person person;

    private String number;

    @Enumerated(EnumType.STRING)
    private PhoneType type;

    @OneToMany(mappedBy = "phone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PCall> calls = new ArrayList<PCall>(  );

    //tag::hql-collection-qualification-example[]
    @OneToMany(mappedBy = "phone")
    @MapKey(name = "timestamp")
    @MapKeyTemporal(TemporalType.TIMESTAMP )
    private Map<Date, PCall> callHistory = new HashMap<Date, PCall>();
    //end::hql-collection-qualification-example[]

    @ElementCollection
    private List<Date> repairTimestamps = new ArrayList<Date>(  );

    //Getters and setters are omitted for brevity

//end::hql-examples-domain-model-example[]
    public Phone() {}

    public Phone(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }

    public List<PCall> getCalls() {
        return calls;
    }

    public Map<Date, PCall> getCallHistory() {
        return callHistory;
    }

    public List<Date> getRepairTimestamps() {
        return repairTimestamps;
    }

    public void addCall(PCall call) {
        calls.add( call );
        callHistory.put( call.getTimestamp(), call );
        call.setPhone( this );
    }

	@Override
	public String toString() {
		return "Phone [id=" + id + ", person=" + person.getName() + ", number=" + number + ", type=" + type + "]";
	}
    
//tag::hql-examples-domain-model-example[]
}
//end::hql-examples-domain-model-example[]
