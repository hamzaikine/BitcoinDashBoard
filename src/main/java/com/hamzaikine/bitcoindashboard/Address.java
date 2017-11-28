
package com.hamzaikine.bitcoindashboard;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hamzaikine
 */
public class Address {
    private String hash160;
    private String address;
    private long totalReceived;
    private long totalSent;
    private long finalBalance;
    private int txCount;
    private List<Transaction> transactions;

    public Address (String hash160, String address, long totalReceived, long totalSent, long finalBalance, int txCount, List<Transaction> transactions) {
        this.hash160 = hash160;
        this.address = address;
        this.totalReceived = totalReceived;
        this.totalSent = totalSent;
        this.finalBalance = finalBalance;
        this.txCount = txCount;
        this.transactions = transactions;
    }

    public Address (JsonObject a) {

        
               this.hash160 = a.has("hash160") ? a.get("hash160").getAsString() : "";
               this.address = a.has("address") ? a.get("address").getAsString() : "";
               this.totalReceived = a.has("total_received") ? a.get("total_received").getAsLong() : 0;
               this.totalSent = a.has("total_sent") ? a.get("total_sent").getAsLong() : 0;
               this.finalBalance = a.has("final_balance") ? a.get("final_balance").getAsLong() : 0;
               this.txCount = a.has("n_tx") ? a.get("n_tx").getAsInt() : 0;
               this.transactions = null;

        transactions = new ArrayList<Transaction>();
        for (JsonElement txElem : a.get("txs").getAsJsonArray()) {
            JsonObject addrObj = txElem.getAsJsonObject();
            transactions.add(new Transaction(addrObj));
        }
    }
    
    
     /**
     * @return Base58Check representation of the address
     */
    public String getAddress () {
        return address;
    }
    
    
    /**
     * @return Total amount received (in satoshi)
     */
    public long getTotalReceived () {
        return totalReceived;
    }

    /**
     * @return Total amount sent (in satoshi)
     */
    public long getTotalSent () {
        return totalSent;
    }

    /**
     * @return Final balance of the address (in satoshi)
     */
    public long getFinalBalance () {
        return finalBalance;
    }
    
    
     /**
     * @return Original number of transactions before filtering and limiting
     */
    public int getTxCount () {
        return txCount;
    }

    /**
     * @return List of transactions associated with this address
     */
    public List<Transaction> getTransactions () {
        return transactions;
    }
    
    
}
