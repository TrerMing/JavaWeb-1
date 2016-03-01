package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import dao.DBOperation;

public class ServletRegister extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ServletRegister() {
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
		
		
		//设置字符编码
		request.setCharacterEncoding("utf-8");
		
		UserBean ub = new UserBean();
		
		
		String name = request.getParameter("logname");
		String pwd = request.getParameter("password");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		
		ArrayList param = new ArrayList();
		
		
//		Integer id = 2;
//		param.add(id);
		param.add(name);
		param.add(pwd);
		param.add(sex);
		param.add(age);
		param.add(address);
		param.add(phone);
		
		String sql = "insert into users(name,pwd,sex,age,address,phone) values(?,?,?,?,?,?)";
		
		boolean flag = ub.updateUser(sql,param);
		
		if(flag == true){
//			request.setAttribute("reg", "注册成功");
			request.setAttribute("ok", "1");
//			//把注册成功的用户对象保存在session中
//			request.getSession().setAttribute("regUser", u);
//			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		else
			request.setAttribute("error", "1");
		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("/register.jsp");
		dispatcher.forward(request, response);
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
