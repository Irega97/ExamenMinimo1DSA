package edu.upc.dsa;

import edu.upc.dsa.models.Brote;
import edu.upc.dsa.models.Caso;
import org.apache.log4j.Logger;

import java.util.*;

public class Covid19ManagerImpl implements Covid19Manager{
    private static Covid19Manager instance;
    protected HashMap<String, Brote> brotes;
    protected List<Caso> casos;
    final static Logger logger = Logger.getLogger(Covid19ManagerImpl.class);

    private Covid19ManagerImpl() {
        this.brotes = new HashMap<>();
        this.casos = new LinkedList<>();
    }

    public static Covid19Manager getInstance() {
        if (instance==null) instance = new Covid19ManagerImpl();
        return instance;
    }


    @Override
    public int addBrote(String id) {
        Brote brote = new Brote(id);
        try{
            brotes.put(id,brote);
            logger.info("Brote Added: " +brote );
            return 201; //OK CREATED
        }
        catch (IllegalArgumentException e){
            logger.error("Incorrect format exception");
            return 400; //ERROR
        }
    }

    @Override
    public List<Brote> mostrarBrotes() {
        if(this.brotes != null) {
            List<Brote> result = new LinkedList<>(brotes.values());
            logger.info("Lista brotes: " + result.toString());
            return result; //200 OK PETITION
        }
        else
            return null; //404 (Empty Table)
    }

    @Override
    public Caso createCaso(String id, String name, String surname, String fechaNacimiento, String fechaCaso, String riskLevel,
                           String gender, String email, String telephone, String direccion, String clasificacion) {
        Caso caso = null;
        for(Caso c : this.casos){
            if(c.getId().equals(id)){
                return null; //El caso ya existe
            }
            else{
                caso = new Caso(id,name,surname,fechaNacimiento,fechaCaso,riskLevel,gender,email,telephone,direccion,clasificacion);
            }
        }
        return caso;
    }

    @Override
    public int addCasoBrote(String broteId, String casoId) {
        Brote brote = brotes.get(broteId);
        Caso caso = getCaso(casoId);
        if(brote != null && caso !=null){
            boolean add = brote.getCasos().add(caso);
                if(add==true){
                logger.info("Caso añadido al brote " + brote.getId() + " : " + caso.getName() + caso.getSurname());
                return 201; //OK CREATED
            }
            else{
                logger.info("No se ha podido añadir caso "+casoId);
                return 400; //BAD REQUEST
            }
        }
        else{
            logger.error("Brote con ID "+broteId+ "o caso con ID "+casoId+" no encontrados");
            return 404; //NOT FOUND
        }
    }

    @Override
    public List<Caso> getCasesOfBrote(Brote b) {
        for(Brote brote : this.brotes.values()){
            if(brotes.get(brote.getId()).equals(b.getId()))
                return b.getCasos();
        }
        return null;
    }

    //FUNCIONES EXTRAS

    @Override
    public Brote getBrote(String id) {
        if(brotes!=null) {
            Brote b = new Brote(id);
                if (brotes.containsKey(id)==true) {
                    logger.info("getBrote(" + id + "): " + b);
                    return b;
                }
            }

        logger.warn("not found " + id);
        return null;
    }

    @Override
    public int sizeBrotes(){
        return this.brotes.size();
    }

    @Override
    public int sizeCasos() {
        return this.casos.size();
    }

    @Override
    public Caso getCaso(String id) {
        for(Caso c: this.casos){
            if(c.getId().equals(id)){
                return c;
            }
        }
        return null;
    }

    public List<Caso> getCasos(){
        return this.casos;
    }

    @Override
    public void off() {
        this.casos.clear();
        this.brotes.clear();
    }
}
