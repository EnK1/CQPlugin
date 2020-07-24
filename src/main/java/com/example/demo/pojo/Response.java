package com.example.demo.pojo;

import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("data")
    private Data data;

    @SerializedName("timestamp")
    private long timestamp;

    public void setData(Data data){
        this.data = data;
    }

    public Data getData(){
        return data;
    }

    public void setTimestamp(long timestamp){
        this.timestamp = timestamp;
    }

    public long getTimestamp(){
        return timestamp;
    }

    @Override
    public String toString(){
        return
                "Response{" +
                        "data = '" + data + '\'' +
                        ",timestamp = '" + timestamp + '\'' +
                        "}";
    }
}
