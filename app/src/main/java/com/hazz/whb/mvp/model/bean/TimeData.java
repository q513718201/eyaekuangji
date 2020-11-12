package com.hazz.whb.mvp.model.bean;


/**
 * 分红池选择时长
 */
public class TimeData {

    /**
     * id : 1
     * length : 15
     * return_rate : 0.1
     * coin : WHB
     * created_at : 2020-10-29 21:52:49
     * updated_at : 2020-10-29 21:52:49
     */

    private String id;
    private String length;
    private String return_rate;
    private String coin;
    private String created_at;
    private String updated_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getReturn_rate() {
        return return_rate;
    }

    public void setReturn_rate(String return_rate) {
        this.return_rate = return_rate;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
