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
    public int addCasoBrote(Caso c);

    //Obtiene todos los casos de un brote
    public List<Caso> getCasesOfBrote(Brote b);

    //FUNCIONES EXTRAS NECESARIAS PARA QUE FUNCIONE
    //Obtener un brote
    public Brote getBrote(String id);

    //Numero brotes que hay en el HashMap
    public int sizeBrotes();

    //Numero casos que hay en la lista
    public int sizeCasos();

    /*public Track addTrack(String title, String singer);
    public Track addTrack(Track t);
    public Track getTrack(String id);
    public List<Track> findAll();
    public void deleteTrack(String id);
    public Track updateTrack(Track t);

    public int size();

    //listado de usuarios ordenado alfaticamente
    List<User> getSortedUsersAlphabetical();
    //Añadir un usuario
    int addUser(String id, String name, String surname);
    //Modicar un usuario
    int updateUser(String id, String name, String surname);
    //Consultar información de un usuario
    User getUser(String id);
    //Consultar número de un usuarios que hay en el sistema
    int numUsers();
    //Consultar los objetos de un usuario (orden de inserción)
    int addUserGameObject(String id, String gameObjectId);
    //Consultar los objetos de un usuario (orden de inserción)
    int addUserGameObjects(String id, List<GameObject> listGameObjects);
    //Consultar el número de objetos de un usuario
    int getNumGameObjectsUser(String id);
                      Extras
    //Añadir un Objeto
    int addGameObject(GameObject gameObject);
    //Añadir una lista de Objetos
    int addGameObjects(List<GameObject> listGameObjects);
    //Consultar un Objeto
    GameObject getGameObject(String ObjectId);
    //Consultar la lista de Objetos
    List<User> getUsersList();
    //Liberar Recursos
    void liberateReserves();
    //Generate Id
    String generateId();
    */
}
