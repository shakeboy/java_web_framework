package com.shakeboy.util;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

import static com.alibaba.druid.sql.ast.SQLPartitionValue.Operator.List;

public class SpringJDBC {
    /**
     * Spring对JDBC的简单封装
     * JavaEE的灵魂框架，提供了JDBCTemplate对象简化了JDBC开发
     * 1.导入jar包 spring-core spring-beans spring-express spring-context commons-logging spring-jdbc
     * 2.创建JDBCTemplate对象，依赖于DataSource
     *      JdbcTemplate template = new JdbcTemplate(ds)
     * 3.调用JdbcTemplate的方法进行CRUD操作
     *      update():执行DML操作，增删改
     *      queryForMap():查询结果将结果封装成map集合
     *              返回结果只能是一个
     *      queryForList():查询结果将结果封装成list集合
     *      query():查询结果将结果封装成JavaBean对象
     *          RowMapper-->手动封装
     *          BeanPropertyRowMapper-->自动封装
     *      queryForObject():查询结果，将结果封装成对象
     *
     */
    public static void main(String[] args) {
        // 1.导入jar包
        // 2.创建对象
        JdbcTemplate template = new JdbcTemplate(DataSourceUtils.getDataSource());
        // 调用方法
        String sql = "select * from users";
        java.util.List<Map<String, Object>> maps = template.queryForList(sql);
        System.out.println(maps);
    }
}
