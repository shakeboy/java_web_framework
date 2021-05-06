package com.shakeboy.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCTest {
    /**
     * 1.概念性东西：java database connection:java数据库连接
     * 2.可不可以操作不同的数据库（MySQL,Oracle,DB2......）
     *      期望使用统一的一套数据库连接代码就行数据库连接===可以实现---》sun公司
     *      JDBC定义了操作所有关系型数据库的规则（一套规范---接口）
     *
     *      每个数据库使用者只需要重写方法即可（实现类）
     *      JDBC本质：其实就是官方（sun公司）定义的一套规范===接口,各个数据厂商去实现这套接口提供数据库驱动的jar包
     *      饿哦们可以使用这套JDBC规范进行操作数据库，真正执行的是驱动jar包中的实现类
     * 3.快速入门
     *      a.导入驱动jar包
     *      b.编写代码，注册驱动
     *      c.获取数据库连接对象connection
     *      d.定义SQL语句
     *      e.获取执行SQL语句的对象Statement
     *      f.执行SQL,接受返回结果
     *      g.处理结果
     *      h.释放资源
     */
    public static void main(String[] args) throws Exception {
        // 1.导入驱动jar包===不同数据库不同jar包===>add as libraries
        // 2.注册驱动
        Class.forName("com.mysql.jdbc.Driver"); // 在mysql5版本以后的jar包可以省略
//        com.mysql.jdbc.Driver
//        com.mysql.fabric.jdbc.FabricMySQLDriver
        // 3.获取连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sharebook?useSSL=true", "root", "root");
//        Connection connection = DriverManager.getConnection("jdbc:mysql:///sharebook?useSSL=true", "root", "");//简写
        // 4.定义sql语句
        String sql = "select * from users";
        // 5.获取数据库操作对象Statement
        Statement statement = connection.createStatement();
//        connection.prepareStatement(sql);
        boolean b = statement.execute(sql);
        // 6.操作结果
        System.out.println(b);
        // 7.释放资源
        statement.close();
        connection.close();
    }
    /**
     *  详解各个对象
     *  1.DriverManager:驱动管理对象
     *      a.注册驱动===告诉我们程序使用哪一个数据库操作的jar包
     *          static void registerDriver(Driver driver):注册于给定的驱动程序DriverManager
     *              使用：Class.forName("com.mysql.jdbc.Driver");
     *              通过查看源码发现：在com.mysql.jdbc.Driver中存在静态代码块
     *              static {
     *                   try {
     *                      DriverManager.registerDriver(new Driver());
     *                      }
     *                   catch (SQLException var1) {
     *                      throw new RuntimeException("Can't register driver!");
     *                   }
     *              }
     *      b.获取数据库连接
     *          public static Connection getConnection(String url,java.util.Properties info) throws SQLException {
     *               return (getConnection(url, info, Reflection.getCallerClass()));
     *           }
     *           参数：
     *              url: jdbc:mysql://localhost:3306/sharebook?useSSL=true
     *              user:'root'
     *              password:
     *  2.Connection：数据库连接对象
     *      a.获取执行SQL的对象crateStatement//preparedStatement
     *      b.管理事务
     *          开启事务：void setAutoCommit(Boolean autoCommit)
     *          提交事务:commit()
     *          回滚事务:rollBack()
     *  3.Statement：数据库操作对象,用于执行静态SQL和返回结果对象
     *      a.boolean execute(String sql)---了解
     *      b.int executeUpdate(String sql)---执行DML（insert，update，delete）语句 、DDL（create，alter，drop）
     *          返回值表示影响行数===通过影响行数判断SQL语句是否成功
     *      c.ResultSet executeQuery(String sql):执行DQL（select）语句
     *  4.ResultSet：结果集对象
     *      封装查询的结果
     *      a.next() 游标向下移动一行
     *      b.getXxx(参数) 获取数据  Xxx代表数据类型 如getInt()返回int型的值
     *          参数：1.int型值，代表列的编号（从1开始）  2.String：代表列的名称
     *      1.游标向下移动
     *      2.判断查询
     *  5.PreparedStatement：数据库操作对象（功能比statement更强大）
     *     效率更高，防止SQL注入
     *      sql注入问题：在拼接sql语句时，会有一些特殊关键字参与字符串的拼接，造成安全性的问题===》使用preparedStatement来解决
     *          预编译的SQL：参数使用？作为占位符，给?符号参数
     *      请输入用户名：
     *      fads
     *      请输入密码
     *      sda 'or'a'='a
     *      fads===sda'or'a'='a
     *      登陆成功
     *  6.查询emp表的所有数据，封装成对象
     *  7.JDBC控制事务
     *  事务：一个包含多个步骤的业务逻辑，如果被事务管理，要么同时成功，要么同时失败
     *      开启：setAutoCommit()
     *      提交：commit()
     *      回滚：rollBack()
     */
}
