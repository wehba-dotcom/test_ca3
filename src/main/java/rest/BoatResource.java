package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.OwnerDTO;
import exceptions.BoatNotFoundException;
import exceptions.MissingInputException;
import facades.FacadeBoat;
import facades.FacadeExample;
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
@Path("boat")
public class BoatResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final FacadeBoat FACADE =  FacadeBoat.getFacadeBoat(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
       
        long count = FACADE.getBoatCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOwnersByName(@PathParam("name")String  name, String a) throws BoatNotFoundException, MissingInputException
    {
        OwnerDTO ownerDTO = GSON.fromJson(a,OwnerDTO.class);
        if(ownerDTO!=null) {
            System.out.println("OwnerDTO:" + ownerDTO.toString());
        }
        List<OwnerDTO> result = FACADE.getOwnersByBoatName(name);
        return Response.ok().entity(GSON.toJson(result)).build();
    }


}
