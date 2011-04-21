/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

 
    @Test
    public void testLoadAndSave() throws Exception{
        String infile = "/tmp/viajes.txt";
        String outfile = "/tmp/generatedviajes.txt";
        System.out.println("Loading data from" + infile + "...");
        ArrayList<JourneyInfo> result = JourneysFileParser.loadData(infile);
        System.out.println("Saving data to " + outfile + "...");
        JourneysFileParser.saveData(result, outfile);

        System.out.println("Comparing " + infile + " with " + outfile + "...");
        BufferedReader br1 = new BufferedReader(new FileReader(infile));
        BufferedReader br2 = new BufferedReader(new FileReader(outfile));
        String line;
        boolean equal = true;
        while((line = br1.readLine()) != null){
            if(!line.equals(br2.readLine())){
               equal = false;
               break;
            }
        }
        br1.close();
        br2.close();
        System.out.println("Test completed");
        assertTrue(equal);
    }

}