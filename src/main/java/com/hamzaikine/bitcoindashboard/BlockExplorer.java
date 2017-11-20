
package com.hamzaikine.bitcoindashboard;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import info.blockchain.api.APIException;
import info.blockchain.api.HttpClient;
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
    
    
    
    
  
     private Map<String, String> buildBasicRequest () {
        Map<String, String> params = new HashMap<String, String>();

        params.put("format", "json");
        if (apiCode != null) {
            params.put("api_code", apiCode);
        }

        return params;
  }
    
}
