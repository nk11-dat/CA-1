package facades;

import dtos.PersonDTO;
import entities.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 * 2nd commit
 */
public class PersonFacade
{

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
//    public innerPersonDTO create(innerPersonDTO personDTO){
//
//        Person person = new Person(new Address(personDTO.getAddress().getStreet(), personDTO.getAddress().getAditionalInfo(), new Cityinfo(personDTO.getAddress().getIdCITY().getCity(), personDTO.getAddress().getIdCITY().getZipcode())), personDTO.getFirstName(), personDTO.getLastName(), personDTO.getAge(),personDTO.getGender(),personDTO.getEmail());
//
//        EntityManager em = getEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.persist(person);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//        return new innerPersonDTO(person);
//    }

    public PersonDTO create(Person person){

//        Person person = new Person(new Address(personDTO.getAddress().getStreet(), personDTO.getAddress().getAditionalInfo(), new Cityinfo(personDTO.getAddress().getIdCITY().getCity(), personDTO.getAddress().getIdCITY().getZipcode())), personDTO.getFirstName(), personDTO.getLastName(), personDTO.getAge(),personDTO.getGender(),personDTO.getEmail());

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.flush(); //Behandel JPA som et offenligt toilet
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(person);
    }

    public Person addHobby(Integer personId, Integer hobbyId) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Person person = em.find(Person.class, personId);
            if (person == null)
                throw new WebApplicationException("Person doesn't exist with id= " + personId);
            Hobby hobby = em.find(Hobby.class, hobbyId);
            if (hobby == null)
                throw new WebApplicationException("Hobby doesn't exist with id= this is hardcoded....!¿?" + personId);
            // new Hobby(hobbyDTO.getName(),hobbyDTO.getWikiLink(), hobbyDTO.getCategory(), hobbyDTO.getType());
            person.addHobby(hobby);
            em.getTransaction().commit();
            return person;
        } finally {
            em.close();
        }
    }

    public Person addAndCreatePhone(Integer personId, Phone phone) { //TODO:OBS, den skulle måske retunere en DTO...
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Person person = em.find(Person.class, personId);
            if (person == null)
                throw new WebApplicationException("Person doesn't exist with id= " + personId);
            Phone p = em.find(Phone.class, phone.getPhoneNumber());//new Hobby(hobbyDTO.getName(),hobbyDTO.getWikiLink(), hobbyDTO.getCategory(), hobbyDTO.getType());
            if (p != null)
                throw new WebApplicationException("PhoneNumber already exist = " + p.getPhoneNumber());
            person.addPhone(phone);
            em.flush();
            em.getTransaction().commit();
            return person;
        } finally {
            em.close();
        }
    }

    public PersonDTO getPersonById(Integer id) { //throws RenameMeNotFoundException {
        EntityManager em = emf.createEntityManager();
        Person p = em.find(Person.class, id);
        if (p == null)
            throw new WebApplicationException("Person doesn't exist with id= " + id);
        return new PersonDTO(p);
    }

    public PersonDTO getPersonByPhone(String phoneNumber) { //throws RenameMeNotFoundException {
        EntityManager em = emf.createEntityManager();
        try
        {
            TypedQuery <Person> query = em.createQuery("SELECT p FROM Person p JOIN p.phones ph WHERE ph.phoneNumber = :phoneNumber", Person.class);
            query.setParameter("phoneNumber", phoneNumber);
            Person person = query.getSingleResult();
            if (person == null)
            {
                throw new WebApplicationException("Person with phone number = " + phoneNumber + " does not exist");
            }
            return new PersonDTO(person);
        } finally
        {
            em.close();
        }
    }
    
    //TODO Remove/Change this before use
    public long getRenameMeCount(){
        EntityManager em = getEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM RenameMe r").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
    }
    
    public List<PersonDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT r FROM Person r", Person.class);
        List<Person> rms = query.getResultList();
        return PersonDTO.getDTOs(rms);
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade fe = getInstance(emf);
        fe.getAll().forEach(dto->System.out.println(dto));
    }

    public List<PersonDTO> getAllPersonByZipcode(String zipcode)
    {
        EntityManager em = getEntityManager();
        try
        {
            TypedQuery<Person> query = em.createQuery("select p from Person p join p.idADDRESS ad join ad.idCITY cit where cit.zipcode = :zipcode", Person.class);
            query.setParameter("zipcode", zipcode);
            List<Person> personList = query.getResultList();
            return PersonDTO.getDTOs(personList);
        }
        finally
        {
            em.close();
        }
    }

    public PersonDTO editPerson(PersonDTO p) {
        EntityManager em = getEntityManager();
        try{
            Person person = em.find(Person.class, p.getId());
            if (person == null)
                throw new WebApplicationException("Person with id: " + p.getId() + " dosesn't exist.");
            em.getTransaction().begin();
            Cityinfo cityinfo = new Cityinfo(p.getAddress().getIdCITY().getCity(), p.getAddress().getIdCITY().getZipcode());
            Address address = new Address(p.getAddress().getStreet(), p.getAddress().getAditionalInfo(), cityinfo);
            person.setAddress(address);
            person.setFirstName(p.getFirstName());
            person.setLastName(p.getLastName());
            person.setAge(p.getAge());
            person.setGender(p.getGender());
            person.setEmail(p.getEmail());
            Set<Phone> phoneSet = new HashSet<>();
            p.getPhones().forEach(phoneDTO -> {
                phoneSet.add(new Phone(phoneDTO.getPhoneNumber(), phoneDTO.getDescription()));
            });
            person.setPhones(phoneSet);
            Set<Hobby> hobbies = new HashSet<>();
            p.getHobbies().forEach(hobbyDTO -> {
                hobbies.add(new Hobby(hobbyDTO.getName(), hobbyDTO.getWikiLink(), hobbyDTO.getCategory(), hobbyDTO.getType()));
            });
            person.setHobbies(hobbies);
            em.flush();
            em.getTransaction().commit();
            return new PersonDTO(person);
        }
        finally {
            em.close();
        }
    }

    public PersonDTO deletePerson(int id) {
        EntityManager em = getEntityManager();
        try{
            Person person = em.find(Person.class, id);
            if (person == null)
                throw new WebApplicationException("Person with id: " + id + " dosesn't exist.");
            em.getTransaction().begin();
            em.remove(person);
            em.getTransaction().commit();
            return new PersonDTO(person);
        }
        finally {
            em.close();
        }
    }

}
