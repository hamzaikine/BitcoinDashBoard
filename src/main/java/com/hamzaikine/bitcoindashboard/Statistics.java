
package com.hamzaikine.bitcoindashboard;

import info.blockchain.api.APIException;
import info.blockchain.api.HttpClient;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hamzaikine
 */
public class Statistics {
    
    private final String apiCode;
    
    public Statistics () {
        this.apiCode = null;
    }

    
    /**
     * Gets the network statistics.
     *
     * @return An instance of the StatisticsResponse class
     * @throws APIException If the server returns an error
     */
    public StatisticsResponse getStats () throws APIException, IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("format", "json");
        if (apiCode != null) {
            params.put("api_code", apiCode);
        }

        String response = HttpClient.getInstance().get("stats", params);
        return null;   //new StatisticsResponse(response)
    }
    
    
    
}
