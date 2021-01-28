package com.bh.oneproject.servlet;

import com.bh.oneproject.pojo.Customer;
import com.bh.oneproject.service.CustomerService;
import com.bh.oneproject.utils.JdbcUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet(urlPatterns="/CustomerServlet")
public class CustomerServlet extends HttpServlet {

    CustomerService customerService ;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /**
         * 1.封装对象
         * 2.添加id
         * 3.调用service
         * 4.成功信息
         * 5.跳转页面
         */
        //获得参数
        String cname = req.getParameter("cname");
        String gender = req.getParameter("gender");
        String birthday = req.getParameter("birthday");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //使用工具类将时间字符串转化为日期对象
        Date date1 = null;
        try {
            date1 = sdf.parse(birthday);
        } catch (ParseException e) {
            System.out.println("转化日期对象失败");
        }
        //获取id
        String cellphone = req.getParameter("cellphone");
        String email = req.getParameter("email");
        String description = req.getParameter("description");
        String ebable = req.getParameter("ebable");
        //构造方法
        String id = JdbcUtils.getId();
        Customer crms = new Customer(id,cname,gender,date1,cellphone,email,description,ebable);
        //调用方法传递修改对象
        int add = customerService.add(crms);
        ServletContext servletContext = this.getServletContext();

        if(1 == add){
            servletContext.setAttribute("msg","添加成功");
            resp.sendRedirect("msg.jsp");
            return;
        }else {
            servletContext.setAttribute("msg","添加失败");
        }
        //跳转
        resp.sendRedirect("msg.jsp");
    }
}
