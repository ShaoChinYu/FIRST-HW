package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import db.Dao;

/**
 * Servlet implementation class Login
 */
@WebServlet(name = "Login", urlPatterns = "/Login.action")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("LOGIN POST START");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/xml;charset=UTF-8");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String state="N";
		Dao dao = Dao.getInstance();
		User user = null;
		try {
			user = dao.login(email, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		if (user == null) {
			System.out.println("不存在用户");
			state = "N";
			out.write(state);
		} else {
			System.out.println("用户验证成功");
			state = "O";
		//	request.getSession().setAttribute("username", user.getUsername());
			out.write(state+"*"+user.getUsername()+"*"+user.getEmail());
		//	response.sendRedirect("ownpg.html");			
		}
		
		out.flush();
		out.close();
		
		

	}

}
