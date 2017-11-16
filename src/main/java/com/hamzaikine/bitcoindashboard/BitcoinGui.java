
package com.hamzaikine.bitcoindashboard;


import info.blockchain.api.APIException;
import info.blockchain.api.blockexplorer.BlockExplorer;
import info.blockchain.api.blockexplorer.entity.Address;
import info.blockchain.api.blockexplorer.entity.SimpleBlock;
import info.blockchain.api.exchangerates.*;
import info.blockchain.api.statistics.Chart;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hamzaikine
 */
public class BitcoinGui {
    
      public static void main(String[] argv){
        
        Currency cr;
        //ExchangeRates er = new ExchangeRates();
        Map<String,Currency> mp = new HashMap<String,Currency>();
        try {
         //    mp = er.getTicker();
             
         //   BigDecimal e = er.toBTC("USD", BigDecimal.valueOf(1000));
           //  System.out.println("$" + BigDecimal.valueOf(1000) + " in BTC:" + e );
             
             Statistics s = new Statistics();
             StatisticsResponse sr;
             BlockExplorer be = new BlockExplorer();
             SimpleBlock sb;
             Address ad;
             sb = be.getLatestBlock();
             ad =be.getAddress("3EhLZarJUNSfV6TWMZY1Nh5mi3FMsdHa5U");
              
             sr = s.getStats();
     
             
             System.out.println("#blockheight:" + sb.getHeight());
              System.out.println("1BTC = " + sr.getMarketPriceUSD() + "$USD.");
        } catch (APIException ex) {
            Logger.getLogger(BitcoinGui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BitcoinGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        Iterator<Map.Entry<String, Currency>> mapIterator = mp.entrySet().iterator();
//        while (mapIterator.hasNext()) {
//            Map.Entry<String, Currency> entry = mapIterator.next();
//            System.out.println(entry.getKey() + ": " + entry.getValue().getSell());
//            
//        }  
         
         
         
         
        
    } 

    
    
    
}
