package dtos;

import entities.Person;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link entities.Hobby} entity
 */
public class HobbyDTO implements Serializable
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
    private final Set<PersonDTO> people;
    private final Set<AddressDTO> addresses;

    public HobbyDTO(String name, String wikiLink, String category, String type, Set<PersonDTO> people, Set<AddressDTO> addresses)
    {
        this.name = name;
        this.wikiLink = wikiLink;
        this.category = category;
        this.type = type;
        this.people = people;
        this.addresses = addresses;
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

    public Set<PersonDTO> getPeople()
    {
        return people;
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
        HobbyDTO entity = (HobbyDTO) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.wikiLink, entity.wikiLink) &&
                Objects.equals(this.category, entity.category) &&
                Objects.equals(this.type, entity.type) &&
                Objects.equals(this.people, entity.people) &&
                Objects.equals(this.addresses, entity.addresses);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, wikiLink, category, type, people, addresses);
    }

    @Override
    public String toString()
    {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "wikiLink = " + wikiLink + ", " +
                "category = " + category + ", " +
                "type = " + type + ", " +
                "people = " + people + ", " +
                "addresses = " + addresses + ")";
    }

    /**
     * A DTO for the {@link Person} entity
     */
    public static class PersonDTO implements Serializable
    {
        @Size(max = 45)
        @NotNull
        private final String firstName;
        @Size(max = 45)
        @NotNull
        private final String lastName;
        @NotNull
        private final Person age;
        @Size(max = 45)
        @NotNull
        private final String gender;
        @Size(max = 45)
        @NotNull
        private final String email;

        public PersonDTO(String firstName, String lastName, Person age, String gender, String email)
        {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.gender = gender;
            this.email = email;
        }

        public String getFirstName()
        {
            return firstName;
        }

        public String getLastName()
        {
            return lastName;
        }

        public Person getAge()
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

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PersonDTO entity = (PersonDTO) o;
            return Objects.equals(this.firstName, entity.firstName) &&
                    Objects.equals(this.lastName, entity.lastName) &&
                    Objects.equals(this.age, entity.age) &&
                    Objects.equals(this.gender, entity.gender) &&
                    Objects.equals(this.email, entity.email);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(firstName, lastName, age, gender, email);
        }

        @Override
        public String toString()
        {
            return getClass().getSimpleName() + "(" +
                    "firstName = " + firstName + ", " +
                    "lastName = " + lastName + ", " +
                    "age = " + age + ", " +
                    "gender = " + gender + ", " +
                    "email = " + email + ")";
        }
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
}