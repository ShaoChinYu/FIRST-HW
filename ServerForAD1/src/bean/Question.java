package bean;

public class Question {
	private int questionID;
	private String questionOwner;
	private String questionTopic;
	private String questionContent;
	private String questionDate;
	public int getquestionID() {
		return questionID;
	}
	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}
	public String getQuestionTopic() {
		return questionTopic;
	}
	public void setQuestionTopic(String questionTopic) {
		this.questionTopic = questionTopic;
	}
	public String getquestionContent() {
		return questionContent;
	}
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	public String getQuestionDate() {
		return questionDate;
	}
	public void setQuestionDate(String questionDate) {
		this.questionDate = questionDate;
	}
	public String getQuestionOwner() {
		return questionOwner;
	}
	public void setQuestionOwner(String questionOwner) {
		this.questionOwner = questionOwner;
	}
}
