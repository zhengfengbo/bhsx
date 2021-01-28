package com.bh.oneproject.dao;

import com.bh.oneproject.pojo.Customer;
import com.bh.oneproject.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CustomerDao {
    /**
     * sql
     * 参数
     */
    //private QueryRunner qr = new QueryRunner();
    //获取数据源
    private DataSource ds = JdbcUtils.getDataSource();
    //生成QueryRunner对象
    private QueryRunner qr = new QueryRunner(ds);
    //添加一个顾客
    public int addCustomer(Customer c){
        //获取数据源
        DataSource ds = JdbcUtils.getDataSource();
        //生成QueryRunner对象
        QueryRunner qr = new QueryRunner(ds);
        String sql = "insert into tb_customer values (?,?,?,?,?,?,?,?)";
        Object[] objects = {c.getCid(),c.getCname(),c.getGender(),c.getBirthday(),c.getCellphone(),c.getEmail(),c.getDescription(),"0"};
        int rows = 0;
        try {
            rows = qr.update(sql,objects);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }
    //查询所有顾客
    public List queryCustomer(){
        List<Customer> list = null;
        //获取数据源
        //DataSource ds = JdbcUtils.getDataSource();
        //生成QueryRunner对象
        //QueryRunner qr = new QueryRunner(ds);
        String sql = "select * from tb_customer where ebable = 0";
        try {
            list = qr.query(sql, new BeanListHandler<>(Customer.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //List<Map<String,Object>> list = qr.query(sql, new MapListHandler());
        return list;
    }
    //根据id查询顾客信息
    public Customer queryIdCustomer(String id){
        Customer list = null;
        String sql = "select * from tb_customer where cid=?";
        try {
            list= qr.query(sql,new BeanHandler<>(Customer.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    //编辑顾客信息
    public int editCustomer(Customer c){
        String sql = "update tb_customer set cname=?,gender=?,birthday=?,cellphone=?,email=?,description=?where cid = ?";
        Object[] objects = {c.getCname(),c.getGender(),c.getBirthday(),c.getCellphone(),c.getEmail(),c.getDescription(),c.getCid()};
        int rows = 0;
        try {
            rows = qr.update(sql,objects);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }
    //删除顾客信息
    //隐藏顾客信息
    public int deleteCustomer(String id){
        String sql = "update tb_customer set ebable = 1 where cid = ?";

        int rows = 0;
        try {
            rows = qr.update(sql,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows;

    }
    //根据条件查询顾客信息
    public List seniorQuery(Customer c){
        String sql = "select * from tb_customer as t where(t.cname like ? or ? ='') and (t.gender = ? or ? ='')" +
                "and (t.cellphone = ? or  ? ='') and (t.email = ? or ? ='') and ebable = 0 ";
        //设置参数值
        Object[] objects = {
                "%"+c.getCname().trim()+"%",c.getCname().trim(),
                c.getGender().trim(),c.getGender().trim(),
                c.getCellphone().trim(), c.getCellphone().trim(),
                c.getEmail().trim(),c.getEmail().trim()
        };
        List<Customer> list = null;
        try {
            list = qr.query(sql,new BeanListHandler<>(Customer.class),objects);
            //list =  qr.query(sql,new BeanHandler<>(Customer.class),objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
