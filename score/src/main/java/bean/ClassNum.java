package bean;

import java.io.Serializable;

public class ClassNum implements Serializable{
	// word修正 class_num > classNum
	private String classNum;
	private School school;
	
	public School getSchool() {
		return school;
	}
	// word修正 getClass_num > getClassNum
	public String getClassNum() {
		return classNum;
	}
	
	public void setSchool(School school) {
		this.school = school;
	}
	// word修正 setClass_num > setClassNum
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
}
