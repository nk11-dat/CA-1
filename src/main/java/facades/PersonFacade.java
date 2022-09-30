package facades;

import dtos.PersonDTO;
import dtos.RenameMeDTO;
import entities.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.LinkedHashSet;
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
    
    public PersonDTO create(PersonDTO rm){

//        Set<Phone> tempPhones = new LinkedHashSet<>();
//
//        Set<Hobby> tempHobbies = new LinkedHashSet<>();


        Person person = new Person(new Address(rm.getAddress().getStreet(), rm.getAddress().getAditionalInfo(), new Cityinfo(rm.getAddress().getIdCITY().getCity(), rm.getAddress().getIdCITY().getZipcode())), rm.getFirstName(), rm.getLastName(), rm.getAge(),rm.getGender(),rm.getEmail());
        for (PersonDTO.PhoneDTO phone : rm.getPhones()) {
            person.addPhone(new Phone(phone.getPhoneNumber(), phone.getDescription()));
        }
        for (PersonDTO.HobbyDTO hobby : rm.getHobbies()) {
            person.addHobby(new Hobby(hobby.getName(), hobby.getWikiLink(), hobby.getCategory(), hobby.getType()));
        }
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(person);
            for (PersonDTO.HobbyDTO hobby : rm.getHobbies()) {
                em.persist(new Hobby(hobby.getName(), hobby.getWikiLink(), hobby.getCategory(), hobby.getType()));
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(person);
    }

    public RenameMeDTO getById(long id) { //throws RenameMeNotFoundException {
        EntityManager em = emf.createEntityManager();
        RenameMe rm = em.find(RenameMe.class, id);
//        if (rm == null)
//            throw new RenameMeNotFoundException("The RenameMe entity with ID: "+id+" Was not found");
        return new RenameMeDTO(rm);
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
