package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.mysql.jdbc.PreparedStatement;

import bean.*;



public class Dao {
	private static String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://127.0.0.1:3306/jwebhw1?useUnicode=true&amp;characterEncoding=UTF-8&amp;";
	
	//your username and password
	String dbUsername = "root"; 
	String dbPassword = "5220297a";
	
	private static Dao dao;
	
	private Dao(){};
	
	public static Dao getInstance(){
		if(dao == null){
			dao = new Dao();
		}
		return dao;
	}
	static {
		try {
			Class.forName(driver).newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	Connection con = null;
	Statement sm = null;
	ResultSet results = null;
	

	public User zhuce(String username, String mail,String pwd) throws SQLException{
		try {
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			sm = con.createStatement();
			results = sm.executeQuery("select * from user where user.username='"+username+"'");
			if(results.next()){
				  return null;
			}
			results = null;
			results = sm.executeQuery("select * from user where user.mail='"+mail+"'");
			if(results.next()){
				  return null;
			}else{
				sm.executeUpdate("insert into user(username,pwd,mail) values('"+username+"', '"+pwd+"','"+mail+"')");
				results.close();
				results = sm.executeQuery("select * from user where user.mail='"+mail+"'");
				User user = new User(); 
				if(results.next()){
					user.setUsername(username);
					user.setEmail(mail);
					user.setPassword(pwd);
				}
				results.close();
				return user;
			}
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(sm != null){
				sm.close();
			}
			if(con != null){
				con.close();	
			}
			if(results != null){
				results.close();
			}
		} 
		
		return null;
		
	}
	


	public User login(String mail,String pwd) throws SQLException{
		try {
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			sm = con.createStatement();
			results = sm.executeQuery("select * from user where user.mail='"+mail+"' and user.pwd='"+pwd+"'");
			if( !(results.next()) ){
				  return null;
			}else{
				results = sm.executeQuery("select * from user where user.mail='"+mail+"' and user.pwd='"+pwd+"'");
				User user = new User(); 
				if(results.next()){
					user.setUsername(results.getString("username"));
					user.setEmail(mail);
					user.setPassword(pwd);
				}
				results.close();
				return user;
			}
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(sm != null){
				sm.close();
			}
			if(con != null){
				con.close();	
			}
			if(results != null){
				results.close();
			}
		} 
		
		return null;
		
	}	
	
	public Question[] searchAllQuestion() throws SQLException{
		Question question[] = null;
		int questioncount = 0;
		try {
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			sm = con.createStatement();
			results = sm.executeQuery("select * from question");
			if( !(results.next()) ){
				  return null;
			}else{
				results = sm.executeQuery("select * from question");
				while(results.next()) {
					questioncount++;
				}
				question = new Question[questioncount];
				results = null;
				results = sm.executeQuery("select * from question");
				int tmp=0;
				while(results.next()){
					question[tmp]=new Question();
					question[tmp].setQuestionOwner(results.getString("owner"));
					question[tmp].setQuestionContent(results.getString("content"));
					question[tmp].setQuestionTopic(results.getString("topic"));
					question[tmp].setQuestionID(results.getInt("queId"));
					question[tmp].setQuestionDate((results.getDate("time")+""));
					tmp++;
				}
				
			}
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(sm != null){
				sm.close();
			}
			if(con != null){
				con.close();	
			}
			if(results != null){
				results.close();
			}
		} 
		
		return question;
		
	}	

	public Answer[] searchAnswer(String username) throws SQLException{
		Answer[] answer = null;
		int answercount = 0;
		try {
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			sm = con.createStatement();
			results = sm.executeQuery("select * from answer where answer.owner='"+username+"'");
			if( !(results.next()) ){
				  return null;
			}else{
				results = sm.executeQuery("select * from answer where answer.owner='"+username+"'");
				while(results.next()) {
					answercount++;
				}
				answer = new Answer[answercount];
				results = null;
				results = sm.executeQuery("select * from answer where answer.owner='"+username+"'");
				int tmp=0;
				while(results.next()){
					answer[tmp]=new Answer();
					answer[tmp].setAnswerID(results.getInt("ansId"));
					answer[tmp].setContent(results.getString("content"));
					answer[tmp].setDate(results.getDate("date")+"");
					answer[tmp].setOwner(results.getString("owner"));
					answer[tmp].setQuestionTopic(results.getString("questionTopic"));
					tmp++;
				}
				
			}
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(sm != null){
				sm.close();
			}
			if(con != null){
				con.close();	
			}
			if(results != null){
				results.close();
			}
		} 
		
		return answer;
		
	}	

	public Question[] searchQuestion(String username) throws SQLException{
		Question question[] = null;
		int questioncount = 0;
		try {
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			sm = con.createStatement();
			results = sm.executeQuery("select * from question where question.owner='"+username+"'");
			if( !(results.next()) ){
				  return null;
			}else{
				results = sm.executeQuery("select * from question where question.owner='"+username+"'");
				while(results.next()) {
					questioncount++;
				}
				question = new Question[questioncount];
				results = null;
				results = sm.executeQuery("select * from question where question.owner='"+username+"'");
				int tmp=0;
				while(results.next()){
					question[tmp]=new Question();
					question[tmp].setQuestionOwner(results.getString("owner"));
					question[tmp].setQuestionContent(results.getString("content"));
					question[tmp].setQuestionTopic(results.getString("topic"));
					question[tmp].setQuestionID(results.getInt("queId"));
					question[tmp].setQuestionDate((results.getDate("time")+""));
					tmp++;
				}
				
			}
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(sm != null){
				sm.close();
			}
			if(con != null){
				con.close();	
			}
			if(results != null){
				results.close();
			}
		} 
		
		return question;
		
	}	

	public void askQuestion(String topic,String content,String time,String owner) throws SQLException{
		try {
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			sm = con.createStatement();
			long l  = System.currentTimeMillis(); 
			Date date = new Date(l);
				sm.executeUpdate("insert into question(topic,content,time,owner) values('"+topic+"', '"+content+"','"+date+"','"+owner+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(sm != null){
				sm.close();
			}
			if(con != null){
				con.close();	
			}
			if(results != null){
				results.close();
			}
		} 
		
		return;
		
	}

	public Question readQuestion(String topic) throws SQLException{
		Question question = null;
		try {
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			sm = con.createStatement();
			results = sm.executeQuery("select * from question where question.topic='"+topic+"'");
			if(results.next()){
				question=new Question();
				question.setQuestionOwner(results.getString("owner"));
				question.setQuestionContent(results.getString("content"));
				question.setQuestionTopic(results.getString("topic"));
				question.setQuestionID(results.getInt("queId"));
				question.setQuestionDate((results.getDate("time")+""));
			}		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(sm != null){
				sm.close();
			}
			if(con != null){
				con.close();	
			}
			if(results != null){
				results.close();
			}
		} 
		
		return question;
		
	}	
	
	public Answer[] readAnswer(String topic) throws SQLException{
		Answer[] answer = null;
		int answercount = 0;
		try {
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			sm = con.createStatement();
			results = sm.executeQuery("select * from answer where answer.questionTopic='"+topic+"'");
			if( !(results.next()) ){
				  return null;
			}else{
				results = sm.executeQuery("select * from answer where answer.questionTopic='"+topic+"'");
				while(results.next()) {
					answercount++;
				}
				answer = new Answer[answercount];
				results = null;
				results = sm.executeQuery("select * from answer where answer.questionTopic='"+topic+"'");
				int tmp=0;
				while(results.next()){
					answer[tmp]=new Answer();
					answer[tmp].setAnswerID(results.getInt("ansId"));
					answer[tmp].setContent(results.getString("content"));
					answer[tmp].setDate(results.getDate("date")+"");
					answer[tmp].setOwner(results.getString("owner"));
					answer[tmp].setQuestionTopic(results.getString("questionTopic"));
					tmp++;
				}
				
			}
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(sm != null){
				sm.close();
			}
			if(con != null){
				con.close();	
			}
			if(results != null){
				results.close();
			}
		} 
		
		return answer;
		
	}	

	public void answerQuestion(String topic,String content,String time,String owner) throws SQLException{
		try {
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			sm = con.createStatement();
			long l  = System.currentTimeMillis(); 
			Date date = new Date(l);
				sm.executeUpdate("insert into answer(questionTopic,content,date,owner) values('"+topic+"', '"+content+"','"+date+"','"+owner+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(sm != null){
				sm.close();
			}
			if(con != null){
				con.close();	
			}
			if(results != null){
				results.close();
			}
		} 
		
		return;
		
	}

}
