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
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MapKeyTemporal;
import javax.persistence.OneToMany;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NaturalId;


@Entity
public class Phone {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    @NaturalId
    private String number;

    @Enumerated(EnumType.STRING)
    private PhoneType type;

    @OneToMany(mappedBy = "phone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Call> calls = new ArrayList<Call>(  );

    @OneToMany(mappedBy = "phone")
    @MapKey(name = "timestamp")
    @MapKeyTemporal(TemporalType.TIMESTAMP )
    private Map<Date, Call> callHistory = new HashMap<Date, Call>();

    @ElementCollection
    private List<Date> repairTimestamps = new ArrayList<Date>(  );

    
    
	public Phone() {
	}

	public Phone(String number, PhoneType type) {
		this.number = number;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public PhoneType getType() {
		return type;
	}

	public void setType(PhoneType type) {
		this.type = type;
	}

	public List<Call> getCalls() {
		return calls;
	}

	public void setCalls(List<Call> calls) {
		this.calls = calls;
	}

	public Map<Date, Call> getCallHistory() {
		return callHistory;
	}

	public void setCallHistory(Map<Date, Call> callHistory) {
		this.callHistory = callHistory;
	}

	public List<Date> getRepairTimestamps() {
		return repairTimestamps;
	}

	public void setRepairTimestamps(List<Date> repairTimestamps) {
		this.repairTimestamps = repairTimestamps;
	}

	@Override
	public String toString() {
		return "Phone [id=" + id + ", person=" + person.getName() + ", number=" + number + ", type=" + type + ", calls=" + calls + ", callHistory=" + callHistory + ", repairTimestamps=" + repairTimestamps
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phone other = (Phone) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}


    
}