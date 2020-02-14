/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.fullstack_slutprojekt.resources;

import javax.ws.rs.core.Response;
import nu.te4.fullstack_slutprojekt.entities.Comment;
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
public class CommentResourceTest {
    
    public CommentResourceTest() {
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
     * Test of getComments method, of class CommentResource.
     */
    @Test
    public void testGetComments() {
        System.out.println("getComments");
        CommentResource instance = new CommentResource();
        Response expResult = null;
        Response result = instance.getComments();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addComment method, of class CommentResource.
     */
    @Test
    public void testAddComment() {
        System.out.println("addComment");
        Comment comment = null;
        CommentResource instance = new CommentResource();
        Response expResult = null;
        Response result = instance.addComment(comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of repportComment method, of class CommentResource.
     */
    @Test
    public void testRepportComment() {
        System.out.println("repportComment");
        int id = 0;
        CommentResource instance = new CommentResource();
        Response expResult = null;
        Response result = instance.repportComment(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of likeComment method, of class CommentResource.
     */
    @Test
    public void testLikeComment() {
        System.out.println("likeComment");
        int id = 0;
        CommentResource instance = new CommentResource();
        Response expResult = null;
        Response result = instance.likeComment(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifyComment method, of class CommentResource.
     */
    @Test
    public void testModifyComment() {
        System.out.println("modifyComment");
        int id = 0;
        CommentResource instance = new CommentResource();
        Response expResult = null;
        Response result = instance.modifyComment(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeComment method, of class CommentResource.
     */
    @Test
    public void testRemoveComment() {
        System.out.println("removeComment");
        int id = 0;
        CommentResource instance = new CommentResource();
        Response expResult = null;
        Response result = instance.removeComment(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
