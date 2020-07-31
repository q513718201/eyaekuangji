package com.hazz.whb.mvp.model;

import java.util.List;

public class DuihuanRecord {


        /**
         * pageNum : 1
         * pageSize : 500
         * total : 6
         * list : [{"id":"8","user_id":"5","coin_from":"WHB","coin_from_amount":"500","coin_to":"USDT","coin_to_amount":"400","number":"75816c07f72be427918681c73b960672","created_at":"2020-07-28 19:46:08","updated_at":"2020-07-28 19:46:08","fee":"0"},{"id":"7","user_id":"5","coin_from":"WHB","coin_from_amount":"500","coin_to":"USDT","coin_to_amount":"400","number":"0c4dbbb718faeaa0c8706a229c7d5a44","created_at":"2020-07-28 19:38:31","updated_at":"2020-07-28 19:38:31","fee":"0"},{"id":"6","user_id":"5","coin_from":"WHB","coin_from_amount":"506","coin_to":"USDT","coin_to_amount":"404.8","number":"c3b6cf65ae84bac6025370b2a53add73","created_at":"2020-07-28 18:08:43","updated_at":"2020-07-28 18:08:43","fee":"0"},{"id":"5","user_id":"5","coin_from":"WHB","coin_from_amount":"501","coin_to":"USDT","coin_to_amount":"400.8","number":"bc782674ee87f1fc368db0f22ed192dc","created_at":"2020-07-28 18:05:31","updated_at":"2020-07-28 18:05:31","fee":"0"},{"id":"4","user_id":"5","coin_from":"WHB","coin_from_amount":"500","coin_to":"USDT","coin_to_amount":"400","number":"61655e46d9d089a1937e6bd829c6179c","created_at":"2020-07-28 17:38:50","updated_at":"2020-07-28 17:38:50","fee":"0"},{"id":"3","user_id":"5","coin_from":"WHB","coin_from_amount":"500","coin_to":"USDT","coin_to_amount":"400","number":"3257160fb32626c04f463c9ea22564d2","created_at":"2020-07-28 14:24:52","updated_at":"2020-07-28 14:24:52","fee":"0"}]
         */

        public int pageNum;
        public int pageSize;
        public String total;
        public List<ListBean> list;

        public static class ListBean {
            /**
             * id : 8
             * user_id : 5
             * coin_from : WHB
             * coin_from_amount : 500
             * coin_to : USDT
             * coin_to_amount : 400
             * number : 75816c07f72be427918681c73b960672
             * created_at : 2020-07-28 19:46:08
             * updated_at : 2020-07-28 19:46:08
             * fee : 0
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
            public String fee;
        }

}
