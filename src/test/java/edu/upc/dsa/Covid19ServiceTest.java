package edu.upc.dsa;


import edu.upc.dsa.models.Brote;
import edu.upc.dsa.models.Caso;
import edu.upc.dsa.services.Covid19Service;
import org.apache.log4j.Logger;
//Junit 4.13
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class Covid19ServiceTest {
    // THE QUICK REMINDER: Remember to name the test class public smh
    //Log4j Logger initialization
    private static Logger logger = Logger.getLogger(Covid19ServiceTest.class);
    //GameManager
    public Covid19Manager cm = null;
    //Estructura de datos
    Brote brote;
    List<Caso> listaCasos;
    //Metodo SetUp
    @Before
    public void setUp() {
        //Configuring Log4j
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        logger.debug("Debug Test Message!");
        logger.info("Info Test Message!");
        logger.warn("Warning Test Message!");
        logger.error("Error Test Message!");
        //Instancing GameManager Implementation
        cm = Covid19ManagerImpl.getInstance();
        //Initializing Object List
        listaCasos =  new LinkedList<Caso>();
        //Initialzing Test User
        brote = new Brote("20");
        //Appending data to Object List
        listaCasos.add(new Caso("01","Ivan","Requena", "16/05/1997", "17/04/2020",
                "medio", "Hombre", "ivan.requena@estudiantat.upc.edu", "625427213", "Sant Boi", "Sospechoso"));
        listaCasos.add(new Caso("02","Toni","Oller", "10/03/1967", "12/04/2020",
                "alto", "Hombre", "toni.oller@estudiantat.upc.edu", "655527213", "BCN", "No caso"));
        //Adding Objects list to Game Manager
        cm.addCasoBrote(brote.getId(),"01");
        cm.addCasoBrote(brote.getId(),"02");
    }
    //Tests
    //Metodo Test para añadir un brote
    @Test
    public void addBroteTest(){
        //Initial Test, initial users in game Zero!
        Assert.assertEquals(0, this.cm.sizeBrotes());
        //Adding a user to the GameManager
        cm.addBrote(brote.getId());
        Assert.assertEquals(1, cm.sizeBrotes());
        //Adding a second user to the GameManager
        cm.addBrote("Brote bcn");
        Assert.assertEquals(2, cm.sizeBrotes());
    }

    @Test
    public void addCasoTest(){
        //Setting up with 1 Test User
        cm.addBrote(brote.getId());
        //Test for the objects the test user has equals 0 as setUp method
        Assert.assertEquals(0, cm.sizeCasos());
        //Adding an object to the User passing a id of the Object, Expects http 201 Ok
        Assert.assertEquals(201,cm.addCasoBrote(brote.getId(), listaCasos.get(0).getId()));
        //Test if the number of objects inside Test User has increased to 1
        Assert.assertEquals(1, cm.sizeBrotes());
    }

    //Metodo Teardown
    @After
    public void tearDown() {
        cm.off();
    }
}
