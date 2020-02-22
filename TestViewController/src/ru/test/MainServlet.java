package ru.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;

@WebServlet(urlPatterns = { "/test" })
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private Logger log = Logger.getLogger("TEST");
	private long id;

	public MainServlet() {
		super();
		this.id = System.currentTimeMillis();
		log.info("=====App started=====" + id);
	}

	@Override
	public void destroy() {
		super.destroy();
		log.info("-----App destroyed-----" + id);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			out.print("<html>");
			HttpSession session = request.getSession();
			Long counter = (Long) session.getAttribute("counter");
			if (counter == null) {
				counter = 0L;
			}
			counter++;
			session.setAttribute("counter", counter);
			log.info("service >>> " + Thread.currentThread());
			TimeUnit.MILLISECONDS.sleep(50);
			Context context = new InitialContext();
			NamingEnumeration ne = context.list("");
			while (ne.hasMoreElements()) {
				Object object = (Object) ne.nextElement();
				out.print("<hr/>" + object);
			}
			try {
				context.lookup("java:/counter");
			} catch (Exception e) {
				context.bind("java:/counter", 0L);
			}
			Long c1 = (Long) context.lookup("java:/counter");
			context.rebind("java:/counter", ++c1);
			out.print("<hr/>" + c1);
			out.print("</html>");
		} catch (Exception e) {
			log.error(e.getStackTrace());
			out.print(e.toString());
		} finally {
			out.close();
		}
	}

}
