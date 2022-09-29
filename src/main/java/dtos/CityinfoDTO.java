package dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link entities.Cityinfo} entity
 */
public class CityinfoDTO implements Serializable
{
    @Size(max = 45)
    @NotNull
    private final String city;
    @Size(max = 45)
    @NotNull
    private final String zipcode;
    private Set<AddressDTO> addresses;

    public CityinfoDTO(String city, String zipcode, Set<AddressDTO> addresses)
    {
        this.city = city;
        this.zipcode = zipcode;
        this.addresses = addresses;
    }

    public CityinfoDTO(String city, String zipcode)
    {
        this.city = city;
        this.zipcode = zipcode;
    }

    public String getCity()
    {
        return city;
    }

    public String getZipcode()
    {
        return zipcode;
    }

    public Set<AddressDTO> getAddresses()
    {
        return addresses;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityinfoDTO entity = (CityinfoDTO) o;
        return Objects.equals(this.city, entity.city) &&
                Objects.equals(this.zipcode, entity.zipcode) &&
                Objects.equals(this.addresses, entity.addresses);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(city, zipcode, addresses);
    }

    @Override
    public String toString()
    {
        return getClass().getSimpleName() + "(" +
                "city = " + city + ", " +
                "zipcode = " + zipcode + ", " +
                "addresses = " + addresses + ")";
    }

    /**
     * A DTO for the {@link entities.Address} entity
     */
    public static class AddressDTO implements Serializable
    {
        @Size(max = 45)
        @NotNull
        private final String street;
        @Size(max = 45)
        private final String aditionalInfo;

        public AddressDTO(String street, String aditionalInfo)
        {
            this.street = street;
            this.aditionalInfo = aditionalInfo;
        }

        public String getStreet()
        {
            return street;
        }

        public String getAditionalInfo()
        {
            return aditionalInfo;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AddressDTO entity = (AddressDTO) o;
            return Objects.equals(this.street, entity.street) &&
                    Objects.equals(this.aditionalInfo, entity.aditionalInfo);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(street, aditionalInfo);
        }

        @Override
        public String toString()
        {
            return getClass().getSimpleName() + "(" +
                    "street = " + street + ", " +
                    "aditionalInfo = " + aditionalInfo + ")";
        }
    }
}