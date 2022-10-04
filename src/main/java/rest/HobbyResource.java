package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.HobbyDTO;
import dtos.PersonDTO;
import facades.HobbyFacade;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
        return GSON.toJson(allHobbiesDTO);
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


    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String createPerson(String input)
    {
//        innerPersonDTO rmdto = GSON.fromJson(input, innerPersonDTO.class);
//        rmdto = FACADE.create(rmdto);
//        System.out.println(rmdto);
//        return GSON.toJson(rmdto);
////        return Response.ok().entity(rmdto).build();
        return "";
    }
}
