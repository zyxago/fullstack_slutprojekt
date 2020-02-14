/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.fullstack_slutprojekt.resources;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Erik
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({nu.te4.fullstack_slutprojekt.resources.RecipeResourceTest.class, nu.te4.fullstack_slutprojekt.resources.CommentResourceTest.class, nu.te4.fullstack_slutprojekt.resources.AuthenticationResourceTest.class})
public class ResourcesSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
