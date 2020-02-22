package ru.test;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.model.FLocal;

@WebServlet("/s1")
public class S1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private FLocal facade;

	public S1() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath()).append(" ---> ")
				.append(facade.toString()).append("\n /facade current class -> ").append(facade.getClass().toString())
				.append("\n /invoke ---> ").append(facade.info())
				.append("\n /invoke ---> ").append(facade.userInfo())
				.append("\n /facade current class -> ").append(facade.getClass().toString())
				.append(" ___ ").append(facade.getClass().toGenericString());
	}

}
