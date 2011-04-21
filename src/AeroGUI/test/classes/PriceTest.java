/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

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
public class PriceTest {

    public PriceTest() {
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
     * Test of getQuantity method, of class Price.
     */
    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
        Price instance = new Price();
        double expResult = 0.0;
        double result = instance.getQuantity();
        assertTrue(expResult == instance.getQuantity());
    }

    /**
     * Test of setQuantity method, of class Price.
     */
    @Test
    public void testSetQuantity() {
        System.out.println("setQuantity");
        double quantity = 3;
        Price instance = new Price();
        instance.setQuantity(quantity);
        assertTrue(instance.getQuantity() == quantity);
    }

    /**
     * Test of getCurrency method, of class Price.
     */
    @Test
    public void testGetCurrency() {
        System.out.println("getCurrency");
        Price instance = new Price();
        String expResult = "EUR";
        String result = instance.getCurrency();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCurrency method, of class Price.
     */
    @Test
    public void testSetCurrency() {
        System.out.println("setCurrency");
        String currency = "DOL";
        Price instance = new Price();
        instance.setCurrency(currency);
        assertEquals(instance.getCurrency(), currency);
    }

}