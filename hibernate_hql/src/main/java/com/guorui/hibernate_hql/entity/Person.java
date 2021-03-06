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
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.EnumType;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * @author Vlad Mihalcea
 */
@NamedNativeQueries({
    //tag::sql-scalar-NamedNativeQuery-example[]
    @NamedNativeQuery(
        name = "find_person_name",
        query =
            "SELECT name " +
            "FROM person "
    ),
    //end::sql-scalar-NamedNativeQuery-example[]
    //tag::sql-multiple-scalar-values-NamedNativeQuery-example[]
    @NamedNativeQuery(
        name = "find_person_name_and_nickName",
        query =
            "SELECT " +
            "   name, " +
            "   nickName " +
            "FROM person "
    ),
    //end::sql-multiple-scalar-values-NamedNativeQuery-example[]
    // tag::sql-multiple-scalar-values-dto-NamedNativeQuery-example[]
    @NamedNativeQuery(
        name = "find_person_name_and_nickName_dto",
        query =
            "SELECT " +
            "   name, " +
            "   nickName " +
            "FROM person ",
        resultSetMapping = "name_and_nickName_dto"
    ),
    //end::sql-multiple-scalar-values-dto-NamedNativeQuery-example[]
    //tag::sql-entity-NamedNativeQuery-example[]
    @NamedNativeQuery(
        name = "find_person_by_name",
        query =
            "SELECT " +
            "   p.id AS \"id\", " +
            "   p.name AS \"name\", " +
            "   p.nickName AS \"nickName\", " +
            "   p.address AS \"address\", " +
            "   p.createdOn AS \"createdOn\", " +
            "   p.version AS \"version\" " +
            "FROM person p " +
            "WHERE p.name LIKE :name",
        resultClass = Person.class
    ),
    //end::sql-entity-NamedNativeQuery-example[]
    //tag::sql-entity-associations-NamedNativeQuery-example[]
    @NamedNativeQuery(
        name = "find_person_with_phones_by_name",
        query =
            "SELECT " +
            "   pr.id AS \"pr.id\", " +
            "   pr.name AS \"pr.name\", " +
            "   pr.nickName AS \"pr.nickName\", " +
            "   pr.address AS \"pr.address\", " +
            "   pr.createdOn AS \"pr.createdOn\", " +
            "   pr.version AS \"pr.version\", " +
            "   ph.id AS \"ph.id\", " +
            "   ph.person_id AS \"ph.person_id\", " +
            "   ph.number AS \"ph.number\", " +
            "   ph.type AS \"ph.type\" " +
            "FROM person pr " +
            "JOIN phone ph ON pr.id = ph.person_id " +
            "WHERE pr.name LIKE :name",
        resultSetMapping = "person_with_phones"
    )
    //end::sql-entity-associations-NamedNativeQuery-example[]
})
@SqlResultSetMappings({
    //tag::sql-entity-associations-NamedNativeQuery-example[]
     @SqlResultSetMapping(
         name = "person_with_phones",
         entities = {
             @EntityResult(
                 entityClass = Person.class,
                 fields = {
                     @FieldResult( name = "id", column = "pr.id" ),
                     @FieldResult( name = "name", column = "pr.name" ),
                     @FieldResult( name = "nickName", column = "pr.nickName" ),
                     @FieldResult( name = "address", column = "pr.address" ),
                     @FieldResult( name = "createdOn", column = "pr.createdOn" ),
                     @FieldResult( name = "version", column = "pr.version" ),
                 }
             ),
             @EntityResult(
                 entityClass = Phone.class,
                 fields = {
                     @FieldResult( name = "id", column = "ph.id" ),
                     @FieldResult( name = "person", column = "ph.person_id" ),
                     @FieldResult( name = "number", column = "ph.number" ),
                     @FieldResult( name = "type", column = "ph.type" ),
                 }
             )
         }
     ),
    //end::sql-entity-associations-NamedNativeQuery-example[]
    //tag::sql-multiple-scalar-values-dto-NamedNativeQuery-example[]
    @SqlResultSetMapping(
        name = "name_and_nickName_dto",
        classes = @ConstructorResult(
            targetClass = PersonNames.class,
            columns = {
                @ColumnResult(name = "name"),
                @ColumnResult(name = "nickName")
            }
        )
    )
    //end::sql-multiple-scalar-values-dto-NamedNativeQuery-example[]
})
//tag::hql-examples-domain-model-example[]
//tag::jpql-api-named-query-example[]
@NamedQueries(
    @NamedQuery(
        name = "get_person_by_name",
        query = "select p from Person p where name = :name"
    )
)
//end::jpql-api-named-query-example[]
@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String nickName;

    private String address;

    @Temporal(TemporalType.TIMESTAMP )
    private Date createdOn;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @OrderColumn(name = "order_id")
    private List<Phone> phones = new ArrayList<Phone>();

    @ElementCollection
    @MapKeyEnumerated(EnumType.STRING)
    private Map<AddressType, String> addresses = new HashMap<AddressType, String>();

    @Version
    private int version;

    //Getters and setters are omitted for brevity

//end::hql-examples-domain-model-example[]

    public Person() {}

    public Person(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public Map<AddressType, String> getAddresses() {
        return addresses;
    }

    public void addPhone(Phone phone) {
        phones.add( phone );
        phone.setPerson( this );
    }

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", nickName=" + nickName + ", address=" + address + ", createdOn=" + createdOn + ", phones=" + phones + ", addresses=" + addresses + ", version="
				+ version + "]";
	}
    
//tag::hql-examples-domain-model-example[]
}
//end::hql-examples-domain-model-example[]
