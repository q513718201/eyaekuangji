package com.hazz.whb.mvp.model;

import java.util.List;

public class DuihuanRecord {

        /**
         * pageNum : 1
         * pageSize : 500
         * total : 2
         * list : [{"id":"2","user_id":"2","coin_from":"WHB","coin_from_amount":"500","coin_to":"USDT","coin_to_amount":"400","number":"2a9be8748cf891889ead04c84d15c587","created_at":"2020-07-21 05:52:25","updated_at":"2020-07-21 05:52:25"},{"id":"1","user_id":"2","coin_from":"WHB","coin_from_amount":"2000","coin_to":"USDT","coin_to_amount":"1600","number":"73905e859b940b05bad6c935f971ab45","created_at":"2020-07-21 05:51:57","updated_at":"2020-07-21 05:51:57"}]
         */

        public int pageNum;
        public int pageSize;
        public String total;
        public List<ListBean> list;

        public static class ListBean {
            /**
             * id : 2
             * user_id : 2
             * coin_from : WHB
             * coin_from_amount : 500
             * coin_to : USDT
             * coin_to_amount : 400
             * number : 2a9be8748cf891889ead04c84d15c587
             * created_at : 2020-07-21 05:52:25
             * updated_at : 2020-07-21 05:52:25
             */

            public String id;
            public String user_id;
            public String coin_from;
            public String coin_from_amount;
            public String coin_to;
            public String coin_to_amount;
            public String number;
            public String created_at;
            public String updated_at;
        }

}
