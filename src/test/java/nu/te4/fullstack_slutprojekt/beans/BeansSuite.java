/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.fullstack_slutprojekt.beans;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author erikh
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({nu.te4.fullstack_slutprojekt.beans.CommentBeanTest.class, nu.te4.fullstack_slutprojekt.beans.RecipeBeanTest.class, nu.te4.fullstack_slutprojekt.beans.AuthenticationBeanTest.class})
public class BeansSuite {

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
