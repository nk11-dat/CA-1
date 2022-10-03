package facades;

import entities.*;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;

class HobbyFacadeTest
{
    private static EntityManagerFactory emf;
    private static HobbyFacade facade;

    public HobbyFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = HobbyFacade.getInstance(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();

            Cityinfo ci = new Cityinfo("IbBy", "6969");
            Address a = new Address("Testgade 16", "Tv.", ci);
            Person p = new Person(a,"Frida","Fridason",27,"Female","Frida@Fridason.dk");

            Hobby h1 = new Hobby("3D-udskrivning", "https://en.wikipedia.org/wiki/3D_printing", "Generel", "Indendørs");
            Hobby h2 =new Hobby("Akrobatik", "https://en.wikipedia.org/wiki/Acrobatics", "Generel", "Indendørs");
            Hobby h3 = new Hobby("Skuespil", "https://en.wikipedia.org/wiki/Acting", "Generel", "Indendørs");
            p.addHobby(h1);

            em.persist(h1);
            em.persist(h2);
            em.persist(h3);
            em.persist(p);

            em.flush();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }



    @Test
    void create()
    {
        
    }

    @Test
    void testCreate()
    {
    }

    @Test
    void getHobbyDTOById()
    {
    }
}