package in.lallit.bo;

public class EmpBo {
	
	private Integer empNo;
	private String ename;
	private String job;
	private Double sal;
	private Integer deptNo;
	private Integer mrgNo;
	
	
	public Integer getEmpNo() {
		return empNo;
	}


	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}


	public String getEname() {
		return ename;
	}


	public void setEname(String ename) {
		this.ename = ename;
	}


	public String getJob() {
		return job;
	}


	public void setJob(String job) {
		this.job = job;
	}


	public Double getSal() {
		return sal;
	}


	public void setSal(Double sal) {
		this.sal = sal;
	}


	public Integer getDeptNo() {
		return deptNo;
	}


	public void setDeptNo(Integer deptNo) {
		this.deptNo = deptNo;
	}


	public Integer getMrgNo() {
		return mrgNo;
	}


	public void setMrgNo(Integer mrgNo) {
		this.mrgNo = mrgNo;
	}


	@Override
	public String toString() {
		return "EmpBo [empNo=" + empNo + ", ename=" + ename + ", job=" + job + ", sal=" + sal + ", deptNo=" + deptNo
				+ ", mrgNo=" + mrgNo + "]";
	}
	
	
}
