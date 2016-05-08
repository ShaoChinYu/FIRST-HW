package bean;

public class Answer {
private int answerID;
private int questionID;
private String content;
private String date;
private String owner;
private String questionTopic;

public String getQuestionTopic() {
	return questionTopic;
}
public void setQuestionTopic(String questionTopic) {
	this.questionTopic = questionTopic;
}
public int getAnswerID() {
	return answerID;
}
public void setAnswerID(int answerID) {
	this.answerID = answerID;
}
public int getQuestionID() {
	return questionID;
}
public void setQuestionID(int questionID) {
	this.questionID = questionID;
}


public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getOwner() {
	return owner;
}
public void setOwner(String owner) {
	this.owner = owner;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}

}
