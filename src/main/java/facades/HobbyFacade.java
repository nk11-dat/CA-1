package facades;

import dtos.HobbyDTO;
import dtos.PersonDTO;
import dtos.RenameMeDTO;
import entities.Hobby;
import entities.Person;
import entities.RenameMe;
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
public class HobbyFacade
{

    private static HobbyFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private HobbyFacade() {}


    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static HobbyFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HobbyFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public HobbyDTO create(Hobby hb){
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(hb);
            em.flush();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new HobbyDTO(hb);
    }

    public Hobby create(HobbyDTO hb){
        EntityManager em = getEntityManager();
        Hobby hob = new Hobby(hb.getName(),hb.getWikiLink(),hb.getCategory(),hb.getType());
        try {
            em.getTransaction().begin();
            em.persist(hob);
            em.flush();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return hob;
    }

    public HobbyDTO getHobbyDTOById(Integer id) { //throws RenameMeNotFoundException {
        EntityManager em = emf.createEntityManager();
        Hobby ho = em.find(Hobby.class, id);
        if (ho == null)
            throw new WebApplicationException("The 'Hobby' entity with ID: "+id+" Was not found");
        return new HobbyDTO(ho);
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

    public List<PersonDTO> getAllPeopleWithHobby(String hobbyName)
    {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p join p.hobbies h where h.name = :hobbyName", Person.class);
            query.setParameter("hobbyName", hobbyName);
            List<Person> persons = query.getResultList();
            List<PersonDTO> aaa = PersonDTO.getDTOs(persons);
//            List<innerPersonDTO> listOfPeople = innerPersonDTO.getDTOs(persons);
            return aaa;
        }finally {
            em.close();
        }
    }

    public List<PersonDTO> getAllPeopleWithHobby(HobbyDTO h)
    {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p join p.hobbies h where h.id = :id", Person.class);
            query.setParameter("id",h.getId());
            List<Person> persons = query.getResultList();
            List<PersonDTO> listOfPeople = PersonDTO.getDTOs(persons);
            return listOfPeople;
        }finally {
            em.close();
        }
    }

    public int getPeopleCountWithHobby(HobbyDTO h)
    {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p join p.hobbies h where h.id = :id", Person.class);
            query.setParameter("id",h.getId());
            List<Person> rms = query.getResultList();
            return rms.size();
        }finally {
            em.close();
        }
    }



    public List<HobbyDTO> getAllHobbiesDTO(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Hobby> query = em.createQuery("SELECT r FROM Hobby r", Hobby.class);
        List<Hobby> hobbyList = query.getResultList();
        return HobbyDTO.getDTOs(hobbyList);
    }
    public List<Hobby> getAllHobbies(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Hobby> query = em.createQuery("SELECT r FROM Hobby r", Hobby.class);
        List<Hobby> rms = query.getResultList();
        return rms;
    }

    public Hobby deleteHobbyById(int i)
    {
        EntityManager em = emf.createEntityManager();
        try{

        Hobby hob = em.find(Hobby.class,i);
        em.getTransaction().begin();
        em.remove(hob);
        em.flush();
        em.getTransaction().commit();
        return hob;

        } finally {
            em.close();
        }

    }

    public HobbyDTO editHobbyDTO(HobbyDTO hob)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();

            Hobby h = em.find(Hobby.class,hob.getId());

            if (h == null)
            {
                throw new WebApplicationException ("The 'Hobby' entity with ID: "+hob.getId()+" Was not found");
            }
          //  h.setAddresses(); ikke angivet i DTO
            h.setName(hob.getName());
            h.setWikiLink(hob.getWikiLink());
            h.setCategory(hob.getCategory());
            h.setType(hob.getType());

            em.flush();
            em.getTransaction().commit();
            return new HobbyDTO(h);

        } finally {
            em.close();
        }

    }



    // ???? *løft dit venstre øjenbryn*
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        HobbyFacade fe = getInstance(emf);
        fe.getAllHobbies().forEach(dto->System.out.println(dto));
    }


    public List<HobbyDTO> getHobbyDTOByName(String hobbyName)
    {
        EntityManager em = emf.createEntityManager();
        TypedQuery<HobbyDTO> query = em.createQuery("select h from Hobby h where h.name like :hobbyName", HobbyDTO.class);
//        query.setParameter("hobbyName", hobbyName);
        query.setParameter("hobbyName", "%" + hobbyName + "%");
        List<HobbyDTO> listFoundHobbies = query.getResultList();
        if (listFoundHobbies == null)
            throw new WebApplicationException("Couldn't find any hobby matching the name: " + hobbyName);
        return listFoundHobbies;
    }
}
