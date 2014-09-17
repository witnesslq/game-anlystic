package cn.mob.node;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.util.List;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/17
 */
public class Conf {

    private static PropertiesConfiguration conf = null;

    static {
        try {
            conf = new PropertiesConfiguration(Constants.CONF_NAME);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

    }

    public static String getString(String key) {
        return conf.getString(key);
    }

    public static List<Object> getList(String key) {
        return conf.getList(key);
    }

}
