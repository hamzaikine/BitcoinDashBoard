package com.hamzaikine.bitcoindashboard;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import info.blockchain.api.blockexplorer.entity.Input;
import info.blockchain.api.blockexplorer.entity.Output;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hamzaikine
 */
public class Transaction {

    private boolean doubleSpend;
    private long time;
    private long blockHeight;
    private long size;
    private String hash;
    private List<Input> inputs;
    private List<Output> outputs;

    public Transaction(JsonObject t) {
        this(t, t.has("block_height") ? t.get("block_height").getAsLong() : -1,
                t.has("double_spend") ? t.get("double_spend").getAsBoolean() : false);
    }

    public Transaction(JsonObject t, long blockHeight, boolean doubleSpend) {
        this.doubleSpend = doubleSpend;
        this.blockHeight = blockHeight;
        this.time = t.get("time").getAsLong();
        this.hash = t.get("hash").getAsString();
        this.size = t.get("size").getAsLong();
        this.inputs = null;
        this.outputs = null;

        inputs = new ArrayList<Input>();
        for (JsonElement inputElem : t.get("inputs").getAsJsonArray()) {
            inputs.add(new Input(inputElem.getAsJsonObject()));
        }

        outputs = new ArrayList<Output>();
        for (JsonElement outputElem : t.get("out").getAsJsonArray()) {
            outputs.add(new Output(outputElem.getAsJsonObject()));
        }
    }

    /**
     * @return Transaction hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * @return Serialized size of the transaction
     */
    public long getSize() {
        return size;
    }

    /**
     * @return List of inputs
     */
    public List<Input> getInputs() {
        return inputs;
    }

    /**
     * @return List of outputs
     */
    public List<Output> getOutputs() {
        return outputs;
    }

}
