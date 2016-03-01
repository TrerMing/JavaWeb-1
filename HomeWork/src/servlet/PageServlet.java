package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.PageUtil;
import dao.DBOperation;

public class PageServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PageServlet() {
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

//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
//		out.println("<HTML>");
//		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
//		out.println("  <BODY>");
//		out.print("    This is ");
//		out.print(this.getClass());
//		out.println(", using the GET method");
//		out.println("  </BODY>");
//		out.println("</HTML>");
//		out.flush();
//		out.close();
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

//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
//		out.println("<HTML>");
//		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
//		out.println("  <BODY>");
//		out.print("    This is ");
//		out.print(this.getClass());
//		out.println(", using the POST method");
//		out.println("  </BODY>");
//		out.println("</HTML>");
//		out.flush();
//		out.close();
		request.setCharacterEncoding("utf-8");
		
		System.out.println("����");
		
		DBOperation dbo = new DBOperation();
		int totalCount = 0;
		int currentPage = 1;
		ArrayList arrayList = null;
		String sql = "select count(*) from books";
		totalCount = dbo.getCount(sql);//����ܼ�¼��
		
		System.out.println(totalCount);
		
		//��õ�ǰҳ��
		if(request.getParameter("page") != null)
			currentPage = Integer.parseInt(request.getParameter("page"));
		
		
		
		PageUtil pu = new PageUtil(5, totalCount);//ÿҳ��ʾ5����¼����totalCount����¼
		pu.setCurrentPage(currentPage);//����ǰҳ��set
		
		//�ѵ�ǰҳ��������session��
		HttpSession session = request.getSession();
		session.setAttribute("currentPage", pu.getCurrentPage());
		
		String sql1 = "select top" + pu.getPageSize() + "* from books where id not in" + 
						"(select top" + pu.getPageSize() * (pu.getCurrentPage() - 1) +"id from books"
						+ "order by id) order by id";//ѡ��ǰ������¼��������������¼��id����֮ǰҳ���id�У�ͨ��id��ѡ
		//����5����¼
		arrayList = dbo.queryReturnList(sql1);
		
		request.setAttribute("search", arrayList);
		request.setAttribute("pageCount", pu.getPageCount());
		
//		System.out.println(pu.getPageCount());
		
		request.getRequestDispatcher("/booklist.jsp").forward(request, response);
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
