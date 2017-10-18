package org.cityu.cs.ian.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * 说明:配置文件读取工具
 */
public class PropertiesUtil {
    public static String readValue(String filePath, String key) {
        Properties props = new Properties();
        try {
            InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
            props.load(stream);
            String value = props.getProperty(key);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
