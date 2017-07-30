package com.school.bicycle.entity;

import java.util.List;

/**
 * Created by ytzht on 2017/07/30 下午2:25
 */

public class SharedBikeList {


    /**
     * code : 1
     * msg : 操作成功
     * body : [{"sid":100,"number":"011007","user_id":33,"start_time":"2017-07-31","lease_status":0},{"sid":101,"number":"011007","user_id":33,"start_time":"2017-08-01","lease_status":0},{"sid":102,"number":"011007","user_id":33,"start_time":"2017-08-02","lease_status":0}]
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
         * sid : 100
         * number : 011007
         * user_id : 33
         * start_time : 2017-07-31
         * lease_status : 0
         */

        private int sid;
        private String number;
        private int user_id;
        private String start_time;
        private int lease_status;

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public int getLease_status() {
            return lease_status;
        }

        public void setLease_status(int lease_status) {
            this.lease_status = lease_status;
        }
    }
}
