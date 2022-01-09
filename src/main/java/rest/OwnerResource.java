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
import javax.ws.rs.*;
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
    @Produces(MediaType.APPLICATION_JSON)
    @Path("allbwithh")
    public String allBoatsWithHarbour() {
        return GSON.toJson(FACADE_BOAT.getAllBoatsWithH());
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

    @Path("add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBoat(String a) throws BoatNotFoundException,MissingInputException{
        BoatDTO boatDTO = GSON.fromJson(a,BoatDTO.class);
        BoatDTO reslt = FACADE_BOAT.createBoat(boatDTO.getBrand(),boatDTO.getMake(),boatDTO.getName());
        return Response.ok().entity(GSON.toJson(reslt)).build();
    }
    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBoat(@PathParam("id")long id, String a) throws BoatNotFoundException,MissingInputException
    {
       BoatDTO boatDTO = GSON.fromJson(a,BoatDTO.class);
        boatDTO.setB_id(id);
        BoatDTO result = FACADE_BOAT.editBoat(boatDTO.getBrand(),boatDTO.getMake(),boatDTO.getName());
        return Response.ok().entity(GSON.toJson(result)).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteBoat(@PathParam("id") Integer id) throws BoatNotFoundException {
        BoatDTO result = FACADE_BOAT.deleteBoat(id);
        return Response.ok().entity(GSON.toJson(result)).build();
    }
    

 @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("test")
    public Response Owners() {
     return Response.ok(GSON.toJson(Populator.populate())).build();
    }
}
