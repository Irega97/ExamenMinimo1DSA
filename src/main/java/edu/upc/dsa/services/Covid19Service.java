package edu.upc.dsa.services;

import edu.upc.dsa.Covid19Manager;
import edu.upc.dsa.Covid19ManagerImpl;
import edu.upc.dsa.models.Brote;
import edu.upc.dsa.models.Caso;
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
            this.cm.createCaso("01", "Ivan", "Requena", "16/05/1997", "17/04/2020",
                    "medio", "Hombre", "ivan.requena@estudiantat.upc.edu", "625427213", "Sant Boi", "Sospechoso");
            this.cm.createCaso("02", "Toni", "Oller", "10/03/1967", "12/04/2020",
                    "alto", "Hombre", "toni.oller@estudiantat.upc.edu", "655527213", "BCN", "No caso");
            this.cm.addCasoBrote("1", "01");
            this.cm.addCasoBrote("2", "02");
        }
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

    /*//Añadir nuevo caso
    @POST
    @ApiOperation(value = "create a new case", notes = "Añade un caso a la lista de casos")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Brote.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/addBrote/{id}/{name}/{surname}/{birthDate}/{caseDate}/{riskLevel}/{gender}/{email}/{telephone}/{direction}/{classification}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newCaso(@PathParam("id") String id, @PathParam("name") String name, @PathParam("surname") String surname,
                            @PathParam("birthDate") String birthDate, @PathParam("caseDate") String caseDate, @PathParam("rsikLevel") String riskLevel,
                            @PathParam("gender") String gender, @PathParam("email") String email, @PathParam("telephone") String telephone,
                            @PathParam("direction") String direction, @PathParam("classification") String classification) {
        if(this.cm.createCaso(id,name,surname,birthDate,caseDate,riskLevel,gender,email,telephone,direction,classification)==null)
            return Response.status(500).entity(cm.getCaso(id)).build();
        return Response.status(201).entity(cm.getCaso(id)).build();
    }*/

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

    //Añadir caso a brote
    @PUT
    @ApiOperation(value = "Add case", notes = "Añade un caso a un brote existente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Brote.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "User/GameObject Not found Error")
    })
    @Path("/addCasoABrote/{broteId}/{casoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCasoBrote(@PathParam("broteId") String broteId, @PathParam("casoId") String casoId) {
        if (broteId.isEmpty() || casoId.isEmpty())  return Response.status(400).entity(new Brote()).build();
        else{
            Brote brote = cm.getBrote(broteId);
            Caso caso = cm.getCaso(casoId);
            if(brote==null || caso ==null)  return Response.status(404).entity(new Brote()).build();
        }
        cm.addCasoBrote(broteId,casoId);
        return Response.status(201).entity(cm.getBrote(broteId)).build();
    }

    //Get casos de un brote
    @GET
    @ApiOperation(value = "Get casos de un brote", notes = "Devuelve lista de casos")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Caso.class,responseContainer="List"),
            @ApiResponse(code = 404, message = "Case not found"),
            @ApiResponse(code = 204, message = "No case found")
    })
    @Path("/brote/casos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCasesOfBrote(@PathParam("id") String id) {
        Brote brote = this.cm.getBrote(id);
        List<Caso> casos;
        if (brote == null) return Response.status(404).build();
        else {
            if(brote.getSizeCasos()==0){
                Response.status(204).build();
            }
        }
        casos = brote.getCasos();
        GenericEntity<List<Caso>> entity = new GenericEntity<List<Caso>>(casos) {};
        return Response.status(201).entity(entity).build()  ;
    }
}