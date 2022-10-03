package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.HobbyDTO;
import dtos.PersonDTO;
import dtos.RenameMeDTO;
import facades.FacadeExample;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

//Todo Remove or change relevant parts before ACTUAL use
@Path("/person")
public class PersonResource
{

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final PersonFacade FACADE = PersonFacade.getInstance(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo()
    {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll()
    {
        List<PersonDTO> personDTOList = FACADE.getAll();
        return GSON.toJson(personDTOList);
    }

    @Path("{phoneNumber}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPersonByPhoneNumber(@PathParam("phoneNumber") String phoneNumber)
    {
        PersonDTO personDTO = FACADE.getPersonByPhone(phoneNumber);
        return GSON.toJson(personDTO);
    }

//    @Path("{zipcode}")
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    public String getAllPersonByZipcode(@PathParam("zipcode") String zipcode)
//    {
//        List<PersonDTO> personDTOList = FACADE.getAllPersonByZipcode(zipcode);
//        PersonDTO personDTO = FACADE.getPersonByPhone(zipcode);
//        return GSON.toJson(personDTO);
//    }


    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount()
    {

        long count = FACADE.getRenameMeCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
    }


//    @POST
//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_JSON})
//    public String createPerson(String input)
//    {
//        PersonDTO rmdto = GSON.fromJson(input, PersonDTO.class);
//        rmdto = FACADE.create(rmdto);
//        System.out.println(rmdto);
//        return GSON.toJson(rmdto);
////        return Response.ok().entity(rmdto).build();
//    }
}
