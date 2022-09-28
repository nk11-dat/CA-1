package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "address")
    private Person person;

    @ManyToMany
    @JoinTable(name = "HOBBY_has_ADDRESS",
            joinColumns = @JoinColumn(name = "ADDRESS_idADDRESS"),
            inverseJoinColumns = @JoinColumn(name = "HOBBY_idHOBBY"))
    private Set<Hobby> hobbies = new LinkedHashSet<>();

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

    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }

    public Set<Hobby> getHobbies()
    {
        return hobbies;
    }

    public void setHobbies(Set<Hobby> hobbies)
    {
        this.hobbies = hobbies;
    }

}