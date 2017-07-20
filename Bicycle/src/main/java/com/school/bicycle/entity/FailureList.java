package com.school.bicycle.entity;

import java.util.List;

/**
 * Created by ytzht on 2017/07/20 下午9:11
 */

public class FailureList {

    /**
     * code : 1
     * msg : 操作成功
     * problems : [{"pro_id":1,"problem":"车锁打不开"},{"pro_id":2,"problem":"轮胎气不足"},{"pro_id":3,"problem":"刹车失灵"},{"pro_id":4,"problem":"变速故障"},{"pro_id":5,"problem":"断车链"},{"pro_id":6,"problem":"牙盘松动"},{"pro_id":7,"problem":"爆胎"}]
     */

    private int code;
    private String msg;
    private List<ProblemsBean> problems;

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

    public List<ProblemsBean> getProblems() {
        return problems;
    }

    public void setProblems(List<ProblemsBean> problems) {
        this.problems = problems;
    }

    public static class ProblemsBean {
        /**
         * pro_id : 1
         * problem : 车锁打不开
         */

        private int pro_id;
        private String problem;

        public boolean isClick() {
            return isClick;
        }

        public void setClick(boolean click) {
            isClick = click;
        }

        private boolean isClick = false;

        public int getPro_id() {
            return pro_id;
        }

        public void setPro_id(int pro_id) {
            this.pro_id = pro_id;
        }

        public String getProblem() {
            return problem;
        }

        public void setProblem(String problem) {
            this.problem = problem;
        }
    }
}
