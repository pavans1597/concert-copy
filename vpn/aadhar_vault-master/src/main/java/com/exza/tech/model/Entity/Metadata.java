package com.exza.tech.model.Entity;
import javax.persistence.*;

@Entity(name="Metadata")
@Table(name="Metadata")
public class Metadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "metaId")
    private int metaId;

    @Column(name = "empId")
    private String empId ;

    @ManyToOne
    @JoinColumn(name = "clientCode")
    private Clients clientCode;


    @Column(name = "EmpName")
    private String EmpName ;

    public Metadata(String empId, String empName,Clients client) {
        this.empId = empId;
        this.EmpName = empName;
this.clientCode=client;

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
