package net.javaguides.exammanagement.model;

public class Exam {
	protected int examid;
	protected String subjectname;
	protected String dateandtime;
	protected String type;
	
	public Exam() {
		
	}
	public Exam(String subjectname, String dateandtime, String type) {
		super();
		this.subjectname = subjectname;
		this.dateandtime = dateandtime;
		this.type = type;
	}
	
	public Exam(int examid, String subjectname, String dateandtime, String type) {
		super();
		this.examid = examid;
		this.subjectname = subjectname;
		this.dateandtime = dateandtime;
		this.type = type;
	}
	public int getExamid() {
		return examid;
	}

	public void setExamid(int examid) {
		this.examid = examid;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getDateandtime() {
		return dateandtime;
	}

	public void setDateandtime(String dateandtime) {
		this.dateandtime = dateandtime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
	
	
}
