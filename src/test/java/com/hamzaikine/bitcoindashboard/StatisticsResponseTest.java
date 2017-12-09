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
public class StatisticsResponseTest {

    public StatisticsResponseTest() {
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
    public void testGetMarketPriceUSD() {
        System.out.println("getMarketPriceUSD");
        StatisticsResponse instance = new StatisticsResponse();
        BigDecimal expResult = null;
        BigDecimal result = instance.getMarketPriceUSD();
        assertEquals(expResult, result);

    }

}
