package cn.guet.navigator.web.dto;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Iterator;

/**
 * @author Administrator
 */
public class PageData<T> implements Serializable {

    public Page requestToPage(HttpServletRequest request) {
        Page<T> page = new Page<T>();
        String aoData = request.getParameter("aoData");
        JSONArray jsonArray = JSONArray.parseArray(aoData);
        Iterator<Object> iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            JSONObject jsonObject = (JSONObject) iterator.next();
            String name = (String) jsonObject.get("name");
            Object value = jsonObject.get("value");
            if (name.equals("sEcho")) {
                if (!StringUtils.isEmpty(value)) {
                    page.setsEcho(Integer.valueOf(value.toString()));
                }
            } else if (name.equals("iDisplayStart")) {
                if (!StringUtils.isEmpty(value)) {
                    page.setiDisplayStart(Integer.valueOf(value.toString()));
                }
            } else if (name.equals("iDisplayLength")) {
                if (!StringUtils.isEmpty(value)) {
                    page.setiDisplayLength(Integer.valueOf(value.toString()));
                }
            }
        }
        return page;
    }

    public PageData() {
    }


}
