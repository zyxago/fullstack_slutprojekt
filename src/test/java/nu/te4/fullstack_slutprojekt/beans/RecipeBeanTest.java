/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.fullstack_slutprojekt.beans;

import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import nu.te4.fullstack_slutprojekt.entities.Recipe;
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
public class RecipeBeanTest {
    
    public RecipeBeanTest() {
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
     * Test of getRecipies method, of class RecipeBean.
     */
    @Test
    public void testGetRecipies() throws Exception {
        System.out.println("getRecipies");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        RecipeBean instance = (RecipeBean)container.getContext().lookup("java:global/classes/RecipeBean");
        List<Recipe> expResult = null;
        List<Recipe> result = instance.getRecipies();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addRecipe method, of class RecipeBean.
     */
    @Test
    public void testAddRecipe() throws Exception {
        System.out.println("addRecipe");
        Recipe recipe = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        RecipeBean instance = (RecipeBean)container.getContext().lookup("java:global/classes/RecipeBean");
        int expResult = 0;
        int result = instance.addRecipe(recipe);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifyRecipe method, of class RecipeBean.
     */
    @Test
    public void testModifyRecipe() throws Exception {
        System.out.println("modifyRecipe");
        int id = 0;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        RecipeBean instance = (RecipeBean)container.getContext().lookup("java:global/classes/RecipeBean");
        int expResult = 0;
        int result = instance.modifyRecipe(id);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeRecipe method, of class RecipeBean.
     */
    @Test
    public void testRemoveRecipe() throws Exception {
        System.out.println("removeRecipe");
        int id = 0;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        RecipeBean instance = (RecipeBean)container.getContext().lookup("java:global/classes/RecipeBean");
        int expResult = 0;
        int result = instance.removeRecipe(id);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
