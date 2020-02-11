/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.fullstack_slutprojekt.beans;

import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import nu.te4.fullstack_slutprojekt.entities.Comment;
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
public class CommentBeanTest {
    
    public CommentBeanTest() {
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
     * Test of getComments method, of class CommentBean.
     */
    @Test
    public void testGetComments() throws Exception {
        System.out.println("getComments");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CommentBean instance = (CommentBean)container.getContext().lookup("java:global/classes/CommentBean");
        List<Comment> expResult = null;
        List<Comment> result = instance.getComments();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addComment method, of class CommentBean.
     */
    @Test
    public void testAddComment() throws Exception {
        System.out.println("addComment");
        Comment comment = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CommentBean instance = (CommentBean)container.getContext().lookup("java:global/classes/CommentBean");
        int expResult = 0;
        int result = instance.addComment(comment);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifyComment method, of class CommentBean.
     */
    @Test
    public void testModifyComment() throws Exception {
        System.out.println("modifyComment");
        int id = 0;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CommentBean instance = (CommentBean)container.getContext().lookup("java:global/classes/CommentBean");
        int expResult = 0;
        int result = instance.modifyComment(id);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeComment method, of class CommentBean.
     */
    @Test
    public void testRemoveComment() throws Exception {
        System.out.println("removeComment");
        int id = 0;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CommentBean instance = (CommentBean)container.getContext().lookup("java:global/classes/CommentBean");
        int expResult = 0;
        int result = instance.removeComment(id);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
