package com.exza.tech.model.requestbody;

public class Metadata {

    private String empId ;
    private String EmpName ;

    public Metadata(String empId, String empName) {
        this.empId = empId;
        EmpName = empName;
    }

    public Metadata() {
    }

    public Metadata(Object metadata) {
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
    }


}
