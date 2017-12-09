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
    private String hash;
    private boolean mainChain;
    private long n_tx;
    private String previousBlockHash;
    private long receivedTime;
    private List<Transaction> transactions;

    public Block(long height, long time, String previousBlockHash, long size, long receivedTime, List<Transaction> transactions) {
        this.height = height;
        this.time = time;
        this.previousBlockHash = previousBlockHash;
        this.size = size;
        this.receivedTime = receivedTime;
        this.transactions = transactions;

    }

    public Block(long height, String hash, long time, boolean mainChain) {
        this.height = height;
        this.hash = hash;
        this.time = time;
        this.mainChain = mainChain;
    }

    public Block(JsonObject b) {
        this.height = b.get("height").getAsLong();
        this.time = b.get("time").getAsLong();
        this.previousBlockHash = b.get("prev_block").getAsString();
        this.size = b.get("size").getAsLong();
        this.receivedTime = (b.has("received_time") ? b.get("received_time").getAsLong() : b.get("time").getAsLong());
        this.n_tx = b.get("n_tx").getAsLong();
        transactions = new ArrayList<Transaction>();
        for (JsonElement txElem : b.get("tx").getAsJsonArray()) {
            transactions.add(new Transaction(txElem.getAsJsonObject(), getHeight(), false));
        }
    }

    /**
     * @return number of transactions in a block
     */
    public long getNTx() {
        return n_tx;
    }

    /**
     * @return the block hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * @return Serialized size of this block
     */
    public long getSize() {
        return size;
    }

    public long getHeight() {
        return height;
    }

    /**
     * @return The time this block was received by Blockchain.info
     */
    public long getReceivedTime() {
        return receivedTime;
    }

    /**
     * @return The time latest block was received by Blockchain.info
     */
    public long getTime() {
        return time;
    }

    /**
     * @return Hash of the previous block
     */
    public String getPreviousBlockHash() {
        return previousBlockHash;
    }

    /**
     * @return Transactions in the block
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

}
