package com.shakeboy.jdbc;

import com.shakeboy.domain.Admin;
import com.shakeboy.util.DataSourceUtils;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class JdbcTemplateDemo {
    private JdbcTemplate template = new JdbcTemplate(DataSourceUtils.getDataSource());
    /**
     * 单元测试Junit
     */
    @Test
    public void test1(){
        // 添加一条记录
        String sql = "insert into admins values(?,?,?)";
        template.update(sql,"water","123","admin");
    }
    @Test
    public void test2(){
        // 查询记录
        String sql = "select * from admins";
        List<Map<String, Object>> list = template.queryForList(sql);
        System.out.println(list);
    }
    @Test
    public void test3(){
        // 修改一条记录
        String sql = "update admins set a_name=? where a_password=?";
        template.update(sql,"watertest","123");
    }
    @Test
    public void test4(){
        // 删除一条记录
        String sql = "delete from admins where a_password=?";
        template.update(sql,"123");
    }
    @Test
    public void test5(){
        // 封装成对象
        String sql = "select * from admins";
        List<Admin> list = template.query(sql, new RowMapper<Admin>() {
            @Override
            public Admin mapRow(ResultSet resultSet, int i) throws SQLException {
                Admin admin = new Admin();
                admin.setA_name(resultSet.getString("a_name"));
                admin.setA_role(resultSet.getString("a_role"));
                admin.setA_password(resultSet.getString("a_password"));
                return admin;
            }
        });
        for (Admin admin : list) {
            System.out.println(admin);
        }
    }
    @Test
    public void test51(){
        // 封装成对象 BeanPropertyRowMapper
        String sql = "select * from admins";
        List<Admin> list = template.query(sql, new BeanPropertyRowMapper<Admin>(Admin.class));
        for (Admin admin : list) {
            System.out.println(admin);
        }
    }
    @Test
    public void test6(){
        // 查询总的记录数
        String sql = "select count(a_name) from admins";
        int count = template.queryForObject(sql, Integer.class);
        System.out.println(count);
    }
}
