package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBOperation;

public class ListServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ListServlet() {
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
		
		DBOperation op = new DBOperation();
		//图书分类
		String sql1 = "select * from types";
		ArrayList al1 = op.queryReturnList(sql1);
		request.setAttribute("type", al1);
		
		//图书类别（最新推荐书，最新上架书）
		String sql2 = "select * from tates";
		ArrayList al2 = op.queryReturnList(sql2);
		request.setAttribute("tate", al2);
		
		//最新推荐书
		String sql3 = "select * from books where tate = 1";
		ArrayList al3 = op.queryReturnList(sql3);
		request.setAttribute("tuibook", al3);
		
		//最新上架书
		String sql4 = "select * from books where tate = 2";
		ArrayList al4 = op.queryReturnList(sql4);
		request.setAttribute("newbook", al3);
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
