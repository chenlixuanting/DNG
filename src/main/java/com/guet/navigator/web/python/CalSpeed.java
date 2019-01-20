package com.guet.navigator.web.python;

import com.guet.navigator.web.pojo.backup.TrainSpeed;
import org.python.core.PyFunction;
import org.python.core.PyList;
import org.python.util.PythonInterpreter;

import java.util.List;
import java.util.Properties;

/**
 * @author Administrator
 */
public class CalSpeed {

    private static PythonInterpreter interpreter;
    private static PyFunction func;

    static {
        Properties props = new Properties();
        props.put("python.home", "path to the Lib folder");
        props.put("python.console.encoding", "UTF-8");
        props.put("python.security.respectJavaAccessibility", "false");
        props.put("python.import.site", "false");
        Properties preprops = System.getProperties();
        PythonInterpreter.initialize(preprops, props, new String[0]);
        interpreter = new PythonInterpreter();
        interpreter.execfile("E:\\speed.py");
        func = (PyFunction) interpreter.get("generate_rate", PyFunction.class);
    }

    /**
     * 计算平均速度
     *
     * @param trainSpeedList
     * @return
     */
    public static String cal(List<TrainSpeed> trainSpeedList) {
        return func.__call__(new PyList(trainSpeedList)).toString();
    }

}
