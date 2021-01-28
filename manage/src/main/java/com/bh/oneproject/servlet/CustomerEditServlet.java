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


@WebServlet(urlPatterns="/editServlet")
public class CustomerEditServlet extends HttpServlet {
    //@Autowired
    CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
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
        //使用工具类把时间字符串转为日期对象
        Date date1 = null;
        try {
            date1 = sdf.parse(birthday);
        } catch (ParseException e) {
            //e.printStackTrace();
            System.out.println("转为日期对象失败");
        }


        String cellphone = req.getParameter("cellphone");
        String email = req.getParameter("email");
        String description = req.getParameter("description");
        String ebable = req.getParameter("ebable");
        String id = req.getParameter("id");
        Customer customer = new Customer(id,cname,gender,date1,cellphone,email,description,ebable);
        int row = customerService.editCustomer(customer);
        ServletContext servletContext = this.getServletContext();

        if(1 == row){
            servletContext.setAttribute("msg","修改成功");
            resp.sendRedirect("msg.jsp");
            return;
        }else {
            servletContext.setAttribute("msg","修改失败");
            resp.sendRedirect("msg.jsp");
            return;
        }

    }
}
