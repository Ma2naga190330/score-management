package bean;

import java.io.Serializable;

public class ClassNum implements Serializable{
	// word修正 class_num > classNum
	private String classNum;
	private School school;
	private String class_name;
	private boolean class_flag;
	
	public School getSchool() {
		return school;
	}
	// word修正 getClass_num > getClassNum
	public String getClassNum() {
		return classNum;
	}
	
	public String getClassName() {
		return class_name;
	}
	
	public boolean getClassFlag() {
		return class_flag;
	}
	
	public void setSchool(School school) {
		this.school = school;
	}
	// word修正 setClass_num > setClassNum
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	
	public void setClassName(String className) {
		this.class_name = className;
	}
	
	public void setClassFlag(boolean classFlag) {
		this.class_flag = classFlag;
	}
}
