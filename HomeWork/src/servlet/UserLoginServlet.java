package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBOperation;
import bean.UserBean;

public class UserLoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserLoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//ÉèÖÃ×Ö·û±àÂë
		request.setCharacterEncoding("utf-8");
			
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
//		UserBean ub = new UserBean();
		DBOperation dbo = new DBOperation();
		String sql1 = "select * from users where name='"+name+"' and pwd='"+pwd+"'";
//		ArrayList alList = ub.getLoginUser(sql1);
//		u = ub.getUserByName(name, pwd);
//		if(ub.getUserByName(name, pwd)){
		if(dbo.queryReturnboolean(sql1)){
			HttpSession session = request.getSession();
			session.setAttribute("login", name);
		}
		else
			request.setAttribute("loginError", "error");
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
