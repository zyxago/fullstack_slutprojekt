/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.fullstack_slutprojekt.resources;

import javax.ws.rs.core.Response;
import nu.te4.fullstack_slutprojekt.entities.Recipe;
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
public class RecipeResourceTest {
    
    public RecipeResourceTest() {
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
     * Test of getRecepies method, of class RecipeResource.
     */
    @Test
    public void testGetRecepies() {
        System.out.println("getRecepies");
        RecipeResource instance = new RecipeResource();
        Response expResult = null;
        Response result = instance.getRecepies();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addRecipe method, of class RecipeResource.
     */
    @Test
    public void testAddRecipe() {
        System.out.println("addRecipe");
        Recipe recipe = null;
        RecipeResource instance = new RecipeResource();
        Response expResult = null;
        Response result = instance.addRecipe(recipe);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of repportRecipe method, of class RecipeResource.
     */
    @Test
    public void testRepportRecipe() {
        System.out.println("repportRecipe");
        int id = 0;
        RecipeResource instance = new RecipeResource();
        Response expResult = null;
        Response result = instance.repportRecipe(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of likeRecipe method, of class RecipeResource.
     */
    @Test
    public void testLikeRecipe() {
        System.out.println("likeRecipe");
        int id = 0;
        RecipeResource instance = new RecipeResource();
        Response expResult = null;
        Response result = instance.likeRecipe(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifyRecipe method, of class RecipeResource.
     */
    @Test
    public void testModifyRecipe() {
        System.out.println("modifyRecipe");
        int id = 0;
        RecipeResource instance = new RecipeResource();
        Response expResult = null;
        Response result = instance.modifyRecipe(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeRecipe method, of class RecipeResource.
     */
    @Test
    public void testRemoveRecipe() {
        System.out.println("removeRecipe");
        int id = 0;
        RecipeResource instance = new RecipeResource();
        Response expResult = null;
        Response result = instance.removeRecipe(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
