package com.school.bicycle.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */

public class GetMyRoute implements Serializable {


    /**
     * code : 1
     * msg : 操作成功
     * body : [{"rid":888,"order_number":"H20170810012643449003","user_id":114,"bike_number":"012099","start_time":"2017-08-10 01:13:00","end_time":"2017-08-10 01:26:40","time_span":"13","total_fee":2.5,"distance":1105.98,"calories":552,"carbon_saved":110,"status":1,"pay_status":1,"route_type":2,"coupon_used":0,"create_time":"2017-08-10 01:13:00","lines":[{"lid":18303,"rid":888,"user_id":114,"bike_number":"012099","log":121.441451,"lat":37.477163,"create_time":"2017-08-10 01:13:00","pin":0}]}]
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

    public static class BodyBean implements Serializable{
        /**
         * rid : 888
         * order_number : H20170810012643449003
         * user_id : 114
         * bike_number : 012099
         * start_time : 2017-08-10 01:13:00
         * end_time : 2017-08-10 01:26:40
         * time_span : 13
         * total_fee : 2.5
         * distance : 1105.98
         * calories : 552
         * carbon_saved : 110
         * status : 1
         * pay_status : 1
         * route_type : 2
         * coupon_used : 0
         * create_time : 2017-08-10 01:13:00
         * lines : [{"lid":18303,"rid":888,"user_id":114,"bike_number":"012099","log":121.441451,"lat":37.477163,"create_time":"2017-08-10 01:13:00","pin":0}]
         */

        private int rid;
        private String order_number;
        private int user_id;
        private String bike_number;
        private String start_time;
        private String end_time;
        private String time_span;
        private double total_fee;
        private double distance;
        private int calories;
        private int carbon_saved;
        private int status;
        private int pay_status;
        private int route_type;
        private int coupon_used;
        private String create_time;
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

        public double getTotal_fee() {
            return total_fee;
        }

        public void setTotal_fee(double total_fee) {
            this.total_fee = total_fee;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getPay_status() {
            return pay_status;
        }

        public void setPay_status(int pay_status) {
            this.pay_status = pay_status;
        }

        public int getRoute_type() {
            return route_type;
        }

        public void setRoute_type(int route_type) {
            this.route_type = route_type;
        }

        public int getCoupon_used() {
            return coupon_used;
        }

        public void setCoupon_used(int coupon_used) {
            this.coupon_used = coupon_used;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public List<LinesBean> getLines() {
            return lines;
        }

        public void setLines(List<LinesBean> lines) {
            this.lines = lines;
        }

        public static class LinesBean implements Serializable{
            /**
             * lid : 18303
             * rid : 888
             * user_id : 114
             * bike_number : 012099
             * log : 121.441451
             * lat : 37.477163
             * create_time : 2017-08-10 01:13:00
             * pin : 0
             */

            private int lid;
            private int rid;
            private int user_id;
            private String bike_number;
            private double log;
            private double lat;
            private String create_time;
            private int pin;

            public int getLid() {
                return lid;
            }

            public void setLid(int lid) {
                this.lid = lid;
            }

            public int getRid() {
                return rid;
            }

            public void setRid(int rid) {
                this.rid = rid;
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

            public double getLog() {
                return log;
            }

            public void setLog(double log) {
                this.log = log;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public int getPin() {
                return pin;
            }

            public void setPin(int pin) {
                this.pin = pin;
            }
        }
    }
}
