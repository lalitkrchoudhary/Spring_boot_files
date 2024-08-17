package in.lalit.vo;

public class EmpVo {
	
private String srNo;
private String empName;
private String sal;
private String deptNo;
private String mrgNo;

public String getSrNo() {
	return srNo;
}

public void setSrNo(String srNo) {
	this.srNo = srNo;
}

public String getEmpName() {
	return empName;
}

public void setEmpName(String empName) {
	this.empName = empName;
}

public String getSal() {
	return sal;
}

public void setSal(String sal) {
	this.sal = sal;
}

public String getDeptNo() {
	return deptNo;
}

public void setDeptNo(String deptNo) {
	this.deptNo = deptNo;
}

public String getMrgNo() {
	return mrgNo;
}

public void setMrgNo(String mrgNo) {
	this.mrgNo = mrgNo;
}

@Override
public String toString() {
	return "EmpVo [srNo=" + srNo + ", empName=" + empName + ", sal=" + sal + ", deptNo=" + deptNo + ", mrgNo=" + mrgNo
			+ "]";
}

	

}
