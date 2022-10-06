package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.HobbyDTO;
import dtos.PersonDTO;
import facades.HobbyFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

//Todo Remove or change relevant parts before ACTUAL use
@Path("/hobbies")
public class HobbyResource
{

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final HobbyFacade FACADE = HobbyFacade.getInstance(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo()
    {
        return "{\"msg\":\"Hello Dankness my old friend...\"}";
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll()
    {
        List<HobbyDTO> allHobbiesDTO = FACADE.getAllHobbiesDTO();
        return GSON.toJson(allHobbiesDTO);
    }

    @Path("{hobbyname}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll(@PathParam("hobbyname") String hobbyname)
    {
        List<HobbyDTO> allHobbiesDTO = FACADE.getHobbyDTOByName(hobbyname);
//        Teststuff allDaStuff = new Teststuff(allHobbiesDTO);
//        String temp = GSON.toJson(allDaStuff);
//        return temp;
        return GSON.toJson(allHobbiesDTO);
    }

    public class Teststuff
    {
        List<HobbyDTO> all = new ArrayList<>();

        public Teststuff(List<HobbyDTO> all)
        {
            this.all = all;
        }
    }

    @Path("people/{hobbyname}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPeopleWithHobby(@PathParam("hobbyname") String hobbyname)
    {
        List<PersonDTO> people = FACADE.getAllPeopleWithHobby(hobbyname);
//        long count = FACADE.getRenameMeCount();
        //System.out.println("--------------->"+count);
        return GSON.toJson(people);
    }

    @Path("people/count/{hobbyname}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPeopleCountWithHobby(@PathParam("hobbyname") String hobbyname)
    {
        List<PersonDTO> people = FACADE.getAllPeopleWithHobby(hobbyname);
//        long count = FACADE.getRenameMeCount();
        //System.out.println("--------------->"+count);
        return GSON.toJson(people.size());
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String createHobby(String input)
    {
        HobbyDTO hob = GSON.fromJson(input,HobbyDTO.class);
        FACADE.create(hob);

        return GSON.toJson(hob);
    }

    @PUT
    @Path("edit/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String editHobby(@PathParam("id") int id, String input)
    {
        HobbyDTO hob = GSON.fromJson(input,HobbyDTO.class);
        hob.setId(id);
        FACADE.editHobbyDTO(hob);

        return GSON.toJson(hob);
    }

    @DELETE
    @Path("delete/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String deleteHobby(@PathParam("id") int id)
    {
        HobbyDTO hob = new HobbyDTO(FACADE.deleteHobbyById(id));

        return GSON.toJson(hob);
    }
}
