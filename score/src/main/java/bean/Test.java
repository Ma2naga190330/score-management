package bean;

import java.io.Serializable;

public class Test implements Serializable{
	private String classNum;
	private Student student;
	private Subject subject;
	private School school;
	private int no;
	private int point;
	
	public String getClassNum() {
		return classNum;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public Subject getSubject() {
		return subject;
	}
	
	public School getSchool() {
		return school;
	}
	public int getNo() {
        return no;
    }
	public int getPoint() {
	        return point;
	}
	
	
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	public void setNo(int no) {
        this.no = no;
    }
	public void setPoint(int point) {
        this.point = point;
    }
}