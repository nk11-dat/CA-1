package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ADDRESS")
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idADDRESS", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "street", nullable = false, length = 45)
    private String street;

    @Size(max = 45)
    @Column(name = "aditionalInfo", length = 45)
    private String aditionalInfo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCITY", nullable = false)
    private Cityinfo idCITY;

    @OneToMany(mappedBy = "address")
    private Set<Person> persons = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "HOBBY_has_ADDRESS",
            joinColumns = @JoinColumn(name = "ADDRESS_idADDRESS"),
            inverseJoinColumns = @JoinColumn(name = "HOBBY_idHOBBY"))
    private Set<Hobby> hobbies = new LinkedHashSet<>();

    public Address()
    {
    }

    public Address(String street, String aditionalInfo, Cityinfo idCITY, Set<Person> persons)
    {
        this.street = street;
        this.aditionalInfo = aditionalInfo;
        this.idCITY = idCITY;
        this.persons = persons;
    }

    public Address(String street, String aditionalInfo, Cityinfo idCITY, Set<Person> persons, Set<Hobby> hobbies)
    {
        this.street = street;
        this.aditionalInfo = aditionalInfo;
        this.idCITY = idCITY;
        this.persons = persons;
        this.hobbies = hobbies;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getAditionalInfo()
    {
        return aditionalInfo;
    }

    public void setAditionalInfo(String aditionalInfo)
    {
        this.aditionalInfo = aditionalInfo;
    }

    public Cityinfo getIdCITY()
    {
        return idCITY;
    }

    public void setIdCITY(Cityinfo idCITY)
    {
        this.idCITY = idCITY;
    }

    public Set<Person> getPerson()
    {
        return persons;
    }

    public void setPerson(Set<Person> person)
    {
        this.persons = person;
    }

    public Set<Hobby> getHobbies()
    {
        return hobbies;
    }

    public void setHobbies(Set<Hobby> hobbies)
    {
        this.hobbies = hobbies;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return getId().equals(address.getId()) && getStreet().equals(address.getStreet()) && Objects.equals(getAditionalInfo(), address.getAditionalInfo()) && getIdCITY().equals(address.getIdCITY());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), getStreet(), getAditionalInfo(), getIdCITY());
    }

    @Override
    public String toString()
    {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", aditionalInfo='" + aditionalInfo + '\'' +
                ", idCITY=" + idCITY +
                ", person=" + persons +
                ", hobbies=" + hobbies +
                '}';
    }
}