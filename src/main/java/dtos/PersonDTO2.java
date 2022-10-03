package dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link entities.Person} entity
 */
public class PersonDTO2 implements Serializable
{
    private final Integer id;
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
    private final Set<PhoneInnerDTO> phones;
    private final Set<HobbyInnerDTO> hobbies;
    @NotNull
    private final AddressInnerDTO idADDRESS;

    public PersonDTO2(Integer id, String firstName, String lastName, Integer age, String gender, String email, Set<PhoneInnerDTO> phones, Set<HobbyInnerDTO> hobbies, AddressInnerDTO idADDRESS)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phones = phones;
        this.hobbies = hobbies;
        this.idADDRESS = idADDRESS;
    }

    public Integer getId()
    {
        return id;
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

    public Set<PhoneInnerDTO> getPhones()
    {
        return phones;
    }

    public Set<HobbyInnerDTO> getHobbies()
    {
        return hobbies;
    }

    public AddressInnerDTO getIdADDRESS()
    {
        return idADDRESS;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO2 entity = (PersonDTO2) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.firstName, entity.firstName) &&
                Objects.equals(this.lastName, entity.lastName) &&
                Objects.equals(this.age, entity.age) &&
                Objects.equals(this.gender, entity.gender) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.phones, entity.phones) &&
                Objects.equals(this.hobbies, entity.hobbies) &&
                Objects.equals(this.idADDRESS, entity.idADDRESS);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, firstName, lastName, age, gender, email, phones, hobbies, idADDRESS);
    }

    @Override
    public String toString()
    {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ", " +
                "age = " + age + ", " +
                "gender = " + gender + ", " +
                "email = " + email + ", " +
                "phones = " + phones + ", " +
                "hobbies = " + hobbies + ", " +
                "idADDRESS = " + idADDRESS + ")";
    }

    /**
     * A DTO for the {@link entities.Phone} entity
     */
    public static class PhoneInnerDTO implements Serializable
    {
        @Size(max = 45)
        private final String phoneNumber;
        @Size(max = 45)
        @NotNull
        private final String description;

        public PhoneInnerDTO(String phoneNumber, String description)
        {
            this.phoneNumber = phoneNumber;
            this.description = description;
        }

        public String getPhoneNumber()
        {
            return phoneNumber;
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
            PhoneInnerDTO entity = (PhoneInnerDTO) o;
            return Objects.equals(this.phoneNumber, entity.phoneNumber) &&
                    Objects.equals(this.description, entity.description);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(phoneNumber, description);
        }

        @Override
        public String toString()
        {
            return getClass().getSimpleName() + "(" +
                    "phoneNumber = " + phoneNumber + ", " +
                    "description = " + description + ")";
        }
    }

    /**
     * A DTO for the {@link entities.Hobby} entity
     */
    public static class HobbyInnerDTO implements Serializable
    {
        private final Integer id;
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

        public HobbyInnerDTO(Integer id, String name, String wikiLink, String category, String type)
        {
            this.id = id;
            this.name = name;
            this.wikiLink = wikiLink;
            this.category = category;
            this.type = type;
        }

        public Integer getId()
        {
            return id;
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
            HobbyInnerDTO entity = (HobbyInnerDTO) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.name, entity.name) &&
                    Objects.equals(this.wikiLink, entity.wikiLink) &&
                    Objects.equals(this.category, entity.category) &&
                    Objects.equals(this.type, entity.type);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(id, name, wikiLink, category, type);
        }

        @Override
        public String toString()
        {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "name = " + name + ", " +
                    "wikiLink = " + wikiLink + ", " +
                    "category = " + category + ", " +
                    "type = " + type + ")";
        }
    }

    /**
     * A DTO for the {@link entities.Address} entity
     */
    public static class AddressInnerDTO implements Serializable
    {
        private final Integer id;
        @Size(max = 45)
        @NotNull
        private final String street;
        @Size(max = 45)
        private final String aditionalInfo;

        public AddressInnerDTO(Integer id, String street, String aditionalInfo)
        {
            this.id = id;
            this.street = street;
            this.aditionalInfo = aditionalInfo;
        }

        public Integer getId()
        {
            return id;
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
            AddressInnerDTO entity = (AddressInnerDTO) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.street, entity.street) &&
                    Objects.equals(this.aditionalInfo, entity.aditionalInfo);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(id, street, aditionalInfo);
        }

        @Override
        public String toString()
        {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "street = " + street + ", " +
                    "aditionalInfo = " + aditionalInfo + ")";
        }
    }
}