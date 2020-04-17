package edu.upc.dsa.services;

import edu.upc.dsa.Covid19Manager;
import edu.upc.dsa.Covid19ManagerImpl;
import edu.upc.dsa.models.Brote;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/covid19", description = "Endpoint to Covid19 Service")
@Path("/covid19")
public class Covid19Service {

    private Covid19Manager cm;

    public Covid19Service() {
        this.cm = Covid19ManagerImpl.getInstance();
        if (cm.sizeBrotes() == 0) {
            this.cm.addBrote("1");
            this.cm.addBrote("2");
        }
        if(cm.sizeCasos() == 0){
            this.cm.createCaso("01","Ivan","Requena", "16/05/1997", "17/04/2020",
            "medio", "Hombre", "ivan.requena@estudiantat.upc.edu", "625427213", "Sant Boi", "Sospechoso");
            this.cm.createCaso("02","Toni","Oller", "10/03/1967", "12/04/2020",
            "alto", "Hombre", "toni.oller@estudiantat.upc.edu", "655527213", "BCN", "No caso");
        }
    }

    //Get lista brotes
    @GET
    @ApiOperation(value = "Get all brotes", notes = "Devuelve una lista con todos los brotes")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Brote.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @Path("/listUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBrotes() {

        List<Brote> brote = this.cm.mostrarBrotes();

        GenericEntity<List<Brote>> entity = new GenericEntity<List<Brote>>(brote) {};
        return Response.status(201).entity(entity).build()  ;
    }

    //Añadir nuevo brote
    @POST
    @ApiOperation(value = "create a new Brote", notes = "Añade un brote a la lista de brotes")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Brote.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/addBrote/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newBrote(@PathParam("id") String id) {
        if (id.isEmpty())  return Response.status(500).entity(new Brote()).build();
        this.cm.addBrote(id);
        return Response.status(201).entity(cm.getBrote(id)).build();
    }
}