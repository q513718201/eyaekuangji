package com.hazz.whb.mvp.model.bean;

import java.util.List;

public class CurrentInventBean {


    /**
     * orders :             [{"id":"4","user_id":"14","amount":"200","coin":"WHB","length":"15","return_rate":"0.1","status":"0","number":"7a810dc7fa58cd68a5679590443c708a","created_at":"2020-10-29 22:50:34","updated_at":"2020-10-30 02:09:45","revenue":4,"days":3},{"id":"5","user_id":"14","amount":"200","coin":"WHB","length":"15","return_rate":"0.1","status":"0","number":"7a810dc7fa58cd68a5679590443c708c","created_at":"2020-10-30 02:09:14","updated_at":"2020-10-30 02:09:14","revenue":4,"days":3},{"id":"6","user_id":"14","amount":"200","coin":"WHB","length":"15","return_rate":"0.1","status":"0","number":"44b7043a2800be9194f43a5d40c59945","created_at":"2020-10-30 02:12:05","updated_at":"2020-10-30 02:12:05","revenue":4,"days":3}]
     * completed_orders :   [{"id":"1","user_id":"14","amount":"200","coin":"WHB","length":"15","return_rate":"0.1","status":"2","number":null,"created_at":"2020-10-29 22:13:01","updated_at":"2020-10-29 22:42:00","revenue":20,"days":"15"},{"id":"2","user_id":"14","amount":"200","coin":"WHB","length":"15","return_rate":"0.1","status":"2","number":null,"created_at":"2020-10-25 22:42:38","updated_at":"2020-10-30 01:33:09","revenue":20,"days":"15"},{"id":"3","user_id":"14","amount":"200","coin":"WHB","length":"15","return_rate":"0.1","status":"2","number":null,"created_at":"2020-10-29 22:46:57","updated_at":"2020-10-29 22:47:20","revenue":20,"days":"15"}]
     * invest : 1200
     * revenue : 72
     */

    private String invest;
    private String revenue;
    private List<OrdersBean> orders;
    private List<CompletedOrdersBean> completed_orders;

    public String getInvest() {
        return invest;
    }

    public void setInvest(String invest) {
        this.invest = invest;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public List<OrdersBean> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdersBean> orders) {
        this.orders = orders;
    }

    public List<CompletedOrdersBean> getCompleted_orders() {
        return completed_orders;
    }

    public void setCompleted_orders(List<CompletedOrdersBean> completed_orders) {
        this.completed_orders = completed_orders;
    }

    public static class OrdersBean {
        /**
         * id : 4
         * user_id : 14
         * amount : 200
         * coin : WHB
         * length : 15
         * return_rate : 0.1
         * status : 0
         * number : 7a810dc7fa58cd68a5679590443c708a
         * created_at : 2020-10-29 22:50:34
         * updated_at : 2020-10-30 02:09:45
         * revenue : 4
         * days : 3
         */

        private String id;
        private String user_id;
        private String amount;
        private String coin;
        private String length;
        private String return_rate;
        private int status;
        private String number;
        private String created_at;
        private String updated_at;
        private String revenue;
        private String days;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
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

        public String getRevenue() {
            return revenue;
        }

        public void setRevenue(String revenue) {
            this.revenue = revenue;
        }

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }
    }

    public static class CompletedOrdersBean {
        /**
         * id : 1
         * user_id : 14
         * amount : 200
         * coin : WHB
         * length : 15
         * return_rate : 0.1
         * status : 2
         * number : null
         * created_at : 2020-10-29 22:13:01
         * updated_at : 2020-10-29 22:42:00
         * revenue : 20
         * days : 15
         */

        private String id;
        private String user_id;
        private String amount;
        private String coin;
        private String length;
        private String return_rate;
        private String status;
        private Object number;
        private String created_at;
        private String updated_at;
        private String revenue;
        private String days;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getNumber() {
            return number;
        }

        public void setNumber(Object number) {
            this.number = number;
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

        public String getRevenue() {
            return revenue;
        }

        public void setRevenue(String revenue) {
            this.revenue = revenue;
        }

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }
    }
}


