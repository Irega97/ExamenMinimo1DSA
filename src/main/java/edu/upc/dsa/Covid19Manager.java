package edu.upc.dsa;

import edu.upc.dsa.models.Brote;
import edu.upc.dsa.models.Caso;

import java.util.HashMap;
import java.util.List;

public interface Covid19Manager {

    //SERVICIOS API
    //Crea un nuevo brote
    public int addBrote(String id);

    //Obtiene todos los brotes
    public List<Brote> mostrarBrotes();

    //Crear un caso y añadirlo a un brote
    public Caso createCaso(String id, String name, String surname, String fechaNacimiento, String fechaCaso,
                           String riskLevel, String gender, String email, String telephone, String direccion, String clasificacion);
    public int addCasoBrote(String broteId, String casoId);

    //Obtiene todos los casos de un brote
    public List<Caso> getCasesOfBrote(Brote b);

    //FUNCIONES EXTRAS NECESARIAS PARA QUE FUNCIONE
    //Obtener un brote
    public Brote getBrote(String id);

    //Numero brotes que hay en el HashMap
    public int sizeBrotes();

    //Numero casos que hay en la lista
    public int sizeCasos();

    //Obtener un caso
    public Caso getCaso(String id);

    //Tear down
    public void off();
}
