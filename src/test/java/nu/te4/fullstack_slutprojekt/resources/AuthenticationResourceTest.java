/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.fullstack_slutprojekt.resources;

import javax.ws.rs.core.Response;
import nu.te4.fullstack_slutprojekt.entities.Credentials;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Erik
 */
public class AuthenticationResourceTest {
    
    public AuthenticationResourceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of register method, of class AuthenticationResource.
     */
    @Test
    public void testRegister() {
        System.out.println("register");
        Credentials credentials = null;
        AuthenticationResource instance = new AuthenticationResource();
        Response expResult = null;
        Response result = instance.register(credentials);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verify method, of class AuthenticationResource.
     */
    @Test
    public void testVerify() {
        System.out.println("verify");
        String token = "";
        AuthenticationResource instance = new AuthenticationResource();
        Response expResult = null;
        Response result = instance.verify(token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
