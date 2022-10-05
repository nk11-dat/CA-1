package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "PHONE")
public class Phone
{
    @Id
    @Size(max = 45)
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phoneNumber", nullable = false, length = 45)
    private String phoneNumber;

    @Size(max = 45)
    @NotNull
    @Column(name = "description", nullable = false, length = 45)
    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idPERSON", nullable = false)
    private Person idPERSON = new Person();

    private Integer idPERSONInt;

    public Phone()
    {
    }

    public Phone(String phoneNumber, String description, Person idPERSON)
    {
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.idPERSON = idPERSON;
    }

    public Phone(String phoneNumber, String description)
    {
        this.phoneNumber = phoneNumber;
        this.description = description;
    }

    public Phone(String phoneNumber, String description, Integer idPERSONInt)
    {
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.idPERSONInt = idPERSONInt;
    }

    public Phone(String description, Person idPERSON)
    {
        this.description = description;
        this.idPERSON = idPERSON;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Person getIdPERSON()
    {
        return idPERSON;
    }

    public void setIdPERSON(Person idPERSON)
    {
        this.idPERSON = idPERSON;
    }

    @Override
    public String toString()
    {
        return "Phone{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", description='" + description + '\'' +
                ", idPERSON=" + idPERSON +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Phone)) return false;
        Phone phone = (Phone) o;
        return getPhoneNumber().equals(phone.getPhoneNumber()) && getDescription().equals(phone.getDescription()) && getIdPERSON().equals(phone.getIdPERSON());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getPhoneNumber(), getDescription(), getIdPERSON());
    }
}