package bean;

import java.io.Serializable;

public class Class implements Serializable{
	private School school;
	private String class_num;
	private String class_name;
	private boolean class_flag;
	
	public School getSchool() {
		return school;
	}
	
	public String getClassNum() {
		return class_num;
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
	
	public void setClassNum(String classNum) {
		this.class_num = classNum;
	}
	
	public void setClassName(String className) {
		this.class_name = className;
	}
	
	public void setClassFlag(boolean classFlag) {
		this.class_flag = classFlag;
	}
}
