/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import java.io.File;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author notrace
 */
public class JourneysFileParserTest {

    public JourneysFileParserTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of loadData method, of class JourneysFileParser.
     */
    @Test
    public void testLoadData() throws Exception {
        System.out.println("loadData");
        
        ArrayList<JourneyInfo> expResult = new ArrayList<JourneyInfo>();
        JourneyInfo ji = new JourneyInfo();
        // First one
        ji.setId("IB201");
        ji.setType("AV");
        ji.setOrigin("A-Coruña");
        ji.setDestination("Madrid");
        // FIXME: Change it when got the reply.
        ji.setDeparture(null);
        ji.setArrival(null);
        ji.getTouristinfo().getPrice().setQuantity(60.0);
        ji.getTouristinfo().setSeatsLeft(50);
        ji.getBusinessinfo().getPrice().setQuantity(120.0);
        ji.getBusinessinfo().setSeatsLeft(20);
        ji.setStatus("NORMAL");

        expResult.add(ji);
        ji = new JourneyInfo();
        
        // Second one
        ji.setId("IB202");
        ji.setType("AV");
        ji.setOrigin("Madrid");
        ji.setDestination("Tenerife");
        // FIXME: Change it when got the reply.
        ji.setDeparture(null);
        ji.setArrival(null);
        ji.getTouristinfo().getPrice().setQuantity(60);
        ji.getTouristinfo().setSeatsLeft(40);
        ji.getBusinessinfo().getPrice().setQuantity(120);
        ji.getBusinessinfo().setSeatsLeft(20);
        ji.setStatus("NORMAL");

        expResult.add(ji);

        String file = "/tmp/viajes.txt";
        ArrayList<JourneyInfo> result = JourneysFileParser.loadData(file);

        for (int i=0; i<expResult.size(); i++) {
            System.out.println("\t" + expResult.get(i).getId() + " == " + result.get(i).getId());
            System.out.println("\t" + expResult.get(i).getType() + " == " + result.get(i).getType());
            System.out.println("\t" + expResult.get(i).getOrigin() + " == " + result.get(i).getOrigin());
            System.out.println("\t" + expResult.get(i).getDestination() + " == " + result.get(i).getDestination());
            System.out.println("\t" + Double.toString(expResult.get(i).getTouristinfo().getPrice().getQuantity()) +
                    " == " + Double.toString(result.get(i).getTouristinfo().getPrice().getQuantity()));
            System.out.println("\t" + Integer.toString(expResult.get(i).getTouristinfo().getSeatsLeft()) + " == " +
                    Integer.toString(result.get(i).getTouristinfo().getSeatsLeft()));
            System.out.println("\t" + Double.toString(expResult.get(i).getBusinessinfo().getPrice().getQuantity()) +
                    " == " + Double.toString(result.get(i).getBusinessinfo().getPrice().getQuantity()));
            System.out.println("\t" + Integer.toString(expResult.get(i).getBusinessinfo().getSeatsLeft()) + " == " +
                    Integer.toString(result.get(i).getBusinessinfo().getSeatsLeft()));
        }

        // It will fail until Date attribute is implemented
        assertTrue(result.equals(expResult));
    }

    /**
     * Test of saveData method, of class JourneysFileParser.
     */
    @Test
    public void testSaveData() throws Exception{
        System.out.println("saveData");
        String infile = "/tmp/viajes.txt";
        String outfile = "/tmp/generatedviajes.txt";
        ArrayList<JourneyInfo> result = JourneysFileParser.loadData(infile);
        JourneysFileParser.saveData(result, outfile);

        File original = new File(infile);
        File generated = new File(outfile);

        // It will fail until Date attribute is implemented
        assertTrue(original.compareTo(generated) == 0);
    }

}