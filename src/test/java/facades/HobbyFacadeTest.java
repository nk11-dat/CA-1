package facades;

import dtos.HobbyDTO;
import dtos.PersonDTO;
import entities.*;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
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
            Address a1 = new Address("Testgade 16", "Tv.", ci);
            Address a2 = new Address("Testgade 17", "basement", ci);
            Person p1 = new Person(a1,"Frida","Fridason",27,"Female","Frida@Fridason.dk");
            Person p2 = new Person(a1,"Gunter","Gunterson",33,"Male","Gunter@Gunterson.no");

            Hobby h1 = new Hobby("3D-udskrivning", "https://en.wikipedia.org/wiki/3D_printing", "Generel", "Indendørs");
            Hobby h2 =new Hobby("Akrobatik", "https://en.wikipedia.org/wiki/Acrobatics", "Generel", "Indendørs");
            Hobby h3 = new Hobby("Skuespil", "https://en.wikipedia.org/wiki/Acting", "Generel", "Indendørs");
            p1.addHobby(h1);
            p1.addHobby(h2);
            p2.addHobby(h1);
            p2.addHobby(h3);

            em.persist(h1);
            em.persist(h2);
            em.persist(h3);
            em.persist(p1);
            em.persist(p2);

            em.flush();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }



    @Test
    void create()
    {
        HobbyDTO h4 = facade.create(new Hobby("Neckbearding", "https://en.wikipedia.org/wiki/yourmum", "Lonelymen", "basement"));
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Hobby found = em.find(Hobby.class, 4);
            HobbyDTO actual = new HobbyDTO(found);
            assertEquals(h4, actual);
        }
        finally {
            em.close();
        }
//        List<HobbyDTO> actual = facade.getAllEmployees();
//        assertEquals(4, actual.size());
//        assertThat(actual, containsInAnyOrder(e1, e2, e3, h4));
    }

    @Test
    void getHobbyDTOById()
    {
    }
}