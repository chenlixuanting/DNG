package cn.guet.navigator.web.vo;

import java.io.Serializable;

/**
 * 登陆信息实体
 */
public class LoginMessageVo implements Serializable {

    private static final long serialVersionUID = 7969298973420723766L;

    private boolean head;

    private Object body;

    public boolean isHead() {
        return head;
    }

    public void setHead(boolean head) {
        this.head = head;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public LoginMessageVo(boolean head, Object body) {
        this.head = head;
        this.body = body;
    }

    public static LoginMessageVo loginSuccessMessage(Object body) {
        return new LoginMessageVo(true, body);
    }

    public static LoginMessageVo loginFailMessage(Object body) {
        return new LoginMessageVo(false, body);
    }

    @Override
    public String toString() {
        return "LoginMessageVo{" +
                "head=" + head +
                ", body=" + body +
                '}';
    }
}
