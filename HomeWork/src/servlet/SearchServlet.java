package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.PageUtil;
import dao.DBOperation;

public class SearchServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SearchServlet() {
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
		
		String name = request.getParameter("name");
		String book1 = request.getParameter("book1");

		

		int totalCount = 0;//总记录数
		int currentPage = 1;//当前页数

		DBOperation db = new DBOperation();
		
		PageUtil pu = new PageUtil(5, totalCount);//每页显示5条记录，共totalCount条记录
		
		int pageSize = pu.getPageSize();
		//获得当前页数
		if(request.getParameter("page") != null && !request.getParameter("page").equals("0"))
			currentPage = Integer.parseInt(request.getParameter("page"));
		
		pu.setCurrentPage(currentPage);//将当前页数set
		
		
		int fromIndex = (currentPage - 1) * pageSize;
		
		System.out.println("fromIndex:" + fromIndex);
		System.out.println("pageSize:" + pageSize);
		
		//初始化sql语句
		String sql = "select * from books";
		String sqlCount = "select count(*) from books";
		
		//生成sql语句
		if(name != null && !name.equals("")){
			sql += " where name like '%"+name+"%' limit " + fromIndex + "," + pageSize;
//			 limit '"+(pu.getCurrentPage()-1)*pu.getPageSize()+"','"+pu.getPageSize()+"'
			sqlCount += " where name like '%"+name+"%'";
			
			if(book1 != null && !book1.equals("")){
				sql += "and publishing like '%"+book1+"%'";
//				 limit '"+(pu.getCurrentPage()-1)*pu.getPageSize()+"','"+pu.getPageSize()+"'
				sqlCount += "and publishing like '%"+book1+"%'";
			}
		}
		else if(name == null || name.equals("")){
			if(book1 != null && !book1.equals("")){
				sql += " where publishing like '%"+book1+"%' limit " + fromIndex + "," + pageSize;
//				 limit '"+(pu.getCurrentPage()-1)*pu.getPageSize()+"','"+pu.getPageSize()+"'
				sqlCount += " where publishing like '%"+book1+"%'";
			}
			else if(book1 == null || book1.equals("")){
				sql += " limit " + fromIndex + "," + pageSize;
			}
		}
		
		totalCount = db.getCount(sqlCount);
		pu.setRecordCount(totalCount);


		
		//保存当前页数
		request.setAttribute("currentPage", currentPage);
		//保存总记录数
		request.setAttribute("totalCount", totalCount);
		//保存总页数
		request.setAttribute("pageCount", pu.getPageCount());
		//保存查询结果
		request.setAttribute("search", db.queryReturnList(sql));
		
		RequestDispatcher rd = request.getRequestDispatcher("/booklist.jsp");
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
