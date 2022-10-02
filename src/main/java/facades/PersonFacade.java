package facades;

import dtos.PersonDTO;
import entities.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import java.util.List;

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
    
    public PersonDTO create(PersonDTO personDTO){

        Person person = new Person(new Address(personDTO.getAddress().getStreet(), personDTO.getAddress().getAditionalInfo(), new Cityinfo(personDTO.getAddress().getIdCITY().getCity(), personDTO.getAddress().getIdCITY().getZipcode())), personDTO.getFirstName(), personDTO.getLastName(), personDTO.getAge(),personDTO.getGender(),personDTO.getEmail());

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(person);
    }

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

}
