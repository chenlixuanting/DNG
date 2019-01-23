package cn.guet.navigator.web.listener;

import cn.guet.navigator.web.constant.user.DeviceConstant;
import org.springframework.util.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听sess的创建与销毁
 *
 * @author Administrator
 */
public class SessionListener implements HttpSessionListener {

    /**
     * session创建时执行
     *
     * @param httpSessionEvent
     */
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }

    /**
     * session销毁时执行
     *
     * @param httpSessionEvent
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

        //获取session
        HttpSession httpSession = httpSessionEvent.getSession();

        //从Session中获取qrCodeStr
        String qrCodeStr = (String) httpSession.getAttribute(DeviceConstant.QRCODE_STR);

        //判断该qrCodeStr是否为null
        if (!StringUtils.isEmpty(qrCodeStr)) {

            //获取servletContext
            ServletContext servletContext = httpSession.getServletContext();

            //从servletContext中移除qrCodeStr
            servletContext.removeAttribute(qrCodeStr);

        }

    }

}
