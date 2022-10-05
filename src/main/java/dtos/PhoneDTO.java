package dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link entities.Phone} entity
 */
public class PhoneDTO implements Serializable
{
    @Size(max = 45)
    private final String phoneNumber;
    @Size(max = 45)
    @NotNull
    private final String description;
    @NotNull
    private final PhoneDTO.PersonInnerDTO idPERSON;

    public PhoneDTO(String phoneNumber, String description, PersonInnerDTO idPERSON)
    {
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.idPERSON = idPERSON;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getDescription()
    {
        return description;
    }

    public PersonInnerDTO getIdPERSON()
    {
        return idPERSON;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneDTO entity = (PhoneDTO) o;
        return Objects.equals(this.phoneNumber, entity.phoneNumber) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.idPERSON, entity.idPERSON);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(phoneNumber, description, idPERSON);
    }

    @Override
    public String toString()
    {
        return getClass().getSimpleName() + "(" +
                "phoneNumber = " + phoneNumber + ", " +
                "description = " + description + ", " +
                "idPERSON = " + idPERSON + ")";
    }

    /**
     * A DTO for the {@link entities.Person} entity
     */
    public static class PersonInnerDTO implements Serializable
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

        public PersonInnerDTO(Integer id, String firstName, String lastName, Integer age, String gender, String email)
        {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.gender = gender;
            this.email = email;
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

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PersonInnerDTO entity = (PersonInnerDTO) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.firstName, entity.firstName) &&
                    Objects.equals(this.lastName, entity.lastName) &&
                    Objects.equals(this.age, entity.age) &&
                    Objects.equals(this.gender, entity.gender) &&
                    Objects.equals(this.email, entity.email);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(id, firstName, lastName, age, gender, email);
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
                    "email = " + email + ")";
        }
    }
}