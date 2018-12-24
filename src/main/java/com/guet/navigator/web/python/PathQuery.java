package com.guet.navigator.web.python;

import com.guet.navigator.web.pojo.Road;
import org.python.core.PyFloat;
import org.python.core.PyFunction;
import org.python.core.PyList;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.util.List;
import java.util.Properties;

public class PathQuery {

    static {
        Properties props = new Properties();
        props.put("python.home", "path to the Lib folder");
        props.put("python.console.encoding", "UTF-8");
        props.put("python.security.respectJavaAccessibility", "false");
        props.put("python.import.site", "false");
        Properties preprops = System.getProperties();
        PythonInterpreter.initialize(preprops, props, new String[0]);
    }

    public static String query(Double deviceLongitude, Double deviceLatitude, List<Road> roadList){
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("E:\\py_find_road.py");
        PyFunction func = (PyFunction)interpreter.get("get_car_to_road",PyFunction.class);
        PyObject pyobj = func.__call__(new PyFloat(deviceLongitude),new PyFloat(deviceLatitude),new PyList(roadList));
        return pyobj.toString();
    }

}
