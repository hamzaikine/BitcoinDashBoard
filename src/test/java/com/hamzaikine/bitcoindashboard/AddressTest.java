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
public class AddressTest {

    Address ad = new Address("", "1F1tAaz5x1HUXrCNLbtMDqcw6o5GNn4xqX", 1, 2, 3, 4, null);

    public AddressTest() {
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
    public void testGetAddress() {
        System.out.println("getAddress");
        String expResult = "1F1tAaz5x1HUXrCNLbtMDqcw6o5GNn4xqX";
        String result = ad.getAddress();
        assertEquals(expResult, result);

    }

    @Test
    public void testGetTotalReceived() {
        System.out.println("getTotalReceived");
        double expResult = 1;
        double result = ad.getTotalReceived();
        assertEquals(expResult, result);

    }

    @Test
    public void testGetTotalSent() {
        System.out.println("getTotalSent");
        double expResult = 2;
        double result = ad.getTotalSent();
        assertEquals(expResult, result);

    }

    @Test
    public void testGetFinalBalance() {
        System.out.println("getFinalBalance");
        double expResult = 3;
        double result = ad.getFinalBalance();
        assertEquals(expResult, result);

    }

    @Test
    public void testGetTxCount() {
        System.out.println("getTxCount");
        int expResult = 4;
        int result = ad.getTxCount();
        assertEquals(expResult, result);

    }

    @Test
    public void testGetTransactions() {
        System.out.println("getTransactions");
        List<Transaction> expResult = null;
        List<Transaction> result = ad.getTransactions();
        assertEquals(expResult, result);

    }

}
