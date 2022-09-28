package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "HOBBY")
public class Hobby
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHOBBY", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Size(max = 200)
    @NotNull
    @Column(name = "wikiLink", nullable = false, length = 200)
    private String wikiLink;

    @Size(max = 45)
    @NotNull
    @Column(name = "category", nullable = false, length = 45)
    private String category;

    @Size(max = 45)
    @NotNull
    @Column(name = "type", nullable = false, length = 45)
    private String type;

    @ManyToMany
    @JoinTable(name = "PERSON_has_HOBBY",
            joinColumns = @JoinColumn(name = "HOBBY_idHOBBY"),
            inverseJoinColumns = @JoinColumn(name = "PERSON_idPERSON"))
    private Set<Person> people = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "HOBBY_has_ADDRESS",
            joinColumns = @JoinColumn(name = "HOBBY_idHOBBY"),
            inverseJoinColumns = @JoinColumn(name = "ADDRESS_idADDRESS"))
    private Set<Address> addresses = new LinkedHashSet<>();

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getWikiLink()
    {
        return wikiLink;
    }

    public void setWikiLink(String wikiLink)
    {
        this.wikiLink = wikiLink;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Set<Person> getPeople()
    {
        return people;
    }

    public void setPeople(Set<Person> people)
    {
        this.people = people;
    }

    public Set<Address> getAddresses()
    {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses)
    {
        this.addresses = addresses;
    }

}