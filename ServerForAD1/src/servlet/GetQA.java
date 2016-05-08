package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Answer;
import bean.Question;
import bean.User;
import db.Dao;

/**
 * Servlet implementation class GetQA
 */
@WebServlet(urlPatterns = "/GetQA.action")
public class GetQA extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetQA() {
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
		System.out.println("GETQA POST START");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/xml;charset=UTF-8");
		String username = request.getParameter("username");
		Dao dao = Dao.getInstance();
		Question question[] = null;
		Answer answer[] = null;
		try {
			question = dao.searchQuestion(username);
			answer = dao.searchAnswer(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int qnum;
		int anum;
		if(question == null){qnum = 0;}else{qnum = question.length;}
		if(answer == null){anum = 0;}else{anum = answer.length;}
		int starnum = 3 + qnum + 3*anum;
		PrintWriter out = response.getWriter();
		String data=qnum+"*"+anum+"*"+starnum+"*";
		for(int i=0;i<qnum;i++){
			data = data + question[i].getQuestionTopic()+"*";
		}
		for(int i=0;i<anum;i++){
			data = data + answer[i].getQuestionTopic()+"*"+ answer[i].getContent()+"*"+answer[i].getDate()+"*";
		}
		out.write(data);
		out.flush();
		out.close();
		
	}

}
