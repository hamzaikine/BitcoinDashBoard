/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamzaikine.bitcoindashboard;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hamzaikine
 */
public class BlockTest {
    
    public BlockTest() {
    }
    
    Block bl = new Block(496547,"000000000000000000ada0187d76aa8658f3737bb7e17b2d868ad529fcd02c9b",0,true);
    
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

    @Test
    public void testGetHash() {
        System.out.println("getHash");
        String expResult = "000000000000000000ada0187d76aa8658f3737bb7e17b2d868ad529fcd02c9b";
        String result = bl.getHash();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testGetSize() {
        System.out.println("getSize");
        long expResult = 0L;
        long result = bl.getSize();
        assertEquals(expResult, result);
       
    }

    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        long expResult = 496547;
        long result = bl.getHeight();
        assertEquals(expResult, result);
       
    }

    @Test
    public void testGetReceivedTime() {
        System.out.println("getReceivedTime");
        long expResult = 0L;
        long result = bl.getReceivedTime();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testGetTransactions() {
        System.out.println("getTransactions");
        List<Transaction> expResult = null;
        List<Transaction> result = bl.getTransactions();
        assertEquals(expResult, result);
        
    }
    
}
