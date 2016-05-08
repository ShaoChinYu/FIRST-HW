package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Question;
import db.Dao;

/**
 * Servlet implementation class GetAllQue
 */
@WebServlet(urlPatterns="/GetAllQue.action")
public class GetAllQue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllQue() {
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
		System.out.println("GETAllQue POST START");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/xml;charset=UTF-8");
		Dao dao = Dao.getInstance();
		Question question[] = null;
		try {
			question = dao.searchAllQuestion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int qnum = question.length;
		int starnum = 2 + qnum;
		PrintWriter out = response.getWriter();
		String data=qnum+"*"+starnum+"*";
		for(int i=0;i<qnum;i++){
			data = data + question[i].getQuestionTopic()+"*";
		}
		out.write(data);
		out.flush();
		out.close();
	}

}
