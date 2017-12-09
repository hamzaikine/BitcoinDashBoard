package com.hamzaikine.bitcoindashboard;

import java.math.BigDecimal;

/**
 *
 * @author hamzaikine
 */
public class Currency {

    private BigDecimal buy;
    private BigDecimal sell;
    private String symbol;

    public Currency(double buy, double sell, String symbol) {
        this.buy = BigDecimal.valueOf(buy);
        this.sell = BigDecimal.valueOf(sell);
        this.symbol = symbol;
    }

    /**
     * @return Current buy price
     */
    public BigDecimal getBuy() {
        return buy;
    }

    /**
     * @return Current sell price
     */
    public BigDecimal getSell() {
        return sell;
    }

    /**
     * @return Currency symbol
     */
    public String getSymbol() {
        return symbol;
    }

}
