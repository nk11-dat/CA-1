package facades;

import dtos.HobbyDTO;
import dtos.PersonDTO;
import entities.*;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

class HobbyFacadeTest
{
    private static EntityManagerFactory emf;
    private static HobbyFacade facade;
    Hobby h1, h2, h3;
    Person p1, p2;

    public HobbyFacadeTest()
    {
    }

    @BeforeAll
    public static void setUpClass()
    {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = HobbyFacade.getInstance(emf);
    }

    @AfterAll
    public static void tearDownClass()
    {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp()
    {
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
//            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
//            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
//            em.createNamedQuery("Cityinfo.deleteAllRows").executeUpdate();
//
//            Cityinfo ci1 = new Cityinfo(33, "IbBy", "6969");
//            Cityinfo ci2 = new Cityinfo(34, "IbBy", "6969");
//            Address a1 = new Address("Testgade 16", "Tv.", ci1);
//            Address a2 = new Address("Testgade 17", "basement", ci2);
//            p1 = new Person(a1, "Frida", "Fridason", 27, "Female", "Frida@Fridason.dk");
//            p2 = new Person(a2, "Gunter", "Gunterson", 33, "Male", "Gunter@Gunterson.no");
//
//            h1 = new Hobby("3D-udskrivning", "https://en.wikipedia.org/wiki/3D_printing", "Generel", "Indendørs");
//            h2 = new Hobby("Akrobatik", "https://en.wikipedia.org/wiki/Acrobatics", "Generel", "Indendørs");
//            h3 = new Hobby("Skuespil", "https://en.wikipedia.org/wiki/Acting", "Generel", "Indendørs");
//            p1.addHobby(h1);
//            p1.addHobby(h2);
//            p2.addHobby(h1);
//            p2.addHobby(h3);
//
//            em.persist(h1);
//            em.persist(h2);
//            em.persist(h3);
//            em.persist(p1);
//            em.persist(p2);
//
//            em.flush();
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
    }


    @Test
    void create()
    {
//        HobbyDTO h4 = facade.create(new Hobby("Neckbearding", "https://en.wikipedia.org/wiki/yourmum", "Lonelymen", "basement"));
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            Hobby found = em.find(Hobby.class, h4.getId());
//            HobbyDTO actual1 = new HobbyDTO(found);
//            assertEquals(h4, actual1);
//
//            List<Hobby> actual2 = facade.getAllHobbies();
//            assertEquals(4, actual2.size());
//            assertThat(actual2, containsInAnyOrder(h1, h2, h3, found));
//        } finally {
//            em.close();
//        }
    }

    @Test
    void getAllPeopleWithHobby()
    {
//        List<PersonDTO> actual = facade.getAllPeopleWithHobby(new HobbyDTO(h1));
//        assertEquals(2, actual.size());
//        assertThat(actual, containsInAnyOrder(new PersonDTO(p1), new PersonDTO(p2)));
    }

    @Test
    void getPeopleCountWithHobby()
    {
//        int actual = facade.getPeopleCountWithHobby(new HobbyDTO(h1));
//        assertEquals(2, actual);
    }

    @Test
    void getHobbyDTOById()
    {
    }

    @Test
    void deleteHobby()
    {
//        Hobby hob = facade.deleteHobbyById(h2.getId());
//        List<HobbyDTO> hoblist = facade.getAllHobbiesDTO();
//        assertEquals(2, hoblist.size());
////        HobbyDTO gone = facade.getHobbyDTOById(2);
////        assertNull(gone);
//        assertThrows(WebApplicationException.class, () -> facade.getHobbyDTOById(hob.getId()));
//
//        WebApplicationException thrown = Assertions.assertThrows(WebApplicationException.class, () -> {
//            facade.getHobbyDTOById(hob.getId());
//        }, "The 'Hobby' entity with ID: "+hob.getId()+" Was not found");
//
//        Assertions.assertEquals("The 'Hobby' entity with ID: " + hob.getId() + " Was not found", thrown.getMessage());
    }

    @Test
    void editHobby()
    {
//        HobbyDTO before = facade.getHobbyDTOById(3);
//        HobbyDTO expected = new HobbyDTO(3, "Tanks", "https://en.wikipedia.org/wiki/yourmumisatank", "praised", "openFields");
//        HobbyDTO after = facade.editHobbyDTO(expected);
//
//        assertNotEquals(before, after);
//        assertEquals(expected, after);
//
//        assertEquals(after, facade.getHobbyDTOById(3));

    }
}