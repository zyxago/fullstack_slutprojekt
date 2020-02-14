/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.fullstack_slutprojekt.entities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Erik
 */
public class CommentBuilderTest {

    public CommentBuilderTest() {
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
     * Test of build method, of class CommentBuilder. text = NULL
     */
    @Test
    public void testBuildNullText() {
        System.out.println("build:comment - NULL text");
        CommentBuilder instance = new CommentBuilder();
        instance.setText(null);
        try {
            instance.build();
            fail("Did not throw IllegalStateException when text string was NULL.");
        } catch (IllegalStateException e) {

        }
    }

    /**
     * Test of build method, of class CommentBuilder. writer id < 0
     */
    @Test
    public void testBuildNegativeWriterId() {
        System.out.println("build:comment - Negative Writer ID");
        CommentBuilder instance = new CommentBuilder();
        instance.setWriterId(-1)
                .setText("")
                .build();
        int expResult = 0;
        Comment result = instance.build();
        assertEquals(expResult, result.getWriterId());
    }

    /**
     * Test of build method, of class CommentBuilder. id < 0
     */
    @Test
    public void testBuildNegativeId() {
        System.out.println("build:comment - Negative ID");
        CommentBuilder instance = new CommentBuilder();
        instance
                .setId(-1)
                .setText("")
                .build();
        int expResult = 0;
        Comment result = instance.build();
        assertEquals(expResult, result.getId());
    }
}
