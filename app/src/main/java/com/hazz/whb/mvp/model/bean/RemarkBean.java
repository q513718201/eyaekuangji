package com.hazz.whb.mvp.model.bean;

public class RemarkBean {

    /**
     * fee_rate : 0.0051
     * fee_amount : 0.102WHB
     * get_amount : 6.8148830085493
     * rate : 0.2919786
     * external_wallet_address : 123456
     */

    private double fee_rate;
    private String fee_amount;
    private String get_amount;
    private String rate;
    private String external_wallet_address;

    public double getFee_rate() {
        return fee_rate;
    }

    public void setFee_rate(double fee_rate) {
        this.fee_rate = fee_rate;
    }

    public String getFee_amount() {
        return fee_amount;
    }

    public void setFee_amount(String fee_amount) {
        this.fee_amount = fee_amount;
    }

    public String getGet_amount() {
        return get_amount;
    }

    public void setGet_amount(String get_amount) {
        this.get_amount = get_amount;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getExternal_wallet_address() {
        return external_wallet_address;
    }

    public void setExternal_wallet_address(String external_wallet_address) {
        this.external_wallet_address = external_wallet_address;
    }
}
