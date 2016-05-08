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
import db.Dao;

/**
 * Servlet implementation class ReadQue
 */
@WebServlet(urlPatterns="/ReadQue.action")
public class ReadQue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadQue() {
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
		
		System.out.println("READQUE POST START");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/xml;charset=UTF-8");
		String topic = request.getParameter("topic");
		Dao dao = Dao.getInstance();
		Question question = null;
		Answer answer[] = null;
		try {
			question = dao.readQuestion(topic);
			answer = dao.readAnswer(topic);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int anum;
		if(answer == null){anum = 0;}else{anum = answer.length;}
		int starnum = 5 + 3*anum;
		PrintWriter out = response.getWriter();
		String data = anum + "*" + starnum + "*" + question.getQuestionTopic() + "*" + question.getQuestionOwner() + "*" + question.getQuestionDate() + "*";
		for(int i=0;i<anum;i++){
			data = data + answer[i].getOwner()+"*"+ answer[i].getContent()+"*"+answer[i].getDate()+"*";
		}
		out.write(data);
		out.flush();
		out.close();
	}

}
