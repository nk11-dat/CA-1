package dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link entities.Person} entity
 */
public class PersonDTO implements Serializable
{
    private final AddressDTO address;
    @Size(max = 45)
    @NotNull
    private final String firstName;
    @Size(max = 45)
    @NotNull
    private final String lastName;
    @NotNull
    private final Integer age;
    @Size(max = 45)
    @NotNull
    private final String gender;
    @Size(max = 45)
    @NotNull
    private final String email;
    private final Set<PhoneDTO> phones;
    private final Set<HobbyDTO> hobbies;

    public PersonDTO(AddressDTO address, String firstName, String lastName, Integer age, String gender, String email, Set<PhoneDTO> phones, Set<HobbyDTO> hobbies)
    {
        this.address = address;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phones = phones;
        this.hobbies = hobbies;
    }

    public AddressDTO getAddress()
    {
        return address;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public Integer getAge()
    {
        return age;
    }

    public String getGender()
    {
        return gender;
    }

    public String getEmail()
    {
        return email;
    }

    public Set<PhoneDTO> getPhones()
    {
        return phones;
    }

    public Set<HobbyDTO> getHobbies()
    {
        return hobbies;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO entity = (PersonDTO) o;
        return Objects.equals(this.address, entity.address) &&
                Objects.equals(this.firstName, entity.firstName) &&
                Objects.equals(this.lastName, entity.lastName) &&
                Objects.equals(this.age, entity.age) &&
                Objects.equals(this.gender, entity.gender) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.phones, entity.phones) &&
                Objects.equals(this.hobbies, entity.hobbies);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(address, firstName, lastName, age, gender, email, phones, hobbies);
    }

    @Override
    public String toString()
    {
        return getClass().getSimpleName() + "(" +
                "address = " + address + ", " +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ", " +
                "age = " + age + ", " +
                "gender = " + gender + ", " +
                "email = " + email + ", " +
                "phones = " + phones + ", " +
                "hobbies = " + hobbies + ")";
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
        @NotNull
        private final CityinfoDTO idCITY;

        public AddressDTO(String street, String aditionalInfo, CityinfoDTO idCITY)
        {
            this.street = street;
            this.aditionalInfo = aditionalInfo;
            this.idCITY = idCITY;
        }

        public String getStreet()
        {
            return street;
        }

        public String getAditionalInfo()
        {
            return aditionalInfo;
        }

        public CityinfoDTO getIdCITY()
        {
            return idCITY;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AddressDTO entity = (AddressDTO) o;
            return Objects.equals(this.street, entity.street) &&
                    Objects.equals(this.aditionalInfo, entity.aditionalInfo) &&
                    Objects.equals(this.idCITY, entity.idCITY);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(street, aditionalInfo, idCITY);
        }

        @Override
        public String toString()
        {
            return getClass().getSimpleName() + "(" +
                    "street = " + street + ", " +
                    "aditionalInfo = " + aditionalInfo + ", " +
                    "idCITY = " + idCITY + ")";
        }

        /**
         * A DTO for the {@link entities.Cityinfo} entity
         */
        public static class CityinfoDTO implements Serializable
        {
            @Size(max = 45)
            @NotNull
            private final String city;
            @Size(max = 45)
            @NotNull
            private final String zipcode;

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

            @Override
            public boolean equals(Object o)
            {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                CityinfoDTO entity = (CityinfoDTO) o;
                return Objects.equals(this.city, entity.city) &&
                        Objects.equals(this.zipcode, entity.zipcode);
            }

            @Override
            public int hashCode()
            {
                return Objects.hash(city, zipcode);
            }

            @Override
            public String toString()
            {
                return getClass().getSimpleName() + "(" +
                        "city = " + city + ", " +
                        "zipcode = " + zipcode + ")";
            }
        }
    }

    /**
     * A DTO for the {@link entities.Phone} entity
     */
    public static class PhoneDTO implements Serializable
    {
        @Size(max = 45)
        private final String id;
        @Size(max = 45)
        @NotNull
        private final String description;

        public PhoneDTO(String id, String description)
        {
            this.id = id;
            this.description = description;
        }

        public String getId()
        {
            return id;
        }

        public String getDescription()
        {
            return description;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PhoneDTO entity = (PhoneDTO) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.description, entity.description);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(id, description);
        }

        @Override
        public String toString()
        {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "description = " + description + ")";
        }
    }

    /**
     * A DTO for the {@link entities.Hobby} entity
     */
    public static class HobbyDTO implements Serializable
    {
        @Size(max = 45)
        @NotNull
        private final String name;
        @Size(max = 200)
        @NotNull
        private final String wikiLink;
        @Size(max = 45)
        @NotNull
        private final String category;
        @Size(max = 45)
        @NotNull
        private final String type;

        public HobbyDTO(String name, String wikiLink, String category, String type)
        {
            this.name = name;
            this.wikiLink = wikiLink;
            this.category = category;
            this.type = type;
        }

        public String getName()
        {
            return name;
        }

        public String getWikiLink()
        {
            return wikiLink;
        }

        public String getCategory()
        {
            return category;
        }

        public String getType()
        {
            return type;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            HobbyDTO entity = (HobbyDTO) o;
            return Objects.equals(this.name, entity.name) &&
                    Objects.equals(this.wikiLink, entity.wikiLink) &&
                    Objects.equals(this.category, entity.category) &&
                    Objects.equals(this.type, entity.type);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(name, wikiLink, category, type);
        }

        @Override
        public String toString()
        {
            return getClass().getSimpleName() + "(" +
                    "name = " + name + ", " +
                    "wikiLink = " + wikiLink + ", " +
                    "category = " + category + ", " +
                    "type = " + type + ")";
        }
    }
}