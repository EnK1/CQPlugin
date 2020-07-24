package com.example.demo.pojo;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("symbol")
    private String symbol;

    @SerializedName("volumeUsd24Hr")
    private String volumeUsd24Hr;

    @SerializedName("marketCapUsd")
    private String marketCapUsd;

    @SerializedName("priceUsd")
    private String priceUsd;

    @SerializedName("vwap24Hr")
    private String vwap24Hr;

    @SerializedName("changePercent24Hr")
    private String changePercent24Hr;

    @SerializedName("name")
    private String name;

    @SerializedName("rank")
    private String rank;

    @SerializedName("id")
    private String id;

    @SerializedName("maxSupply")
    private String maxSupply;

    @SerializedName("supply")
    private String supply;

    public void setSymbol(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol(){
        return symbol;
    }

    public void setVolumeUsd24Hr(String volumeUsd24Hr){
        this.volumeUsd24Hr = volumeUsd24Hr;
    }

    public String getVolumeUsd24Hr(){
        return volumeUsd24Hr;
    }

    public void setMarketCapUsd(String marketCapUsd){
        this.marketCapUsd = marketCapUsd;
    }

    public String getMarketCapUsd(){
        return marketCapUsd;
    }

    public void setPriceUsd(String priceUsd){
        this.priceUsd = priceUsd;
    }

    public String getPriceUsd(){
        return priceUsd;
    }

    public void setVwap24Hr(String vwap24Hr){
        this.vwap24Hr = vwap24Hr;
    }

    public String getVwap24Hr(){
        return vwap24Hr;
    }

    public void setChangePercent24Hr(String changePercent24Hr){
        this.changePercent24Hr = changePercent24Hr;
    }

    public String getChangePercent24Hr(){
        return changePercent24Hr;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setRank(String rank){
        this.rank = rank;
    }

    public String getRank(){
        return rank;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setMaxSupply(String maxSupply){
        this.maxSupply = maxSupply;
    }

    public String getMaxSupply(){
        return maxSupply;
    }

    public void setSupply(String supply){
        this.supply = supply;
    }

    public String getSupply(){
        return supply;
    }

    @Override
    public String toString(){
        return
                "Data{" +
                        "symbol = '" + symbol + '\'' +
                        ",volumeUsd24Hr = '" + volumeUsd24Hr + '\'' +
                        ",marketCapUsd = '" + marketCapUsd + '\'' +
                        ",priceUsd = '" + priceUsd + '\'' +
                        ",vwap24Hr = '" + vwap24Hr + '\'' +
                        ",changePercent24Hr = '" + changePercent24Hr + '\'' +
                        ",name = '" + name + '\'' +
                        ",rank = '" + rank + '\'' +
                        ",id = '" + id + '\'' +
                        ",maxSupply = '" + maxSupply + '\'' +
                        ",supply = '" + supply + '\'' +
                        "}";
    }
}
