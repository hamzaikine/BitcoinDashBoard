/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamzaikine.bitcoindashboard;

import java.math.BigDecimal;
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
public class CurrencyTest {

    Currency cr = new Currency(1.0, 1.0, "USD");

    public CurrencyTest() {
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

    @Test
    public void testGetBuy() {
        System.out.println("getBuy");
        BigDecimal expResult = BigDecimal.valueOf(1.0);
        BigDecimal result = cr.getBuy();
        assertEquals(expResult, result);

    }

    @Test
    public void testGetSell() {
        System.out.println("getSell");
        BigDecimal expResult = BigDecimal.valueOf(1.0);
        BigDecimal result = cr.getSell();
        assertEquals(expResult, result);

    }

    @Test
    public void testGetSymbol() {
        System.out.println("getSymbol");
        String expResult = "USD";
        String result = cr.getSymbol();
        assertEquals(expResult, result);

    }

}
