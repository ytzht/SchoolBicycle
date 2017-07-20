package com.school.bicycle.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */

public class GetMyMessage {


    /**
     * code : 1
     * msg : 操作成功
     * message : [{"message_id":43,"title":"校易行开张了！！！","content":"<p>欢迎您的加入，我是机器人小X<br/><\/p><p><br/><\/p>","state":"成功","read":0}]
     */

    private int code;
    private String msg;
    private List<MessageBean> message;

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

    public List<MessageBean> getMessage() {
        return message;
    }

    public void setMessage(List<MessageBean> message) {
        this.message = message;
    }

    public static class MessageBean {
        /**
         * message_id : 43
         * title : 校易行开张了！！！
         * content : <p>欢迎您的加入，我是机器人小X<br/></p><p><br/></p>
         * state : 成功
         * read : 0
         */

        private int message_id;
        private String title;
        private String content;
        private String state;
        private int read;

        public int getMessage_id() {
            return message_id;
        }

        public void setMessage_id(int message_id) {
            this.message_id = message_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getRead() {
            return read;
        }

        public void setRead(int read) {
            this.read = read;
        }
    }
}
