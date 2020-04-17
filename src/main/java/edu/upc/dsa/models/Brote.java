package edu.upc.dsa.models;

import java.util.LinkedList;
import java.util.List;

public class Brote {
    public String id;
    public List<Caso> casos = null;

    public Brote(){
    }

    public Brote (String id){
        this();
        this.id=id;
        this.casos=new LinkedList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Caso> getCasos() {
        return casos;
    }

    public void setCasos(List<Caso> casos) {
        this.casos = casos;
    }

    public void addCaso(Caso c){
        this.casos.add(c);
    }

    public int getSizeCasos(){
        return this.casos.size();
    }

    @Override
    public String toString() {
        return "BROTE: [id = " + this.getId() + "]" ;
    }
}
