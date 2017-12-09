package com.hamzaikine.bitcoindashboard;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.math.BigDecimal;

/**
 *
 * @author hamzaikine
 */
public class StatisticsResponse {

    private BigDecimal marketPriceUSD;

    public StatisticsResponse() {
        this.marketPriceUSD = null;
    }

    public StatisticsResponse(String jsonString) {
        JsonObject s = new JsonParser().parse(jsonString).getAsJsonObject();

        this.marketPriceUSD = new BigDecimal(s.get("market_price_usd").getAsString());

    }

    public BigDecimal getMarketPriceUSD() {
        return marketPriceUSD;
    }

}
