/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.fullstack_slutprojekt.beans;

import javax.ejb.embeddable.EJBContainer;
import nu.te4.fullstack_slutprojekt.entities.Credentials;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author erikh
 */
public class AuthenticationBeanTest {
    
    public AuthenticationBeanTest() {
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
     * Test of register method, of class AuthenticationBean.
     */
    @Test
    public void testRegister() throws Exception {
        System.out.println("register");
        Credentials credentials = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        AuthenticationBean instance = (AuthenticationBean)container.getContext().lookup("java:global/classes/AuthenticationBean");
        int expResult = 0;
        int result = instance.register(credentials);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verify method, of class AuthenticationBean.
     */
    @Test
    public void testVerify() throws Exception {
        System.out.println("verify");
        String token = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        AuthenticationBean instance = (AuthenticationBean)container.getContext().lookup("java:global/classes/AuthenticationBean");
        boolean expResult = false;
        boolean result = instance.verify(token);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
