package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.BoatDTO;
import exceptions.BoatNotFoundException;
import exceptions.MissingInputException;
import facades.FacadeBoat;
import facades.FacadeOwner;
import facades.Populator;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

//Todo Remove or change relevant parts before ACTUAL use
@Path("harbour")
public class OwnerResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final FacadeOwner FACADE =  FacadeOwner.getFacadeOwner(EMF);
    private static final FacadeBoat FACADE_BOAT =  FacadeBoat.getFacadeBoat(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getOwnerCount() {
       
        long count = FACADE.getOwnerCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allOwners() {
        return GSON.toJson(FACADE.getAll());

    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("allboats")
    public String allBoats() {
        return GSON.toJson(FACADE_BOAT.getAll());
    }


    
    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBoatsByName(@PathParam("name")String  name, String a) throws BoatNotFoundException, MissingInputException
    {
        BoatDTO boatDTO = GSON.fromJson(a,BoatDTO.class);
        if(boatDTO!=null) {
            System.out.println("BoatDTO:" + boatDTO.toString());
        }
        List<BoatDTO> result = FACADE_BOAT.getBoatsByHarbourName(name);
        return Response.ok().entity(GSON.toJson(result)).build();
    }

 @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("test")
    public Response Owners() {
     return Response.ok(GSON.toJson(Populator.populate())).build();
    }
}
