package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "CITYINFO")
public class Cityinfo
{
    @Id
    @Column(name = "idCITY", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "city", nullable = false, length = 45)
    private String city;

    @Size(max = 45)
    @NotNull
    @Column(name = "zipcode", nullable = false, length = 45)
    private String zipcode;

    @OneToMany(mappedBy = "idCITY")
    private Set<Address> addresses = new LinkedHashSet<>();

    public Cityinfo()
    {
    }

    public Cityinfo(Integer id, String city, String zipcode)
    {
        this.id = id;
        this.city = city;
        this.zipcode = zipcode;
    }

    public Cityinfo(String city, String zipcode)
    {
        this.city = city;
        this.zipcode = zipcode;
    }

    public Cityinfo(String city, String zipcode, Set<Address> addresses)
    {
        this.city = city;
        this.zipcode = zipcode;
        this.addresses = addresses;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getZipcode()
    {
        return zipcode;
    }

    public void setZipcode(String zipcode)
    {
        this.zipcode = zipcode;
    }

    public Set<Address> getAddresses()
    {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses)
    {
        this.addresses = addresses;
    }

    @Override
    public String toString()
    {
        return "Cityinfo{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", addresses=" + addresses +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Cityinfo)) return false;
        Cityinfo cityinfo = (Cityinfo) o;
        return getId().equals(cityinfo.getId()) && getCity().equals(cityinfo.getCity()) && getZipcode().equals(cityinfo.getZipcode());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), getCity(), getZipcode());
    }
}