package com.bh.oneproject.servlet;

import com.bh.oneproject.pojo.Customer;
import com.bh.oneproject.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns="/seniorQuery")
public class CustomerSeniorQuery extends HttpServlet {
    CustomerService customerService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String cname1 = req.getParameter("cname");
        String gender = req.getParameter("gender");
        String cellphone = req.getParameter("cellphone");
        String email = req.getParameter("email");
        //构造方法
        Customer c = new Customer(cname1, gender, cellphone, email);
        List list = customerService.seniorQuery(c);
        req.setAttribute("list", list);
        //跳转页面
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }
}
