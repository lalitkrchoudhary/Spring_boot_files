package in.lalit.dto;

public class StudentDto {
	
	private int sid;
	private String sname;
	private int sage;
	private String sadd;
	
	private int sno;
	private String grade;
	
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public int getSage() {
		return sage;
	}

	public void setSage(int sage) {
		this.sage = sage;
	}

	public String getSadd() {
		return sadd;
	}

	public void setSadd(String sadd) {
		this.sadd = sadd;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "StudentDto [sid=" + sid + ", sname=" + sname + ", sage=" + sage + ", sadd=" + sadd + ", sno=" + sno
				+ ", grade=" + grade + "]";
	}
	
	

}
