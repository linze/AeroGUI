/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author notrace
 */
public class UsersHandlerTest {

    /**
     * Test of login method, of class UsersList.
     */
    @Test
    public void testLogin() {
        System.out.println("login");

        UsersHandler instance = new UsersHandler();
        boolean result;
        instance.register("Pepe", "Paco", "Lala", "correo@mail.com", "123");
        result = instance.login("correo@mail.com", "123");
        assertEquals(result, true);
        result = instance.login("lalalala@lelele.com", "345");
        assertEquals(result, false);
        result = instance.login(null, null);
        assertEquals(result, false);
    }

    /**
     * Test of register method, of class UsersList.
     */
    @Test
    public void testRegister() {
        System.out.println("register");
        UsersHandler instance = new UsersHandler();
        boolean result;
        result = instance.register("Pepe", "Paco", "Lala", "correo@mail.com", "123");
        assertEquals(result, true);
        result = instance.register("Pepe", "Paco", "Lala", "correo@mail.com", "123");
        assertEquals(result, false);
        result = instance.register("Pepe", "Paco", "Lala", "juan@mail.com", "123");
        assertEquals(result, true);
    }

}