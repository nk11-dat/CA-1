package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person")
@Table(name = "PERSON")
public class Person
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPERSON", nullable = false)
    private java.lang.Integer id;


    @Size(max = 45)
    @NotNull
    @Column(name = "firstName", nullable = false, length = 45)
    private String firstName;

    @Size(max = 45)
    @NotNull
    @Column(name = "lastName", nullable = false, length = 45)
    private String lastName;

    @NotNull
    @Column(name = "age", nullable = false)
    private java.lang.Integer age;

    @Size(max = 45)
    @NotNull
    @Column(name = "gender", nullable = false, length = 45)
    private String gender;

    @Size(max = 45)
    @NotNull
    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @OneToMany(mappedBy = "idPERSON", cascade = CascadeType.ALL)
    private Set<Phone> phones = new LinkedHashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "PERSON_has_HOBBY",
            joinColumns = @JoinColumn(name = "PERSON_idPERSON"),
            inverseJoinColumns = @JoinColumn(name = "HOBBY_idHOBBY"))
    private Set<Hobby> hobbies = new LinkedHashSet<>();

//    @MapsId
//    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "idPERSON", nullable = false)
//    private Address address;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "idADDRESS", nullable = false)
    private Address idADDRESS;


    public Address getIdADDRESS()
    {
        return idADDRESS;
    }

    public void setIdADDRESS(Address idADDRESS)
    {
        this.idADDRESS = idADDRESS;
    }

    public Person()
    {
    }

    public Person(Address address, String firstName, String lastName, java.lang.Integer age, String gender, String email)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.idADDRESS = address;
    }

    public Person(Address address, String firstName, String lastName, java.lang.Integer age, String gender, String email, Set<Phone> phones, Set<Hobby> hobbies)
    {
        this.idADDRESS = address;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phones = phones;
//        for (Phone phone : phones) {
//            phone.setIdPERSON(this);
//        }
        this.hobbies = hobbies;
    }

    public Person(Address address, String firstName, String lastName, Integer age, String gender, String email, Set<Hobby> hobbies)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.hobbies = hobbies;
        this.idADDRESS = address;
    }

    public Person(String firstName, String lastName, Integer age, String gender, String email)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
    }

    public java.lang.Integer getId()
    {
        return id;
    }

    public void setId(java.lang.Integer id)
    {
        this.id = id;
    }

    public Address getAddress()
    {
        return idADDRESS;
    }

    public void setAddress(Address address)
    {
        this.idADDRESS = address;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public java.lang.Integer getAge()
    {
        return age;
    }

    public void setAge(java.lang.Integer age)
    {
        this.age = age;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Set<Phone> getPhones()
    {
        return phones;
    }

    public void setPhones(Set<Phone> phones)
    {
        this.phones = phones;
    }

    public Set<Hobby> getHobbies()
    {
        return hobbies;
    }

    public void setHobbies(Set<Hobby> hobbies)
    {
        this.hobbies = hobbies;
    }

//    @Override
//    public String toString()
//    {
//        return "Person{" +
//                "id=" + id +
//                ", address=" + address +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", age=" + age +
//                ", gender='" + gender + '\'' +
//                ", email='" + email + '\'' +
//                ", phones=" + phones +
//                ", hobbies=" + hobbies +
//                '}';
//    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getId().equals(person.getId()) && getAddress().equals(person.getAddress()) && getFirstName().equals(person.getFirstName()) && getLastName().equals(person.getLastName()) && getAge().equals(person.getAge()) && getGender().equals(person.getGender()) && getEmail().equals(person.getEmail());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), getAddress(), getFirstName(), getLastName(), getAge(), getGender(), getEmail());
    }

    public void addPhone(Phone mobil)
    {
        this.phones.add(mobil);
        mobil.setIdPERSON(this);
    }

    public void addHobby(Hobby hobby)
    {
        this.hobbies.add(hobby);
//        hobby.getPeople().add(this);
    }
}