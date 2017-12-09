/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamzaikine.bitcoindashboard;

import info.blockchain.api.blockexplorer.entity.FilterType;
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
public class BlockExplorerTest {

    public BlockExplorerTest() {
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
    public void testGetBlock() throws Exception {
        System.out.println("getBlock");
        String blockHash = "0000000000000000005858d63912f89e244f83d138b4e0b00b54430b2877a57c";
        BlockExplorer instance = new BlockExplorer();
        Block expResult = null;
        Block result = instance.getBlock(blockHash);
        assertEquals(expResult, result.getHash());

    }

    @Test
    public void testGetAddress() throws Exception {
        System.out.println("getAddress");
        String address = "3EhLZarJUNSfV6TWMZY1Nh5mi3FMsdHa5U";
        FilterType filter = null;
        Integer limit = null;
        Integer offset = null;
        BlockExplorer instance = new BlockExplorer();
        Address expResult = new Address("", "3EhLZarJUNSfV6TWMZY1Nh5mi3FMsdHa5U", 0L, 0L, 0L, 0, null);
        Address result = instance.getAddress(address, filter, limit, offset);
        assertEquals(expResult.getAddress(), result.getAddress());

    }

}
