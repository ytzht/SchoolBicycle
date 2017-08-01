package com.school.bicycle.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */

public class GetMyRoute implements Serializable {


    /**
     * code : 1
     * msg : 操作成功
     * body : [{"rid":172,"order_number":"H20170731184413399006","user_id":32,"bike_number":"011094","start_time":"2017-07-31 18:43:51","end_time":"2017-07-31 18:44:07","time_span":"0","total_fee":0.02,"distance":0,"calories":0,"carbon_saved":0,"status":true,"pay_status":true,"lines":[]},{"rid":174,"order_number":"H20170731184544743007","user_id":32,"bike_number":"011094","start_time":"2017-07-31 18:45:17","end_time":"2017-07-31 18:45:43","time_span":"0","total_fee":0.02,"distance":0,"calories":0,"carbon_saved":0,"status":true,"pay_status":true,"lines":[{"lid":5384,"user_id":32,"bike_number":"011094","rid":174,"lat":37.477148,"log":121.441372,"create_time":"2017-07-31 18:45:17"}]}]
     */

    private int code;
    private String msg;
    private List<BodyBean> body;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class BodyBean {
        /**
         * rid : 172
         * order_number : H20170731184413399006
         * user_id : 32
         * bike_number : 011094
         * start_time : 2017-07-31 18:43:51
         * end_time : 2017-07-31 18:44:07
         * time_span : 0
         * total_fee : 0.02
         * distance : 0.0
         * calories : 0
         * carbon_saved : 0
         * status : true
         * pay_status : true
         * lines : []
         */

        private int rid;
        private String order_number;
        private int user_id;
        private String bike_number;
        private String start_time;
        private String end_time;
        private String time_span;
        private String total_fee;
        private String distance;
        private int calories;
        private int carbon_saved;
        private boolean status;
        private boolean pay_status;
        private List<LinesBean> lines;

        public int getRid() {
            return rid;
        }

        public void setRid(int rid) {
            this.rid = rid;
        }

        public String getOrder_number() {
            return order_number;
        }

        public void setOrder_number(String order_number) {
            this.order_number = order_number;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getBike_number() {
            return bike_number;
        }

        public void setBike_number(String bike_number) {
            this.bike_number = bike_number;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getTime_span() {
            return time_span;
        }

        public void setTime_span(String time_span) {
            this.time_span = time_span;
        }

        public String getTotal_fee() {
            return total_fee;
        }

        public void setTotal_fee(String total_fee) {
            this.total_fee = total_fee;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public int getCalories() {
            return calories;
        }

        public void setCalories(int calories) {
            this.calories = calories;
        }

        public int getCarbon_saved() {
            return carbon_saved;
        }

        public void setCarbon_saved(int carbon_saved) {
            this.carbon_saved = carbon_saved;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public boolean isPay_status() {
            return pay_status;
        }

        public void setPay_status(boolean pay_status) {
            this.pay_status = pay_status;
        }

        public List<LinesBean> getLines() {
            return lines;
        }

        public void setLines(List<LinesBean> lines) {
            this.lines = lines;
        }


        public static class LinesBean {
            /**
             * lid : 5384
             * user_id : 32
             * bike_number : 011094
             * rid : 174
             * lat : 37.477148
             * log : 121.441372
             * create_time : 2017-07-31 18:45:17
             */

            private int lid;
            @SerializedName("user_id")
            private int user_idX;
            @SerializedName("bike_number")
            private String bike_numberX;
            @SerializedName("rid")
            private int ridX;
            private double lat;
            private double log;
            private String create_time;

            public int getLid() {
                return lid;
            }

            public void setLid(int lid) {
                this.lid = lid;
            }

            public int getUser_idX() {
                return user_idX;
            }

            public void setUser_idX(int user_idX) {
                this.user_idX = user_idX;
            }

            public String getBike_numberX() {
                return bike_numberX;
            }

            public void setBike_numberX(String bike_numberX) {
                this.bike_numberX = bike_numberX;
            }

            public int getRidX() {
                return ridX;
            }

            public void setRidX(int ridX) {
                this.ridX = ridX;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLog() {
                return log;
            }

            public void setLog(double log) {
                this.log = log;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }
    }
}
