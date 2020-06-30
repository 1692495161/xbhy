package com.cjj.dao;

import com.cjj.utils.JDBCUtil;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author cjj
 * @date 2020/6/22
 * @description
 */
public class TemplateDao {
    JdbcTemplate template=new JdbcTemplate(JDBCUtil.getDataSource());
}
