package application.model.steemj;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Asset {

    public long amount;
    public AssetSymbolType symbol;
    public byte precision;
    @GraphId
    Long id;

    public Asset() {
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public AssetSymbolType getSymbol() {
        return symbol;
    }

    public void setSymbol(AssetSymbolType symbol) {
        this.symbol = symbol;
    }

    public byte getPrecision() {
        return precision;
    }

    public void setPrecision(byte precision) {
        this.precision = precision;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
