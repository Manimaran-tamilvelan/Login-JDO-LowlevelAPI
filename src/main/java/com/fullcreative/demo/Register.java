package com.fullcreative.demo;

import java.io.IOException;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fullcreative.jdo.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.cloud.datastore.Transaction;

/**
 * Servlet implementation class Register
 */
@Controller
public class Register extends HttpServlet {

	@RequestMapping("/register")
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String mailID = req.getParameter("mailID");
		String dOB = req.getParameter("dob");
		String mobileNo = req.getParameter("mobileNo");

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Entity e = new Entity("Users", userName+" "+password);
		

		//e.setProperty("userName", userName);
		e.setProperty("password", password);
		e.setProperty("mailID", mailID);
		e.setProperty("dOB", dOB);
		e.setProperty("mobileNo", mobileNo);

		datastore.put(e);

		RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
		String display = "Registered Successfully! You can Login now";

		req.setAttribute("message", display);

		rd.include(req, res);

	}

}
