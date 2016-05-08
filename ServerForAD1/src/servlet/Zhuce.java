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
 * Servlet implementation class Zhuce
 */
@WebServlet(name="Zhuce",urlPatterns="/Zhuce.action")
public class Zhuce extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Zhuce() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String state ="";
		Dao dao = Dao.getInstance();
		User user = null;
		try {
			user = dao.zhuce(username, email,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		if (user == null) {
			System.out.println("注册错误");
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
