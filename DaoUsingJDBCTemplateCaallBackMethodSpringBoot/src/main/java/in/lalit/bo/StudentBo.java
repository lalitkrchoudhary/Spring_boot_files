package in.lalit.bo;

public class StudentBo {

	private int sid;
	private String sname;
	private  int sage;
	private String sadd;
	
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

	@Override
	public String toString() {
		return "StudentBo [sid=" + sid + ", sname=" + sname + ", sage=" + sage + ", sadd=" + sadd + "]";
	}
	
	
}
