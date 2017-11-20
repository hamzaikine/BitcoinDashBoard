
package com.hamzaikine.bitcoindashboard;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hamzaikine
 */
public class Block {
    private long time;
    private long height;
    private long size;
    private String previousBlockHash;
    private long receivedTime;
    private List<Transaction> transactions;
    
    
    public Block (long height, long time, String previousBlockHash, long size, long receivedTime, List<Transaction> transactions) {
        this.height = height;
        this.time = time;
        this.previousBlockHash = previousBlockHash;
        this.size = size;
        this.receivedTime = receivedTime; 
        this.transactions = transactions;
        
    }
    
    
    public Block (JsonObject b) {
        this.height = b.get("height").getAsLong();
        this.time = b.get("time").getAsLong();
        this.previousBlockHash = b.get("prev_block").getAsString();
        this.size = b.get("size").getAsLong();
        this.receivedTime = (b.has("received_time") ? b.get("received_time").getAsLong() : b.get("time").getAsLong());

        transactions = new ArrayList<Transaction>();
        for (JsonElement txElem : b.get("tx").getAsJsonArray()) {
            transactions.add(new Transaction(txElem.getAsJsonObject(), getHeight(), false));
        }
    }
    
    
     public long getHeight () {
        return height;
    }
     
     
     
     
     
     
    
}
