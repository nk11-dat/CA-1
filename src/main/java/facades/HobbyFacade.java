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
//        if (rm == null)
//            throw new RenameMeNotFoundException("The RenameMe entity with ID: "+id+" Was not found");
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

    public List<Person> getAllPeopleWithHobby(HobbyDTO h)
    {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p join p.hobbies h where h.id = :id", Person.class);
            query.setParameter("id",h.getId());
            List<Person> rms = query.getResultList();
            return rms;
        }finally {
            em.close();
        }
    }



    public List<HobbyDTO> getAllHobbiesDTO(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Hobby> query = em.createQuery("SELECT r FROM Hobby r", Hobby.class);
        List<Hobby> rms = query.getResultList();
        return HobbyDTO.getDTOs(rms);
    }
    public List<Hobby> getAllHobbies(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Hobby> query = em.createQuery("SELECT r FROM Hobby r", Hobby.class);
        List<Hobby> rms = query.getResultList();
        return rms;
    }


    // ???? *løft dit venstre øjenbryn*
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        HobbyFacade fe = getInstance(emf);
        fe.getAllHobbies().forEach(dto->System.out.println(dto));
    }

}
