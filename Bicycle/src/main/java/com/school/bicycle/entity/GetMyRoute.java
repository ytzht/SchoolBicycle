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
     * body : [{"rid":1,"user_id":12,"bike_number":"012011","start_time":"2017-06-24 21:26:34","end_time":"2017-06-24 21:46:37","time_span":"11","total_fee":2.2,"distance":2.3,"calories":23,"carbon_saved":100,"status":true,"create_time":"2017-06-24 21:50:14","lines":[{"lid":1,"user_id":12,"bike_number":"012011","rid":1,"lat":37.477225,"log":121.477225,"create_time":"2017-06-24 21:56:18"},{"lid":2,"user_id":12,"bike_number":"012011","rid":1,"lat":121.442689,"log":37.477634,"create_time":"2017-06-24 21:56:59"}]}]
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

    public static class BodyBean implements Serializable {
        /**
         * rid : 1
         * user_id : 12
         * bike_number : 012011
         * start_time : 2017-06-24 21:26:34
         * end_time : 2017-06-24 21:46:37
         * time_span : 11
         * total_fee : 2.2
         * distance : 2.3
         * calories : 23
         * carbon_saved : 100
         * status : true
         * create_time : 2017-06-24 21:50:14
         * lines : [{"lid":1,"user_id":12,"bike_number":"012011","rid":1,"lat":37.477225,"log":121.477225,"create_time":"2017-06-24 21:56:18"},{"lid":2,"user_id":12,"bike_number":"012011","rid":1,"lat":121.442689,"log":37.477634,"create_time":"2017-06-24 21:56:59"}]
         */

        private int rid;
        private int user_id;
        private String bike_number;
        private String start_time;
        private String end_time;
        private String time_span;
        private double total_fee;
        private double distance;
        private int calories;
        private int carbon_saved;
        private boolean status;
        private String create_time;
        private List<LinesBean> lines;

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

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
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
             * lid : 1
             * user_id : 12
             * bike_number : 012011
             * rid : 1
             * lat : 37.477225
             * log : 121.477225
             * create_time : 2017-06-24 21:56:18
             */

            private int lid;
            private int user_id;
            private String bike_number;
            private int rid;
            private double lat;
            private double log;
            private String create_time;

            public int getLid() {
                return lid;
            }

            public void setLid(int lid) {
                this.lid = lid;
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

            public int getRid() {
                return rid;
            }

            public void setRid(int rid) {
                this.rid = rid;
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
