
package com.hamzaikine.bitcoindashboard;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import info.blockchain.api.APIException;
import info.blockchain.api.HttpClient;
import info.blockchain.api.blockexplorer.entity.FilterType;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hamzaikine
 */
public class BlockExplorer {
    private final String apiCode;
    public BlockExplorer(){
       this.apiCode = null;
    }
    
    /**
     * @param apiCode Blockchain.info API code (optional, nullable)
     */
    public BlockExplorer (String apiCode) {
        this.apiCode = apiCode;
    }
    
  /**
     * Gets a single block based on a block hash.
     *
     * @param blockHash Block hash
     * @return An instance of the {@link Block} class
     * @throws APIException If the server returns an error
     */
    public Block getBlock (String blockHash) throws APIException, IOException {
        String response = HttpClient.getInstance().get("rawblock/" + blockHash, buildBasicRequest());
        JsonObject blockJson = new JsonParser().parse(response).getAsJsonObject();
        return new Block(blockJson);
    }
    
    
     /**
     * Gets data for a single address.
     *
     * @param address Base58check or hash160 address string
     * @param filter the filter for transactions selection, use null to indicate default
     * @param limit an integer to limit number of transactions to display, use null to indicate default
     * @param offset an integer to set number of transactions to skip when fetch, use null to indicate default
     * @return An instance of the {@link Address} class
     * @throws APIException If the server returns an error
     */
    public Address getAddress (String address, FilterType filter, Integer limit, Integer offset) throws APIException, IOException {
        Map<String, String> params = buildBasicRequest();
        if (filter != null) {
            params.put("filter", filter.getFilterInt().toString());
        }
        if (limit != null) {
            params.put("limit", limit.toString());
        }
        if (offset != null) {
            params.put("offset", offset.toString());
        }
        String response = HttpClient.getInstance().get("rawaddr/" + address, params);
        JsonObject addrJson = new JsonParser().parse(response).getAsJsonObject();

        return new Address(addrJson);
    }
    
  
     private Map<String, String> buildBasicRequest () {
        Map<String, String> params = new HashMap<String, String>();

        params.put("format", "json");
        if (apiCode != null) {
            params.put("api_code", apiCode);
        }

        return params;
  }
    
}
