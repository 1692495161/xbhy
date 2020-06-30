package com.cjj.utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author cjj
 * @date 2020/6/22
 * @description
 */
public class JDBCUtil {
    //获取一个数据源
    private static DruidDataSource dataSource = null;

    static {
        InputStream is = null;
        try {
            Properties prop = new Properties();
            is = JDBCUtil.class.getClassLoader().getResourceAsStream("p.properties");
            prop.load(is);
            //声明一个数据源
            dataSource = new DruidDataSource();
            dataSource.setDriverClassName(prop.getProperty("jdbc.driver"));
            dataSource.setUrl(prop.getProperty("jdbc.url"));
            dataSource.setUsername(prop.getProperty("jdbc.user"));
            dataSource.setPassword(prop.getProperty("jdbc.password"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DruidDataSource getDataSource() {
        return dataSource;
    }
}
