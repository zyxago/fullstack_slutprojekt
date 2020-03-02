/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.fullstack_slutprojekt.entities;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Erik
 */
public class RecipeBuilderTest {

    private RecipeBuilder instance;

    public RecipeBuilderTest() {
    }

    @Before
    public void setUp() {
        instance = new RecipeBuilder();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of build method, of class RecipeBuilder. List<String> == NULL
     */
    @Test
    public void testBuildNullCategories() {
        System.out.println("build:recipe - NULL categories");
        instance
                .setIngredients(new ArrayList<>())
                .setInstructions(new ArrayList<>())
                .setTitle("")
                .setCategories(null);
        try {
            instance.build();
            fail("Did not throw IllegalStateException when Category list was NULL.");
        } catch (IllegalStateException e) {

        }
    }

    /**
     * Test of build method, of class RecipeBuilder. List<String> == NULL
     */
    @Test
    public void testBuildNullInstructions() {
        System.out.println("build:recipe - NULL instructions");
        instance
                .setIngredients(new ArrayList<>())
                .setInstructions(null)
                .setTitle("")
                .setCategories(new ArrayList<>());
        try {
            instance.build();
            fail("Did not throw IllegalStateException when Instruction list was NULL.");
        } catch (IllegalStateException e) {

        }
    }

    /**
     * Test of build method, of class RecipeBuilder. List<Ingredient> == NULL
     */
    @Test
    public void testBuildNullIngredients() {
        System.out.println("build:recipe - NULL ingredients");
        instance
                .setIngredients(null)
                .setInstructions(new ArrayList<>())
                .setTitle("")
                .setCategories(new ArrayList<>());
        try {
            instance.build();
            fail("Did not throw IllegalStateException when Ingredient list was NULL.");
        } catch (IllegalStateException e) {

        }
    }

    /**
     * Test of build method, of class RecipeBuilder. id < 0
     */
    @Test
    public void testBuildNegativeId() {
        System.out.println("build:recipe - Negative ID");
        instance
                .setId(-1)
                .setIngredients(new ArrayList<>())
                .setInstructions(new ArrayList<>())
                .setCategories(new ArrayList<>())
                .setTitle("")
                .build();
        int expResult = 0;
        Recipe result = instance.build();
        assertEquals(expResult, result.getId());
    }

    /**
     * Test of build method, of class RecipeBuilder. writer Id < 0
     */
    @Test
    public void testBuildNegativeWriterId() {
        System.out.println("build:recipe - Negative Writer ID");
        instance
                .setWriterId(-1)
                .setIngredients(new ArrayList<>())
                .setInstructions(new ArrayList<>())
                .setTitle("")
                .setCategories(new ArrayList<>())
                .build();
        int expResult = 0;
        Recipe result = instance.build();
        assertEquals(expResult, result.getId());
    }

    /**
     * Test of build method, of class RecipeBuilder. title == NULL
     */
    @Test
    public void testBuildNullTitle() {
        System.out.println("build:recipe - NULL Title");
        instance
                .setIngredients(new ArrayList<>())
                .setInstructions(new ArrayList<>())
                .setCategories(new ArrayList<>());
        try {
            instance.build();
            fail("Did not throw IllegalStateException when Title was NULL.");
        } catch (IllegalStateException e) {

        }
    }
}
